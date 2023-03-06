package UIhandler.QICQ;

import ClientToServer.ManageClientToServerThread;
import ClientToServer.myInfo;
import DAO.QICQ.Application;
import DAO.QICQ.Filetrans;
import DAO.QICQ.Friend;
import UIviewer.QQ.add_friend;
import UIviewer.QQ.chat_panel;
import UIviewer.QQ.friend_list;
import message.Message;
import message.MessageType;
import utils.MyObjectOutputStream;
import utils.myTime;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 客户端qicq
 *
 * @author Zhangyang_Ge
 * @date 2022/09/03
 */
public class Client_qicq {
    static String id;

    /**
     * 得到id
     *
     * @return {@link String}
     */
    public static String getId() {
        return id;
    }

    static MyObjectOutputStream oos=null;

    /**
     * 设置操作
     *
     * @param mos 金属氧化物半导体
     * @throws IOException ioexception
     */
    public static void setOps(MyObjectOutputStream mos) throws IOException {
        oos=mos;
    }

    /**
     * 组id
     *
     * @param id id
     */
    public static void setId(String id) {
        Client_qicq.id = id;
    }

    /**
     * 添加好友失败
     */
    public static void add_friend_fail() {
        add_friend.add_friend_fail();
    }

    /**
     * 添加好友成功
     */
    public static void add_friend_succeed() {
        add_friend.add_friend_succeed();
    }

    /**
     * 需要应用程序
     */
    public static void require_application(){
        Message message=new Message();
        message.setData(id);
        message.setType(MessageType.MESSAGE_QICQ_LIST_APPLICATION);
        try {
            oos.writeObject(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 修改
     *
     * @param friend 朋友
     */
    public static void modify(Friend friend){
        Message message=new Message();
        message.setData(friend);
        message.setType(MessageType.MESSAGE_QICQ_MODIFY);
        try {
            oos.writeObject(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 发送文件
     *
     * @param src      src
     * @param sender   发送方
     * @param getter   getter
     * @param filename 文件名
     */
    public static void send_file(String src, String sender, String getter, String filename){
       // System.out.println(5525);
        Message message=new Message();
        message.setSender(sender);
        message.setGetter(getter);
        message.setSendTime(myTime.getCurrentTime());
        message.setType(MessageType.MESSAGE_QICQ_SEND_FILE);
        FileInputStream fileInputStream=null;
        byte[] filebytes=new byte[(int)new File(src).length()];
        try {
            fileInputStream =new FileInputStream(src);
            fileInputStream.read(filebytes);
            Filetrans file=new Filetrans();
            file.setContent(filebytes);
            file.setName(filename);
            message.setData(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(fileInputStream!=null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 应用程序列表
     *
     * @param app 应用程序
     */
    public static void list_application(ArrayList<Application> app) {
        int num=app.size();
        for(int i=0;i<num;i++){

        }
    }

    /**
     * 接收文件
     *
     * @param file 文件
     * @param dest 桌子
     * @throws IOException ioexception
     */
    public static void receive_file(Filetrans file, String dest) throws IOException {
     //   System.out.println("receive");
        FileOutputStream fileOutputStream = new FileOutputStream(dest);
        fileOutputStream.write(file.getContent());
    }

    /**
     * 发送消息
     *
     * @param content 内容
     * @param sender  发送方
     * @param getter  getter
     */
    public static void send_message(String content, String sender, String getter){
        Message message=new Message();
        message.setSender(sender);
        message.setGetter(getter);
        message.setSendTime(myTime.getCurrentTime());
        message.setType(MessageType.MESSAGE_QICQ_SEND_MSG);
        message.setData(content);
        try {
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 得到消息
     *
     * @param id id
     * @throws IOException ioexception
     */
    public static void get_message(String id) throws IOException {
        Message message=new Message();
        message.setData(id);
        message.setType(MessageType.MESSAGE_QICQ_GET_MESSAGE);
        oos.writeObject(message);
    }

    /**
     * 显示消息
     *
     * @param messages 消息
     */
    public static void show_message(ArrayList<Message> messages){
        chat_panel.show_message(messages);
    }

    /**
     * 接收消息
     *
     * @param sender 发送方
     */
    public static void receive_message(String sender){
        friend_list.show_unread(sender);
    }

    /**
     * 添加公告
     *
     * @param sender  发送方
     * @param content 内容
     */
    public static void add_announcement(String sender,String content){
        Message message=new Message();
        message.setType(MessageType.MESSAGE_QICQ_ADD_ANNOUNCEMENT);
        message.setSender(sender);
        message.setSendTime(myTime.getCurrentTime());
        message.setData(content);
        try {
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 得到消息
     */
    public static void get_announcement(){
        Message message=new Message();
       // System.out.println(3);
        message.setType(MessageType.MESSAGE_QICQ_GET_ANNOUNCEMENT);
        try {
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加朋友
     *
     * @param myid     myid
     * @param group    集团
     * @param number   数量
     * @param nickname 昵称
     * @throws IOException ioexception
     */
    public static void add_friend(String myid, String group, String number, String nickname) throws IOException {
        Application app=new Application();
        Message message=new Message();
        message.setData(app);
        app.from_id=myid;
        app.from_name= myInfo.getName();
        app.group=group;
        app.setTo_id(number);
        app.setTo_name(nickname);
        message.setType(MessageType.MESSAGE_QICQ_ADD_FRIEND);
        oos.writeObject(message);
    }

    /**
     * 需要朋友列表
     *
     * @throws IOException ioexception
     */
    public static void Require_friend_list() throws IOException {
        Message message=new Message();
        message.setType(MessageType.MESSAGE_QICQ_LIST_FRIENDS);
        oos.writeObject(message);
    }

    /**
     * 我在线
     *
     * @throws IOException ioexception
     */
    public static void I_am_online() throws IOException{
        Message message=new Message();
        message.setData(id);
        message.setType(MessageType.MESSAGE_QICQ_FRIEND_ONLINE);
        oos.writeObject(message);
    }

    /**
     * 我离线
     *
     * @throws IOException ioexception
     */
    public static void I_am_offline() throws IOException{
        Message message=new Message();
        message.setData(id);
        message.setType(MessageType.MESSAGE_QICQ_FRIEND_OFFLINE);
        oos.writeObject(message);
    }

    /**
     * 给朋友
     *
     * @param friend 朋友
     */
    public static void show_friend(HashMap<String, ArrayList<Friend>> friend){
        friend_list.show_Friend(friend);
    }

    /**
     * 显示公告
     *
     * @param messages 消息
     */
    public static void Show_announcement(ArrayList<Message> messages) {
        chat_panel.show_announcement(messages);
    }
}
