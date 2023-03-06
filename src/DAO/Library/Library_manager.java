package DAO.Library;

import ServerToClient.ManageServerToClientThread;
import ServerToClient.ServerToClient;
import ServerToClient.ServerToClientThread;
import User.Admin;
import User.Student;
import User.Teacher;
import connection.JDBC_Connector;
import message.Message;
import message.MessageType;
import utils.myTime;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.io.Serializable;

/**
 * 图书馆管理类
 *@description 用于图书馆模块和数据库进行操作
 * @author Shuheng_Gu
 * @date 2022/09/03
 */
public class Library_manager implements Serializable{
    /**
     * 用户id
     */
    private String ID;
    /**
     * JDBC数据库链接
     */
    private static Connection conn;

    /**
     * 构造函数
     *
     * @param ID 用户id
     */
    public Library_manager(String ID){

        this.ID = ID;
        try {
            conn= JDBC_Connector.ConnectMySQL();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 登录信息
     *
     * @return {@link Message}
     * @throws SQLException sqlexception异常
     */
    public Message enter_info() throws SQLException {
        Message message=new Message();
        message.setType(MessageType.MESSAGE_LIBRARY_ENTER_RET);
        Info information=new Info();
        String sql="select * from students where Student_idcard=?;";
        PreparedStatement st=conn.prepareStatement(sql);
        st.setString(1,ID);
        ResultSet rs=st.executeQuery();
        if(rs.next()){
            information.id=rs.getString("Student_idcard");
            information.name=rs.getString("Student_name");
            message.setData(information);
        }
        else {
            sql="select * from teachers where Teacher_idcard=?;";
            st=conn.prepareStatement(sql);
            st.setString(1,ID);
            rs=st.executeQuery();
            if(rs.next()) {
                information.id=rs.getString("Teacher_idcard");
                information.name=rs.getString("Teacher_name");
                message.setData(information);
            }
            else{
                sql="select * from teachers where Teacher_idcard=?;";
                st=conn.prepareStatement(sql);
                st.setString(1,ID);
                rs=st.executeQuery();
                if(rs.next()){
                    information.id=rs.getString("Admin_idcard");
                    information.name=rs.getString("Admin_name");
                    message.setData(information);
                }
            }
        }
        rs.close();
        st.close();
        return message;
    }

    /**
     * 列出我借阅的书
     *
     * @return {@link ArrayList}<{@link Book_borrower}>
     * @throws SQLException sqlexception异常
     */
    public ArrayList<Book_borrower> list_my_book() throws SQLException {
        ArrayList<Book_borrower> books = new ArrayList<Book_borrower>();
        String sql="select * from library  where borrow_to=? order by expire_date;";
        PreparedStatement st=conn.prepareStatement(sql);
        st.setString(1,ID);
        ResultSet rs=st.executeQuery();
        while(rs.next())
        {
            Book_borrower x=new Book_borrower();
            x.borrow_to=ID;
            x.id=rs.getString("id");;
            x.name=rs.getString("name");
            x.author=rs.getString("author");
            x.country=rs.getString("country");
            x.publisher=rs.getString("publisher");
            x.date_borrow=myTime.dateToString(rs.getDate("borrow_date"));
            x.date_expire=myTime.dateToString(rs.getDate("expire_date"));
            books.add(x);
        }
        rs.close();
        st.close();
        return books;
    }

    /**
     * 查询书
     *
     * @param s 年代
     * @return {@link ArrayList}<{@link Book_borrower}>
     * @throws SQLException sqlexception异常
     */
    public ArrayList<Book_borrower> query_book(String s) throws SQLException {
        ArrayList<Book_borrower> books = new ArrayList<>();
        String sql="select * from library where name like ? or author like ? or id like ? order by name;";
        String parse="%"+s+"%";
        PreparedStatement st=conn.prepareStatement(sql);
        st.setString(1,parse);
        st.setString(2,parse);
        st.setString(3,parse);
        ResultSet rs=st.executeQuery();
        while(rs.next())
        {
            Book_borrower x=new Book_borrower();
            x.id=rs.getString("ID");
            x.name=rs.getString("name");
            x.author=rs.getString("author");
            x.available=rs.getInt("available");
            x.publisher=rs.getString("publisher");
            x.place=rs.getString("place");
            x.country=rs.getString("country");
            x.available=rs.getInt("available");
            if(x.available==0){
                x.date_expire=myTime.dateToString(rs.getDate("expire_date"));
            }
            books.add(x);
        }
        rs.close();
        st.close();
        return books;
    }

    /**
     * 列出所有书
     *
     * @param s 年代
     * @return {@link ArrayList}<{@link Book_admin}>
     * @throws SQLException sqlexception异常
     */
    public ArrayList<Book_admin> list_all_book(String s) throws SQLException {
        ArrayList<Book_admin> books = new ArrayList<Book_admin>();
        String sql="select * from library where id like ? or name like ? or author like ? order by name;";
        String parse="%"+s+"%";
        PreparedStatement st=conn.prepareStatement(sql);
        st.setString(1,parse);
        st.setString(2,parse);
        st.setString(3,parse);
        ResultSet rs=st.executeQuery();
        while(rs.next())
        {
            Book_admin x=new Book_admin();
            x.borrow_to=ID;
            x.name=rs.getString("name");
            x.author=rs.getString("author");
            x.borrow_to=rs.getString("borrow_to");
            x.price=rs.getDouble("price");
            x.ID=rs.getString("ID");
            x.place=rs.getString("place");
            x.publisher=rs.getString("publisher");
            x.country=rs.getString("country");
            x.available=rs.getInt("available");
            if(x.available==0){
                x.date_borrow=myTime.dateToString(rs.getDate("borrow_date"));
                x.date_expire=myTime.dateToString(rs.getDate("expire_date"));
            }
            books.add(x);
        }
        rs.close();
        st.close();
        return books;
    }

    /**
     * 借书
     *
     * @param b b
     * @return {@link Message}
     * @throws SQLException sqlexception异常
     */
    public Message borrow(Book_borrower b) throws SQLException {
        String sql="select * from library where borrow_to =?;";
        PreparedStatement st=conn.prepareStatement(sql);
        st.setString(1,ID);
        ResultSet rs=st.executeQuery();
        Date today=new Date();
        Message msg=new Message();
        int cnt=0;
        int flag=1;
        while(rs.next())
        {
            cnt++;
            if(rs.getDate("expire_date").compareTo(today)==-1)
            {
                msg.setType(MessageType.MESSAGE_LIBRARY_BORROW_FAIL_RETURN_FIRST);
                flag=0;
            }
        }
        if(cnt==5)
        {
            msg.setType(MessageType.MESSAGE_LIBRARY_BORROW_FAIL_TOO_MANY);
            flag=0;
        }
        if(flag==1)
        {
            msg.setType(MessageType.MESSAGE_LIBRARY_BORROW_SUCCEED);
            sql="update library set available=0, borrow_date=?, expire_date=?, borrow_to=? where id= ?;";
            st=conn.prepareStatement(sql);
            st.setString(1,myTime.dateToString(today));
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(today);
            rightNow.add(Calendar.DAY_OF_YEAR,30);//日期加30天
            Date expire=rightNow.getTime();
            st.setString(2, myTime.dateToString(expire));
            st.setString(3,ID);
            st.setString(4,b.getId());
            st.executeUpdate();
        }
        rs.close();
        st.close();
        return msg;
    }

    /**
     * 还书
     *
     * @param b b
     * @return {@link Message}
     * @throws SQLException sqlexception异常
     */
    public Message ret(Book_borrower b) throws SQLException{

        String sql;
        Message msg=new Message();
        sql="select expire_date from library where id=?;";
        PreparedStatement st=conn.prepareStatement(sql);
        st.setString(1,b.id);
        ResultSet rs=st.executeQuery();
        if(rs.next()){
            Date today=new Date();
//            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            if(rs.getDate("expire_date").compareTo(today)==-1){
                msg.setType(MessageType.MESSAGE_LIBRARY_RET_LATE);
            }else{
                msg.setType(MessageType.MESSAGE_LIBRARY_RET_SUCCEED);
            }
        }
        String sql1="update library set available = 1,borrow_date = null,expire_date = null," +
                "borrow_to = null, extended = 0 where id=?;";
        PreparedStatement st1=conn.prepareStatement(sql1);
        st1.setString(1,b.id);
        int affect=st1.executeUpdate();
        rs.close();
        st.close();
        return msg;
    }


     /* public void handle(Punishment punishment) throws SQLException, IOException {
        Iterator it=ServerToClient.getPunish().iterator();
        while(it.hasNext()){
            Punishment p=(Punishment)it.next();
            if(punishment.Book_id.equals(p.Book_id)&&punishment.Customer_iD.equals(p.Customer_iD)) {
                ServerToClient.getPunish().remove(p);
                p.status=1;
                ServerToClient.getPunish().add(p);
                return;
            }
        }
    }*/

    /**
     * 延长借阅
     *
     * @param b 书
     * @return {@link Message}
     * @throws SQLException   sqlexception异常
     * @throws ParseException 解析异常
     */
    public Message extend(Book_borrower b) throws SQLException, ParseException {
        String sql="select * from library where id=?;";
        PreparedStatement st=conn.prepareStatement(sql);
        st.setString(1,b.id);
        ResultSet rs=st.executeQuery();
        Message message=new Message();
        while(rs.next()){
            if(rs.getInt("extended")==1)
            {
                message.setType(MessageType.MESSAGE_LIBRARY_EXTEND_FAIL);
                return message;
            }
            String ex=myTime.dateToString(rs.getDate("expire_date"));
            String bookid=rs.getString("id");
            //     System.out.println("original "+ex);
            Date next=new SimpleDateFormat("yyyy-MM-dd").parse(ex);
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(next);
            rightNow.add(Calendar.DAY_OF_YEAR,30);//日期加30天
            Date new_expire=rightNow.getTime();
            //     System.out.println(myTime.dateToString(new_expire));
            sql="update library set expire_date=?, extended=1 where id=?";
            st=conn.prepareStatement(sql);
            st.setString(1,myTime.dateToString(new_expire));
            st.setString(2,bookid);
            st.executeUpdate();
        }
        message.setType(MessageType.MESSAGE_LIBRARY_EXTEND_SUCCEED);
        rs.close();
        st.close();
        return message;
    }

    /**
     * 申请
     *
     * @param b b
     * @return {@link Punishment}
     * @throws SQLException sqlexception异常
     */
    public Punishment apply(Book_borrower b) throws SQLException {
        Punishment pp=new Punishment();
        String sql="select * from library where name=? and author=? and borrow_to=?;";
        PreparedStatement st=conn.prepareStatement(sql);
        st.setString(1,b.name);
        st.setString(2,b.author);
        st.setString(3,ID);
        ResultSet rs=st.executeQuery();
        while (rs.next())
        {
            pp.Book_id=rs.getString("ID");
            pp.Customer_iD= ID;
            pp.price=rs.getDouble("price");
        }
        return pp;
    }

    /**
     * 支付罚单
     *
     * @param p p
     * @return {@link Message}
     * @throws SQLException sqlexception异常
     */
    public Message pay(Punishment p) throws SQLException{
        Message msg=new Message();
        String sql="select * from students where Student_idcard=?;";
        PreparedStatement st=conn.prepareStatement(sql);
        st.setString(1,p.Customer_iD);
        //    System.out.println("punish  "+p.Customer_iD);
        ResultSet rs=st.executeQuery();
        if(rs.next()){
            double curmoney=rs.getDouble("Student_money")-p.price;
            if(curmoney>=0){
                sql="update students set Student_money=? where Student_idcard=?;";
                st=conn.prepareStatement(sql);
                st.setDouble(1,curmoney);
                st.setString(2,p.Customer_iD);
                st.executeUpdate();
                sql="update ticket set status=1 where id=?";
                st=conn.prepareStatement(sql);
                st.setString(1,p.punishmentID);
                st.executeUpdate();
                msg.setType(MessageType.MESSAGE_LIBRARY_PAY_SUCCEED);
            }
            else {
                msg.setType(MessageType.MESSAGE_LIBRARY_PAY_FAIL);
            }
        }
        else {
            sql="select * from teachers where Teacher_idcard=?";
            st=conn.prepareStatement(sql);
            st.setString(1,p.Customer_iD);
            rs=st.executeQuery();
            rs.next();
            double curmoney=rs.getDouble("Teacher_money")-p.price;
            if(curmoney>=0){
                sql="update teachers set Teacher_money=? where Teacher_idcard=?;";
                st=conn.prepareStatement(sql);
                st.setDouble(1,curmoney);
                st.setString(2,p.Customer_iD);
                st.executeUpdate();
                sql="update ticket set status=1 where id=?";
                st=conn.prepareStatement(sql);
                st.setString(1,p.punishmentID);
                st.executeUpdate();
                msg.setType(MessageType.MESSAGE_LIBRARY_PAY_SUCCEED);
            }
            else {
                msg.setType(MessageType.MESSAGE_LIBRARY_PAY_FAIL);
            }
        }
        return msg;
    }

    /**
     * 添加书籍
     *
     * @param book 书
     * @throws SQLException sqlexception异常
     */
    public void addbook(Book_admin book) throws SQLException{
        String sql="insert into library(name,author,ID,place,price,publisher,country,available,extended) values(?,?,?,?,?,?,?,1,0);";
        PreparedStatement st=conn.prepareStatement(sql);
        st.setString(1,book.name);
        st.setString(2,book.author);
        st.setString(3,book.ID);
        st.setString(4,book.place);
        st.setString(6,book.publisher);
        st.setString(7,book.country);
        st.setDouble(5,book.price);
        st.executeUpdate();
    }

    /**
     * 删除书籍
     *
     * @param id id
     * @return {@link Message}
     * @throws SQLException sqlexception异常
     */
    public Message deletebook(String id) throws SQLException{
        Message message=new Message();
        String sql="delete from library where ID=?;";
        PreparedStatement st=conn.prepareStatement(sql);
        st.setString(1,id);
        int cnt=st.executeUpdate();
        if(cnt==1){
            message.setType(MessageType.MESSAGE_LIBRARY_ADMIN_DELETE_SUCCEED);
        }else{
            message.setType(MessageType.MESSAGE_LIBRARY_ADMIN_DELETE_FAIL);
        }
        st.close();
        return message;
    }
    /* public ArrayList<Punishment>admin_list_tickets(){
        ArrayList<Punishment>punishments=new ArrayList<>();
        Iterator it=ServerToClient.getPunish().iterator();
        while(it.hasNext()){
            Punishment p=(Punishment)it.next();
            if(p.status==0) punishments.add(p);
        }
        return punishments;
    }*/
    /**
     * 列出我的罚单
     *
     * @return {@link ArrayList}<{@link Punishment}>
     * @throws SQLException sqlexception异常
     */
    public ArrayList<Punishment>list_my_tickets() throws SQLException {
        String sql="select * from ticket where customer=? and status=0 order by id+0 ;";
        PreparedStatement st=conn.prepareStatement(sql);
        st.setString(1,ID);
        ResultSet rs=st.executeQuery();
        ArrayList<Punishment>punishments=new ArrayList<>();
        while(rs.next()){
            Punishment p = new Punishment();
            p.status=rs.getInt("status");
            p.notice=rs.getString("notice");
            p.price=rs.getDouble("price");
            p.punishmentID=rs.getString("id");
            p.setBook_id(rs.getString("book"));
            p.Customer_iD=ID;
            punishments.add(p);
        }
        rs.close();
        st.close();
        return punishments;
    }

    /**
     * 开罚单
     *
     * @param p p
     * @throws SQLException sqlexception异常
     */
    public void admin_give_ticket(Punishment p) throws SQLException {
        String sql="insert into ticket(id,customer,notice,book,price,status) values(?,?,?,?,?,0);";
        PreparedStatement st=conn.prepareStatement(sql);
        st.setString(1,p.punishmentID);
        st.setString(2,p.Customer_iD);
        st.setString(3,p.notice);
        st.setString(4,p.Book_id);
        st.setDouble(5,p.price);
        st.executeUpdate();
        st.close();
    }

}
