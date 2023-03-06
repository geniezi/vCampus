package DAO.QICQ;

import ServerToClient.ManageServerToClientThread;
import ServerToClient.ServerToClient;
import connection.JDBC_Connector;
import message.Message;
import message.MessageType;
import utils.MyObjectOutputStream;
import utils.myTime;

import java.io.*;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

/**
 * 站内通信模块与数据库对接类
 *
 * @author Shuheng_Gu
 * @date 2022/09/03
 */
public class QICQ_manager {
    /**
     * 用户id
     */
    String id;
    /**
     * JDBC数据库链接
     */
    private static Connection conn;

    public QICQ_manager(String id) {
        this.id = id;
        try {
            conn= JDBC_Connector.ConnectMySQL();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 朋友上线
     *
     * @param id id
     * @throws SQLException sqlexception异常
     * @throws IOException  ioexception
     */
    public void friend_is_online(String id) throws SQLException, IOException {
    //    System.out.println("wanting online");
        Message msg=new Message();
        msg.setType(MessageType.MESSAGE_QICQ_FRIEND_ONLINE_RET);
        msg.setData(id);
        String sql="select * from friends where user_id=?;";
        PreparedStatement st=conn.prepareStatement(sql);
        st.setString(1,id);
        ResultSet rs=st.executeQuery();
        while(rs.next()){
            String friend_id=rs.getString("friend_id");
        //    System.out.println(friend_id+" check");
            if(ServerToClient.isOnline(friend_id)!=-1){
        //        System.out.println(friend_id);
                ManageServerToClientThread.getThread(friend_id).oos.writeObject(msg);
            }
        }
    }

    /**
     * 朋友离线
     *
     * @param id id
     * @throws SQLException sqlexception异常
     * @throws IOException  ioexception
     */
    public void friend_is_offline(String id) throws SQLException, IOException {
        Message msg=new Message();
        msg.setType(MessageType.MESSAGE_QICQ_FRIEND_OFFLINE_RET);
        msg.setData(id);
        String sql="select * from friends where user_id=?;";
        PreparedStatement st=conn.prepareStatement(sql);
        st.setString(1,id);
        ResultSet rs=st.executeQuery();
        while(rs.next()){
            String friend_id=rs.getString("friend_id");
            if(ServerToClient.isOnline(friend_id)!=-1){
                ManageServerToClientThread.getThread(friend_id).oos.writeObject(msg);
            }
        }
        rs.close();
        st.close();
    }

    /**
     * 得到好友列表
     *
     * @return {@link Message}
     * @throws SQLException sqlexception异常
     * @throws IOException  ioexception
     */
    public Message get_friends() throws SQLException, IOException {
        HashMap<String, ArrayList<Friend>> friends = new HashMap<>();
        String sql="select * from friends where user_id=? order by friend_id+0;";
        PreparedStatement st= conn.prepareStatement(sql);
        st.setString(1,id);
        ResultSet rs= st.executeQuery();
        while(rs.next()){
            Friend friend=new Friend();
            String group=rs.getString("relation");
            friend.name=rs.getString("nickname");
            friend.id=rs.getString("friend_id");
            if(ServerToClient.isOnline(friend.id)!=-1) {
                friend.setOnline(1);
               // System.out.println(friend.id+" ok");
            }
            else friend.setOnline(0);
            friend.image=readicon(friend.id);
            sql="select * from message where sender=? and getter=? and isread=0;";
            st= conn.prepareStatement(sql);
            st.setString(1, friend.id);
            st.setString(2,id);
            ResultSet rs1= st.executeQuery();
            if(rs1.next()) friend.unread=1;
            else friend.unread=0;
            if(friends.containsKey(group)){
                friends.get(group).add(friend);
            }
            else {
               // System.out.println(group);
                ArrayList<Friend> f = new ArrayList<>();
                f.add(friend);
                friends.put(group,f);
            }

        }
        Message msg=new Message();
        msg.setType(MessageType.MESSAGE_QICQ_LIST_FRIENDS_RET);
        msg.setData(friends);
    //    MyObjectOutputStream oos=new MyObjectOutputStream(ManageServerToClientThread.getThread(to).getSocket().getOutputStream());
        return msg;
    }

    /**
     * 在线发送文件
     *
     * @param msg 消息
     * @throws IOException  ioexception
     * @throws SQLException sqlexception异常
     */
    public void send_online_file(Message msg) throws IOException, SQLException {
        String to=msg.getGetter();
        String sql="insert into message(sender,getter,sendtime,file,isfile,isread,filename) values(?,?,?,?,1,0,?);";
        PreparedStatement st=conn.prepareStatement(sql);
        st.setString(1,msg.getSender());
        st.setString(2,msg.getGetter());
        st.setString(3,msg.getSendTime());
        Filetrans f=(Filetrans)msg.getData();
   //     System.out.println(f.getName());
        InputStream is = new ByteArrayInputStream(f.getContent());
        st.setBlob(4,is);
        st.setString(5,f.getName());
        st.executeUpdate();
        st.close();
      //  return msg;
    }

    /**
     * 发送离线文件
     *
     * @param msg 消息
     * @throws IOException  ioexception
     * @throws SQLException sqlexception异常
     */
    public void send_offline_file(Message msg) throws IOException, SQLException {
        String to=msg.getGetter();
        String sql="insert into message(sender,getter,sendtime,file,isfile,isread,filename) values(?,?,?,?,1,0,?);";
        PreparedStatement st=conn.prepareStatement(sql);
        st.setString(1,msg.getSender());
        st.setString(2,msg.getGetter());
        st.setString(3,msg.getSendTime());
        Filetrans f=(Filetrans)msg.getData();
      //  System.out.println(f.getName());
        InputStream is = new ByteArrayInputStream(f.getContent());
        st.setBlob(4,is);
        st.setString(5,f.getName());
        st.executeUpdate();
        st.close();
    }

    /**
     * 发送在西安消息
     *
     * @param msg 消息
     * @throws IOException  ioexception
     * @throws SQLException sqlexception异常
     */
    public void send_online_message(Message msg) throws IOException, SQLException {
        String to=msg.getGetter();
        msg.setType(MessageType.MESSAGE_QICQ_RECERIVE_MESSAGE);
      //  ObjectOutputStream oos=new ObjectOutputStream(ManageServerToClientThread.getThread(to).getSocket().getOutputStream());
        String sql="insert into message(sender,getter,sendtime,content,isread,isfile) values(?,?,?,?,0,0);";
        PreparedStatement st=conn.prepareStatement(sql);
        st.setString(1,msg.getSender());
        st.setString(2,msg.getGetter());
        st.setString(3,msg.getSendTime());
        st.setString(4,(String)msg.getData());
        st.executeUpdate();
        ManageServerToClientThread.getThread(msg.getGetter()).oos.writeObject(msg);
       // oos.writeObject(msg);
        st.close();
    }

    /**
     * 发送离线消息
     *
     * @param msg 消息
     * @throws IOException  ioexception
     * @throws SQLException sqlexception异常
     */
    public void send_offline_message(Message msg) throws IOException, SQLException {
        String to=msg.getGetter();
        String sql="insert into message(sender,getter,sendtime,content,isread,isfile) values(?,?,?,?,0,0);";
        PreparedStatement st=conn.prepareStatement(sql);
        st.setString(1,msg.getSender());
        st.setString(2,msg.getGetter());
        st.setString(3,msg.getSendTime());
        st.setString(4,(String)msg.getData());
        st.executeUpdate();
        //ServerToClient.addQQbox(to,msg);
        st.close();
    }

    /**
     * 添加朋友
     *
     * @param message 消息
     * @throws IOException  ioexception
     * @throws SQLException sqlexception异常
     */
    public void add_friend(Message message) throws IOException, SQLException {
        Application app=(Application)message.getData();
        int flag=0;
        String sql="select * from students where Student_idcard=?;";
        PreparedStatement st=conn.prepareStatement(sql);
        st.setString(1,app.to_id);
      //  System.out.println(app.to_id);
        ResultSet rs=st.executeQuery();
        if(rs.next()){
            String sql1="insert into new_friend(sender,getter,sendtime,status,nickname,group) values(?,?,?,0,?,?);";
            PreparedStatement st1=conn.prepareStatement(sql1);
            st1.setString(1,app.from_id);
            st1.setString(2,app.to_id);
            st1.setString(3,message.getSendTime());
            st1.setString(4,app.to_name);
            st1.setString(4,app.group);
            st1.executeUpdate();
        }
        else{
            sql="select * from teachers where Teacher_idcard=?;";
            st=conn.prepareStatement(sql);
            st.setString(1,app.to_id);
            rs=st.executeQuery();
            if(rs.next()){
                String sql1="insert into new_friend(sender,getter,sendtime,status,nickname,group) values(?,?,?,0,?,?);";
                PreparedStatement st1=conn.prepareStatement(sql1);
                st1.setString(1,app.from_id);
                st1.setString(2,app.to_id);
                st1.setString(3,message.getSendTime());
                st1.setString(4,app.to_name);
                st1.setString(4,app.group);
                st1.executeUpdate();

            }
            else {
                flag=1;
                Message msg=new Message();
                msg.setType(MessageType.MESSAGE_QICQ_ADD_FRIEND_FAIL_CANNOT_FIND_USER);
        //        MyObjectOutputStream oos=new MyObjectOutputStream(ManageServerToClientThread.getThread(id).getSocket().getOutputStream());
                ManageServerToClientThread.getThread(id).oos.writeObject(msg);
            }

        }
        if(flag==0){
            Message msg=new Message();
            msg.setType(MessageType.MESSAGE_QICQ_ADD_FRIEND_SUCCEED);
            ManageServerToClientThread.getThread(id).oos.writeObject(msg);
        }

    }

    /**
     * 列出我的申请
     *
     * @return {@link Message}
     * @throws SQLException sqlexception异常
     * @throws IOException  ioexception
     */
    public Message list_my_application() throws SQLException, IOException {
        ArrayList<Application>app=new ArrayList<>();
        String sql="select * from new_friend where sender=? order by sendtime";
        PreparedStatement st=conn.prepareStatement(sql);
        st.setString(1,id);
        ResultSet rs=st.executeQuery();
        while(rs.next()){
            Application a=new Application();
            a.status=rs.getInt("status");
            a.to_id=rs.getString("getter");
            app.add(a);
        }
        Message msg=new Message();
        msg.setData(app);
        msg.setType(MessageType.MESSAGE_QICQ_LIST_APPLICATION_RET);
     //   MyObjectOutputStream oos=new MyObjectOutputStream(ManageServerToClientThread.getThread(id).getSocket().getOutputStream());
      //  oos.writeObject(msg);
        rs.close();
        st.close();
        return msg;
    }

    /**
     * 列出我待处理的申请
     *
     * @throws SQLException sqlexception异常
     * @throws IOException  ioexception
     */
    public void list_my_application_handled() throws SQLException, IOException {
        ArrayList<Application>app=new ArrayList<>();
        String sql="select * from new_friend where getter=? order by sendtime DESC";
        PreparedStatement st=conn.prepareStatement(sql);
        st.setString(1,id);
        ResultSet rs=st.executeQuery();
        while(rs.next()){
            Application a=new Application();
            a.status=rs.getInt("status");
            a.from_id=rs.getString("sender");
            app.add(a);
        }
        Message msg=new Message();
        msg.setData(app);
        msg.setType(MessageType.MESSAGE_QICQ_LIST_APPLICATION_HANDLE_RET);
        ManageServerToClientThread.getThread(id).oos.writeObject(msg);
        rs.close();
        st.close();
    }

    /**
     * 管理员添加公告
     *
     * @param m 米
     * @throws SQLException sqlexception异常
     */
    public void admin_add_announcement(Message m) throws SQLException {
        String sql="insert into message(sender,getter,sendtime,content) values(?,'all',?,?);";
        PreparedStatement st=conn.prepareStatement(sql);
        st.setString(1,m.getSender());
        st.setString(2,m.getSendTime());
        st.setString(3,(String)m.getData());
        st.executeUpdate();
    }

    /**
     * 查看公告
     *
     * @return {@link Message}
     * @throws IOException  ioexception
     * @throws SQLException sqlexception异常
     */
    public Message list_announcement() throws IOException, SQLException {
        Message message=new Message();
        String sql="select * from message where getter='all' order by sendtime DESC;";
        PreparedStatement st=conn.prepareStatement(sql);
        ResultSet rs=st.executeQuery();
        ArrayList<Message>bulletin=new ArrayList<>();
        while(rs.next())
        {
            Message m=new Message();
            m.setData(rs.getString("content"));
            m.setSendTime(myTime.datetimeToString(rs.getTimestamp("sendtime")));
            bulletin.add(m);
        }
        message.setType(MessageType.MESSAGE_QICQ_GET_ANNOUNCEMENT_RET);
        message.setData(bulletin);
        Socket socket=ManageServerToClientThread.getThread(id).getSocket();
    //    ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
      //  oos.writeObject(message);
        return message;
    }

    /**
     * 接受新朋友
     *
     * @param application 申请
     * @throws SQLException sqlexception异常
     */
    public void accept_new_friend(Application application) throws SQLException {
        String sql="update new_friend set status=2 where sender=? and getter=? and status=0;";
        PreparedStatement st= conn.prepareStatement(sql);
        st.setString(1,application.to_id);
        st.setString(2,application.from_id);
        st.executeUpdate();
        sql="insert into friends(user_id,friend_id,relation,nickname) values(?,?,?,?);";
        st= conn.prepareStatement(sql);
        st.setString(1,application.from_id);
        st.setString(2,application.to_id);
        st.setString(3,application.group);
        st.setString(4,application.to_name);
        st.executeUpdate();
        sql="insert into friends(user_id,friend_id,relation,nickname) values(?,?,?,?);";
        st= conn.prepareStatement(sql);
        st.setString(1,application.to_id);
        st.setString(2,application.from_id);
        String sql2="select * from new_friend where sender=? and getter=?;";
        PreparedStatement st2=conn.prepareStatement(sql2);
        st2.setString(1,application.to_id);
        st2.setString(2,application.from_id);
        ResultSet rs=st2.executeQuery();
        st.setString(3,rs.getString("group"));
        st.setString(4,rs.getString("nickname"));
        st.executeUpdate();
        rs.close();
        st.close();
    }

    /**
     * 拒绝新朋友
     *
     * @param application 申请
     * @throws SQLException sqlexception异常
     */
    public void deny_new_friend(Application application) throws SQLException {
        String sql="update new_friend set status=1 where sender=? and getter=? and status=0;";
        PreparedStatement st= conn.prepareStatement(sql);
        st.setString(1,application.from_id);
        st.setString(2,application.to_id);
        st.executeUpdate();
    }

    /**
     * 我与好友的信息列表
     *
     * @param to_id 为id
     * @return {@link Message}
     * @throws IOException  ioexception
     * @throws SQLException sqlexception异常
     */
    public Message list_my_message_with(String to_id) throws IOException, SQLException {
        String sql="select * from message where (sender=? and getter=?) or (sender=? and getter=?) order by sendtime DESC;";
        PreparedStatement st= conn.prepareStatement(sql);
        st.setString(1,id);
        st.setString(2,to_id);
        st.setString(4,id);
        st.setString(3,to_id);
        ResultSet rs=st.executeQuery();
        ArrayList<Message>messages=new ArrayList<>();
        while(rs.next()){
            Message x=new Message();
            if(rs.getInt("isfile")==1)
            {
                Blob blob=(Blob) rs.getBlob("file");
                Filetrans filetrans=new Filetrans();
                filetrans.content=new byte[(int)blob.length()];
                InputStream is=blob.getBinaryStream();
                int len;
                int i=0;
                while((len=is.read(filetrans.content))!=-1) {
                    i++;
                }
        //        System.out.println(filetrans.getContent());
                filetrans.setName(rs.getString("filename"));
                x.isfile=1;
                x.setData(filetrans);
            }
            else {
                x.setData(rs.getString("content"));
                x.isfile=0;
            }
            x.setSender(rs.getString("sender"));
            x.setGetter(rs.getString("getter"));
            x.setSendTime(rs.getString("sendtime"));
            messages.add(x);
        }
        Message sendback=new Message();
        sendback.setType(MessageType.MESSAGE_QICQ_GET_MESSAGE_RET);
        sendback.setData(messages);
        sql="update message set isread=1 where (sender=? and getter=?);";
        st= conn.prepareStatement(sql);
        st.setString(1,to_id);
        st.setString(2,id);
        st.executeUpdate();
        rs.close();
        st.close();
        return sendback;
    }

    /**
     * 读取头像
     *
     * @param id id
     * @return {@link byte[]}
     * @throws SQLException sqlexception异常
     */
    public byte[] readicon(String id) throws SQLException {
       /* String path = "src/image/QQ/temp.jpg";
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBC_Connector.ConnectMySQL();
            String sql = "select * from qqimage where id =?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Blob picture= rs.getBlob("image");
                InputStream in = picture.getBinaryStream();
                int len = 0;
                byte[] buf = new byte[(int)picture.length()];
                while ((len = in.read(buf)) != -1) {
                   // out.write(buf, 0, len);
                }
         //       System.out.println("图片读取成功！");
            //    JDBC_Connector.close(rs,ps,conn);
                rs.close();
                ps.close();
                return buf;
            }
            else
            {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 修改朋友信息
     *
     * @param f f
     * @throws SQLException sqlexception异常
     */
    public void modify_friend_info(Friend f) throws SQLException {
        String sql="update friends set relation=?, nickname=? where user_id=? and friend_id=?;";
        PreparedStatement st=conn.prepareStatement(sql);
        st.setString(1,f.type);
        st.setString(2,f.name);
        st.setString(3,id);
        st.setString(4,f.id);
        st.executeUpdate();
        st.close();
    }

    /**
     * 新加朋友
     *
     * @param message 消息
     * @throws IOException  ioexception
     * @throws SQLException sqlexception异常
     */
    public void new_add_friend(Message message) throws IOException, SQLException {
        Application app=(Application)message.getData();
        int flag=0;
        String sql="select * from students where Student_idcard=?;";
        PreparedStatement st=conn.prepareStatement(sql);
        st.setString(1,app.to_id);
    //    System.out.println(app.to_id);
        ResultSet rs=st.executeQuery();
        if(rs.next()){
            String sql1="insert into friends(user_id,friend_id,relation,nickname) values(?,?,?,?);";
            PreparedStatement st1=conn.prepareStatement(sql1);
            st1.setString(1,app.from_id);
            st1.setString(2,app.to_id);
            st1.setString(3,app.group);
            st1.setString(4,app.to_name);
            st1.executeUpdate();
            st1.setString(2,app.from_id);
            st1.setString(1,app.to_id);
            st1.setString(3,"新朋友");
            st1.setString(4,app.from_name);
            st1.executeUpdate();
            st1.close();
        }
        else{
            sql="select * from teachers where Teacher_idcard=?;";
            st=conn.prepareStatement(sql);
            st.setString(1,app.to_id);
            rs=st.executeQuery();
            if(rs.next()){
                String sql1="insert into friends(user_id,friend_id,relation,nickname) values(?,?,?,?);";
                PreparedStatement st1=conn.prepareStatement(sql1);
                st1.setString(1,app.from_id);
                st1.setString(2,app.to_id);
                st1.setString(3,app.group);
                st1.setString(4,app.to_name);
                st1.executeUpdate();
                st1.setString(2,app.from_id);
                st1.setString(1,app.to_id);
                st1.setString(3,"新朋友");
                st1.setString(4,app.from_name);
                st1.executeUpdate();
                st1.close();
            }
            else {
                flag=1;
                Message msg=new Message();
                msg.setType(MessageType.MESSAGE_QICQ_ADD_FRIEND_FAIL_CANNOT_FIND_USER);
                //        MyObjectOutputStream oos=new MyObjectOutputStream(ManageServerToClientThread.getThread(id).getSocket().getOutputStream());
                ManageServerToClientThread.getThread(id).oos.writeObject(msg);
            }

        }
        if(flag==0){
            Message msg=new Message();
            msg.setType(MessageType.MESSAGE_QICQ_ADD_FRIEND_SUCCEED);
            ManageServerToClientThread.getThread(id).oos.writeObject(msg);
        }
        rs.close();
        st.close();
    }

}
