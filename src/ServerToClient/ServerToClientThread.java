package ServerToClient;

import DAO.Curriculum.Course;
import DAO.Curriculum.Course_manager;
import DAO.Curriculum.Opencourse;
import DAO.Library.Book_admin;
import DAO.Library.Book_borrower;
import DAO.Library.Library_manager;
import DAO.Library.Punishment;
import DAO.QICQ.Application;
import DAO.QICQ.Friend;
import DAO.QICQ.QICQ_manager;
import DAO.Shop.Admin_Shop_utils;
import DAO.Shop.Product;
import DAO.Shop.ProductPair;
import DAO.Shop.buyers_Shop_utils;
import DAO.StatusManagement.Admin_SM_utils;
import DAO.StatusManagement.ImageAndTable;
import DAO.StatusManagement.User_SM_utils;
import User.Student;
import message.Message;
import message.MessageType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import DAO.StatusManagement.Image_SM_utils;
import utils.MyObjectInputStream;
import utils.MyObjectOutputStream;

/**
 * 服务器客户端线程
 *
 * @author 28468
 * @version : [v1.0]
 * @description : [该类的一个对象和某个客户端保持通信]
 * @createTime : [2022.08.14 21:05]
 * @date 2022/09/04
 */
public class ServerToClientThread extends Thread{
    /**
     * 套接字
     */
    private Socket socket;
    /**
     * 用户标识
     */
    private String userid;//连接到服务器的用户id

    /**
     * 服务器客户端线程
     *
     * @param mos    金属氧化物半导体
     * @param mis    管理信息系统
     * @param userid 用户标识
     * @param s      年代
     */
    public ServerToClientThread(MyObjectOutputStream mos,MyObjectInputStream mis,String userid,Socket s){
        this.oos=mos;
        this.ois=mis;
        this.userid=userid;
        socket=s;
    }

    /**
     * oos
     */
    public MyObjectOutputStream oos;
    /**
     * ois
     */
    public MyObjectInputStream ois;

    /**
     * 获取套接字
     *
     * @return {@link Socket}
     */
    public Socket getSocket() {
        return socket;
    }

    /**
     * 设置套接字
     *
     * @param socket 套接字
     */
    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    /**
     * 获取用户标识
     *
     * @return {@link String}
     */
    public String getUserid() {
        return userid;
    }

    /**
     * 设置用户名
     *
     * @param userid 用户标识
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * 运行
     */
    public void run(){
        while (true){
        //    System.out.println("客户端和服务端 "+userid+" 保持通信，读取数据...");
            try {
                Message m = (Message) ois.readObject();
                Message sendback = new Message();
            //    System.out.println(m.getType());

                //退出系统
                if(m.getType().equals(MessageType.MESSAGE_CLIENT_EXIT)){
               //     System.out.println(m.getSender()+"退出系统");
                    ServerToClient.removeOnline(userid);
                    ManageServerToClientThread.removeServerToClientThread(m.getSender());
                    break;
                }
                //图书馆
                if(m.getType().equals( MessageType.MESSAGE_LIBRARY_ENTER)){
                    if(ServerToClient.isOnline(userid)==2){
                        sendback.setData(new Library_manager(userid).list_all_book(""));
                        sendback.setType(MessageType.MESSAGE_LIBRARY_ENTER_RET);
                        oos.writeObject(sendback);
                    }
                }
                else if(m.getType().equals(MessageType.MESSAGE_LIBRARY_BORROW))
                {
                    Book_borrower book=(Book_borrower)m.getData();
                    Library_manager lib_manager = new Library_manager(userid);
                    oos.writeObject(lib_manager.borrow(book));
                }
                else if(m.getType().equals(MessageType.MESSAGE_LIBRARY_RET))
                {
                    Book_borrower book=(Book_borrower)m.getData();
                    Library_manager lib_manager = new Library_manager(userid);
                    sendback=lib_manager.ret(book);
                    oos.writeObject(sendback);
                }
                else if(m.getType().equals( MessageType.MESSAGE_LIBRARY_QUERY))
                {
                    String s=(String)m.getData();
                    Library_manager lib_manager = new Library_manager(userid);
                    sendback.setData(lib_manager.query_book(s));
                    sendback.setType(MessageType.MESSAGE_LIBRARY_QUERY_RET);
                    oos.writeObject(sendback);
                }
                else if(m.getType().equals( MessageType.MESSAGE_LIBRARY_EXTEND)){
                    Book_borrower b=(Book_borrower)m.getData();
                    sendback=new Library_manager(userid).extend(b);
                    oos.writeObject(sendback);
                }
                else if(m.getType().equals(MessageType.MESSAGE_LIBRARY_LIST_MY_BOOK)){
                    sendback.setData(new Library_manager(userid).list_my_book());
                    sendback.setType(MessageType.MESSAGE_LIBRARY_LIST_MY_BOOK_RET);
                    oos.writeObject(sendback);
                }
                else if(m.getType().equals( MessageType.MESSAGE_LIBRARY_ADMIN_LIST))
                {
                    Library_manager lib_manager = new Library_manager(userid);
                    sendback.setData(lib_manager.list_all_book(""));
                    sendback.setType(MessageType.MESSAGE_LIBRARY_ADMIN_LIST_RET);
                    oos.writeObject(sendback);
                }
                else if (m.getType().equals( MessageType.MESSAGE_LIBRARY_ADMIN_QUERY)) {
                    sendback.setData(new Library_manager(userid).list_all_book((String)m.getData()));
                    sendback.setType(MessageType.MESSAGE_LIBRARY_ADMIN_QUERY_RET);
                    oos.writeObject(sendback);
                }
                /*else if(m.getType().equals(MessageType.MESSAGE_LIBRARY_ADMIN_LIST_TICKETS)){
                    sendback.setData(new Library_manager(userid).admin_list_tickets());
                    sendback.setType(MessageType.MESSAGE_LIBRARY_ADMIN_LIST_TICKETS_RET);
                    oos.writeObject(sendback);
                }
                else if(m.getType().equals( MessageType.MESSAGE_LIBRARY_ADMIN_HANDLE))
                {
                    Punishment p=(Punishment) m.getData();
                    new Library_manager(userid).handle(p);
                }*/
                else if(m.getType().equals(MessageType.MESSAGE_LIBRARY_ADMIN_GIVE_TICKET)){
                    new Library_manager(userid).admin_give_ticket((Punishment)m.getData());
                }
                else if(m.getType().equals(MessageType.MESSAGE_LIBRARY_LIST_MY_TICKET)){
                    sendback.setData(new Library_manager(userid).list_my_tickets());
                    sendback.setType(MessageType.MESSAGE_LIBRARY_LIST_MY_TICKET_RET);
                    oos.writeObject(sendback);
                }
                else if(m.getType().equals( MessageType.MESSAGE_LIBRARY_PAY)){
                    Punishment p=(Punishment) m.getData();
                    sendback=(new Library_manager(userid).pay(p));
                    oos.writeObject(sendback);
                }
                else if(m.getType().equals( MessageType.MESSAGE_LIBRARY_ADMIN_ADD)){
                    Book_admin book=(Book_admin)m.getData();
                    new Library_manager(userid).addbook(book);
                }
                else if(m.getType().equals( MessageType.MESSAGE_LIBRARY_ADMIN_DELETE)){
                    String id=(String)m.getData();
                    sendback=new Library_manager(userid).deletebook(id);
                    oos.writeObject(sendback);
                }


                //选课
                if(m.getType().equals(MessageType.MESSAGE_CURRICULUM_LIST_ALL)){
                    sendback.setData(new Course_manager(userid).list_all_courses());
                    sendback.setType(MessageType.MESSAGE_CURRICULUM_LIST_ALL_RET);
                    oos.writeObject(sendback);
                }
                else if(m.getType().equals(MessageType.MESSAGE_CURRICULUM_MY_CHOOSING)){
                    sendback.setData(new Course_manager(userid).list_I_can_choose());
                    sendback.setType(MessageType.MESSAGE_CURRICULUM_MY_CHOOSING_RET);
                    oos.writeObject(sendback);
                }
                else if (m.getType().equals(MessageType.MESSAGE_CURRICULUM_LIST_MINE)) {
                    sendback.setData(new Course_manager(userid).list_my_courses());
                    sendback.setType(MessageType.MESSAGE_CURRICULUM_LIST_MINE_RET);
                    oos.writeObject(sendback);
                }
                else if(m.getType().equals(MessageType.MESSAGE_CURRICULUM_CHOOSE)){
                    Course c=(Course)m.getData();
                    sendback=new Course_manager(userid).choose(c);
                    oos.writeObject(sendback);
                }
                else if(m.getType().equals(MessageType.MESSAGE_CURRICULUM_APPLY)){
                    sendback=new Course_manager(userid).apply((Opencourse)m.getData());
                    oos.writeObject(sendback);
                }
                else if(m.getType().equals(MessageType.MESSAGE_CURRICULUM_SHOW_STU)) {
                    sendback.setData(new Course_manager(userid).get_student((String) m.getData()));
                    sendback.setType(MessageType.MESSAGE_CURRICULUM_SHOW_STU_RET);
                    oos.writeObject(sendback);
                }
                else if(m.getType().equals(MessageType.MESSAGE_CURRICULUM_SHOW_SCHEDULE)){
                    sendback.setData(new Course_manager(userid).schedule());
                    sendback.setType(MessageType.MESSAGE_CURRICULUM_SHOW_SCHEDULE_RET);
                    oos.writeObject(sendback);
                }
                else if(m.getType().equals(MessageType.MESSAGE_CURRICULUM_DELETE)){
                    new Course_manager(userid).delete((String) m.getData());
                }
                else if(m.getType().equals(MessageType.MESSAGE_CURRICULUM_LIST_APPLICATION)){
                    sendback.setData(new Course_manager(userid).list_tea_opencourse((String) m.getData()));
                    sendback.setType(MessageType.MESSAGE_CURRICULUM_LIST_APPLICATION_RET);
                    oos.writeObject(sendback);
                }
                else if (m.getType().equals(MessageType.MESSAGE_CURRICULUM_QUERY)) {
                    String q=(String)m.getData();
                    sendback.setData(new Course_manager(userid).query_courses(q));
                    sendback.setType(MessageType.MESSAGE_CURRICULUM_QUERY_RET);
                    oos.writeObject(sendback);
                }
                else if(m.getType().equals(MessageType.MESSAGE_CURRICULUM_LIST_ADMIN_APPLICATION)){
                    sendback=new Course_manager(userid).admin_list_application();
                    oos.writeObject(sendback);
                }
                else if(m.getType().equals(MessageType.MESSAGE_CURRICULUM_APPLICATION_REFUSE)){
                    String reason=(String) m.getData();
                    new Course_manager(userid).refuse(m.getGetter(),reason);
                }
                else if(m.getType().equals(MessageType.MESSAGE_CURRICULUM_APPLICATION_APPROVE)){
                    Course c=(Course) m.getData();
                    new Course_manager(userid).approve(c);
                }
                else if(m.getType().equals(MessageType.MESSAGE_CURRICULUM_ADMIN_ARRANGEMENT)){
                    Course c=(Course) m.getData();
                    new Course_manager(userid).admin_arrange(c);
                }
                else if(m.getType().equals(MessageType.MESSAGE_CURRICULUM_DROP)){
                    new Course_manager(userid).drop((String)m.getData());
                }
                else if(m.getType().equals(MessageType.MESSAGE_CURRICULUM_LIST_TEACHER_COURSE)){
                    sendback.setData(new Course_manager(userid).list_tea_course());
                    sendback.setType(MessageType.MESSAGE_CURRICULUM_LIST_TEACHER_COURSE_RET);
                    oos.writeObject(sendback);
                }
                else if(m.getType().equals(MessageType.MESSAGE_CURRICULUM_TEACHER_SCHEDULE)){
                    sendback.setData(new Course_manager(userid).list_tea_schedule());
                    sendback.setType(MessageType.MESSAGE_CURRICULUM_SHOW_SCHEDULE_RET);
                    oos.writeObject(sendback);
                }
                //站内通信
                if(m.getType().equals(MessageType.MESSAGE_QICQ_SEND_MSG)){
                     String getter=m.getGetter();
                     if(ServerToClient.isOnline(getter)!=-1){
                         new QICQ_manager(userid).send_online_message(m);

                     }
                     else {
                         new QICQ_manager(userid).send_offline_message(m);
                     }
                }
                else if(m.getType().equals(MessageType.MESSAGE_QICQ_SEND_FILE)){
               //     System.out.println("123456");
                    String getter=m.getGetter();
                    if(ServerToClient.isOnline(getter)!=-1){
                        new QICQ_manager(userid).send_online_file(m);

                    }
                    else {
                        new QICQ_manager(userid).send_offline_file(m);
                    }
                }
                else if(m.getType().equals(MessageType.MESSAGE_QICQ_GET_ANNOUNCEMENT)){
                    sendback=new QICQ_manager(userid).list_announcement();
                    oos.writeObject(sendback);
                }
                else if(m.getType().equals(MessageType.MESSAGE_QICQ_ADD_ANNOUNCEMENT)){
                    new QICQ_manager(userid).admin_add_announcement(m);
                }
                else if(m.getType().equals(MessageType.MESSAGE_QICQ_ADD_FRIEND)){
                    //new QICQ_manager(userid).add_friend(m);
                    new QICQ_manager(userid).new_add_friend(m);
                }
                else if(m.getType().equals(MessageType.MESSAGE_QICQ_LIST_APPLICATION)){
                    sendback=new QICQ_manager(userid).list_my_application();
                    oos.writeObject(sendback);
                }
                else if(m.getType().equals(MessageType.MESSAGE_QICQ_LIST_FRIENDS)){
                    sendback=new QICQ_manager(userid).get_friends();
                    oos.writeObject(sendback);
                }
                else if(m.getType().equals(MessageType.MESSAGE_QICQ_LIST_APPLICATION_HANDLE)){
                    new QICQ_manager(userid).list_my_application_handled();
                }
                else if(m.getType().equals(MessageType.MESSAGE_QICQ_GET_MESSAGE)){
                    sendback=new QICQ_manager(userid).list_my_message_with((String)m.getData());
                    oos.writeObject(sendback);
                }
                else if(m.getType().equals(MessageType.MESSAGE_QICQ_ACCEPT_NEW_FRIEND)){
                    new QICQ_manager(userid).accept_new_friend((Application)m.getData());
                }
                else if(m.getType().equals(MessageType.MESSAGE_QICQ_DENY_NEW_FRIEND)){
                    new QICQ_manager(userid).deny_new_friend((Application)m.getData());
                }
                else if(m.getType().equals(MessageType.MESSAGE_QICQ_FRIEND_ONLINE)){
                    new QICQ_manager(userid).friend_is_online((String)m.getData());
                }
                else if(m.getType().equals(MessageType.MESSAGE_QICQ_FRIEND_OFFLINE)){
                    new QICQ_manager(userid).friend_is_offline((String)m.getData());
                }
                else if(m.getType().equals(MessageType.MESSAGE_QICQ_MODIFY)){
                    new QICQ_manager(userid).modify_friend_info((Friend)m.getData());
                    sendback.setType(MessageType.MESSAGE_QICQ_MODIFY_RET);
                    oos.writeObject(sendback);
                }
                //商店
                else if(m.getType().equals(MessageType.RETURN_ALL_PRODUCT)){
                    List<Product> ps  = buyers_Shop_utils.returnAllProduct();
                    if(ps.size()!=0){
                    //    System.out.println("!!="+ps.size());
                        sendback.setData(ps);
                        sendback.setType(MessageType.RETURN_ALL_PRODUCT_SUCCEED);
                        oos.writeObject(sendback);
                    } else{
                        sendback.setType(MessageType.RETURN_ALL_PRODUCT_FAILED);
                        oos.writeObject(sendback);
                        //socket.close();
                    }
                }
                else if(m.getType().equals(MessageType.FIND_PRODUCT)){
                    List<Product> ps  = buyers_Shop_utils.checkProduct(m.getSender());
                    if(ps.size()!=0){
                        sendback.setData(ps);
                        sendback.setType(MessageType.FIND_PRODUCT_SUCCEED);
                        oos.writeObject(sendback);
                    } else{
                        sendback.setType(MessageType.FIND_PRODUCT_FAILED);
                        oos.writeObject(sendback);
                        //socket.close();
                    }
                }
                else if(m.getType().equals(MessageType.CHECK_CERTAIN_PRODUCT)){
                    Product ps  = buyers_Shop_utils.checkCertainProduct(m.getCode());
                    if(ps!=null){
                    //    System.out.println(ps.getProduct_name());
                        sendback.setData(ps);
                        sendback.setType(MessageType.CHECK_CERTAIN__PRODUCT_SUCCEED);
                        oos.writeObject(sendback);
                    } else{
                        sendback.setType(MessageType.CHECK_CERTAIN__PRODUCT_FAILED);
                        oos.writeObject(sendback);
                        //socket.close();
                    }
                }
                else if(m.getType().equals(MessageType.CHECK_BUYED_PRODUCT)){
                    List<ProductPair> s = buyers_Shop_utils.getBuyedandNum(m.getSender());
                    if(s.size()!=0){
                        sendback.setData(s);
                        sendback.setType(MessageType.CHECK_BUYED_PRODUCT_SUCCEED);
                        oos.writeObject(sendback);
                    } else{
                        sendback.setType(MessageType.CHECK_BUYED_PRODUCT_FAILED);  //相当于为0
                        oos.writeObject(sendback);
                        //socket.close();
                    }
                }
                else if(m.getType().equals(MessageType.CHECK_READYTOBUY_PRODUCT)){
                    List<ProductPair> s = buyers_Shop_utils.getReadytoBuyandNum(m.getSender());
                    if(s.size()!=0){
                        sendback.setData(s);
                        sendback.setType(MessageType.CHECK_READYTOBUY_PRODUCT_SUCCEED);
                        oos.writeObject(sendback);
                    } else{
                        sendback.setType(MessageType.CHECK_READYTOBUY_PRODUCT_FAILED);  //相当于为0
                        oos.writeObject(sendback);
                        //socket.close();
                    }
                }

                else if(m.getType().equals(MessageType.DELETE_READYTPBUY_PRODUCT)){
                    String idcard = m.getSender();
                    ProductPair s = (ProductPair) m.getData();
                    boolean b = buyers_Shop_utils.deleteShopCar(idcard,s.getId(),s.getNum());
                    if(b){
                        sendback.setType(MessageType.DELETE_READYTPBUY_PRODUCT_SUCCEED);
                        oos.writeObject(sendback);
                    } else{
                        sendback.setType(MessageType.DELETE_READYTPBUY_PRODUCT_FAILED);  //相当于为0
                        oos.writeObject(sendback);
//                        socket.close();
                    }
                }

                else if(m.getType().equals(MessageType.ADD_TO_SHOPCAR)){
                    String idcard = m.getSender();
                    ProductPair s = (ProductPair) m.getData();
                    boolean b = buyers_Shop_utils.addToShopCar(idcard,s.getId(),s.getNum());
                    if(b){
                        sendback.setType(MessageType.ADD_TO_SHOPCAR_SUCCEED);
                        oos.writeObject(sendback);
                    } else{
                        sendback.setType(MessageType.ADD_TO_SHOPCAR_FAILED);
                        oos.writeObject(sendback);
//                        socket.close();
                    }
                }

                else if(m.getType().equals(MessageType.GET_MONEY)){
                    String idcard = m.getSender();
                    double b = buyers_Shop_utils.getMoney(idcard);
                    sendback.setType(MessageType.GET_MONEY_SUCCEED);
                    sendback.setData(b);
                    oos.writeObject(sendback);
                }

                else if(m.getType().equals(MessageType.GET_MONEY_TEACHER)){
                    String idcard = m.getSender();
                    System.out.println("2222@");
                    double b = buyers_Shop_utils.getMoney_teacher(idcard);
                    sendback.setType(MessageType.GET_MONEY_TEACHER_SUCCEED);
                    System.out.println("1111@");
                    sendback.setData(b);
                    oos.writeObject(sendback);
                }

                else if(m.getType().equals(MessageType.DELETE_PRODUCT)){
                    if(Admin_Shop_utils.deleteProduct(Integer.parseInt(m.getSender()))){
                        sendback.setType(MessageType.DELETE_PRODUCT_SUCCEED);
                        oos.writeObject(sendback);
                    } else{
                        sendback.setType(MessageType.DELETE_PRODUCT_FAILED);
                        oos.writeObject(sendback);
                        //socket.close();
                    }
                }
                else if(m.getType().equals(MessageType.FIND_TYPE_PRODUCT)){
                    List<Product> ps  = buyers_Shop_utils.findTypeProduct(m.getSender());
                    if(ps.size()!=0){
                        sendback.setData(ps);
                        sendback.setType(MessageType.FIND_TYPE_PRODUCT_SUCCEED);
                        oos.writeObject(sendback);
                    } else{
                        sendback.setType(MessageType.FIND_TYPE_PRODUCT_FAILED);
                        oos.writeObject(sendback);
                        //socket.close();
                    }
                }
                else if(m.getType().equals(MessageType.ADD_PRODUCT)){
                    if(Admin_Shop_utils.addProduct((Product) m.getData())){
                        sendback.setType(MessageType.ADD_PRODUCT_SUCCEED);
                        oos.writeObject(sendback);
                    } else{
                        sendback.setType(MessageType.ADD_PRODUCT_FAILED);
                        oos.writeObject(sendback);
                        //socket.close();
                    }
                }
                else if(m.getType().equals(MessageType.BUY_CERTAIN_PRODUCT)){
                    String ps  = buyers_Shop_utils.buyCertainProduct(m.getGetter(),Integer.parseInt(m.getSender()),m.getCode(),m.getMoney());
                    if(ps.equals("购买成功")){
                        sendback.setData(ps);
                        sendback.setType(MessageType.BUY_CERTAIN__PRODUCT_SUCCEED);
                        oos.writeObject(sendback);
                    } else{
                        sendback.setData(ps);
                        sendback.setType(MessageType.BUY_CERTAIN__PRODUCT_FAILED);
                        oos.writeObject(sendback);
                        //socket.close();
                    }
                }

                else if(m.getType().equals(MessageType.BUY_CERTAIN_PRODUCT_TEACHER)){
                    String ps  = buyers_Shop_utils.buyCertainProduct_Teacher(m.getGetter(),Integer.parseInt(m.getSender()),m.getCode(),m.getMoney());
                    if(ps.equals("购买成功")){
                        sendback.setData(ps);
                        sendback.setType(MessageType.BUY_CERTAIN_PRODUCT_TEACHER_SUCCEED);
                        oos.writeObject(sendback);
                    } else{
                        sendback.setData(ps);
                        sendback.setType(MessageType.BUY_CERTAIN_PRODUCT_TEACHER_FAILED);
                        oos.writeObject(sendback);
                        //socket.close();
                    }
                }

                //学籍管理
                /*if(m.getType().equals(MessageType.RETURN_STUDENT_INFO)){
                    Student stu  = User_SM_utils.returnStudentAllInfo( m.getSender());
                    if(stu!=null){
                        sendback.setData(stu);
                        sendback.setType(MessageType.RETURN_STUDENT_INFO_SUCCEED);
                        oos.writeObject(sendback);             //将message对象回复客户端
                    } else{
                        sendback.setType(MessageType.RETURN_STUDENT_INFO_FAILED);  //登录失败
                        oos.writeObject(sendback);                        //将message对象回复客户端
                        //socket.close();
                    }
                }
                else if(m.getType().equals(MessageType.ADMIN_RETURN_STUDENT_INFO)){
                    Student stu  = User_SM_utils.returnStudentAllInfo( m.getSender());
                    if(stu!=null){
                        sendback.setData(stu);
                        sendback.setType(MessageType.ADMIN_RETURN_STUDENT_INFO_SUCCEED);
                        oos.writeObject(sendback);             //将message对象回复客户端
                    } else{
                        sendback.setType(MessageType.ADMIN_RETURN_STUDENT_INFO_FAILED);  //登录失败
                        oos.writeObject(sendback);                        //将message对象回复客户端
                        //socket.close();
                    }
                }
                else if(m.getType().equals(MessageType.RETURN_PHOTO)){
                    boolean sign = Image_SM_utils.readDBImage(m.getSender());
                    if(sign){
                        sendback.setType(MessageType.RETURN_PHOTO_SUCCEED);
                        oos.writeObject(sendback);             //将message对象回复客户端
                    } else{
                        sendback.setType(MessageType.RETURN_PHOTO_FAILED);  //失败
                        oos.writeObject(sendback);                        //将message对象回复客户端
                       // socket.close();
                    }
                }
                else if(m.getType().equals(MessageType.RENEW_STUDENT_INFO)){
                    Student st = (Student)m.getData();
                    boolean stu  = Admin_SM_utils.changeStudentInfo(st.getStudent_idcard(),st);
                    if(stu){
                        sendback.setType(MessageType.RENEW_STUDENT_INFO_SUCCEED);
                        oos.writeObject(sendback);             //将message对象回复客户端
                    } else{
                        sendback.setType(MessageType.RENEW_STUDENT_INFO_FAILED);  //登录失败
                        oos.writeObject(sendback);                        //将message对象回复客户端
                        //socket.close();
                    }
                }*/
                
                if(m.getType().equals(MessageType.MESSAGE_STATUS_STU_ENTER)){
                    ImageAndTable iat=new ImageAndTable();
                    iat.student  = User_SM_utils.returnStudentAllInfo((String) m.getData());
                    iat.image=Image_SM_utils.readDBImage((String) m.getData());
                    sendback.setType(MessageType.MESSAGE_STATUS_STU_ENTER_RET);
                    sendback.setData(iat);
                    oos.writeObject(sendback);
                }
                else if(m.getType().equals(MessageType.MESSAGE_STATUS_ADMIN_QUERY)){
                    if(User_SM_utils.search((String) m.getData())==true){
                        ImageAndTable iat=new ImageAndTable();
                        iat.student  = User_SM_utils.returnStudentAllInfo((String) m.getData());
                        iat.image=Image_SM_utils.readDBImage((String) m.getData());
                        sendback.setData(iat);
                        sendback.setType(MessageType.MESSAGE_STATUS_ADMIN_QUERY_RET);
                    }
                    else {
                        sendback.setType(MessageType.MESSAGE_STATUS_ADMIN_QUERY_FAIL);
                    }
                    oos.writeObject(sendback);
                }
                else if(m.getType().equals(MessageType.MESSAGE_STATUS_CONFIRM)){
                    Student st=(Student)m.getData();
                    Admin_SM_utils.changeStudentInfo(st);
                    ImageAndTable iat=new ImageAndTable();
                    iat.student =User_SM_utils.returnStudentAllInfo(st.getStudent_idcard());
                    iat.image=Image_SM_utils.readDBImage(st.getStudent_idcard());
                    sendback.setData(iat);
                    sendback.setType(MessageType.MESSAGE_STATUS_CONFIRM_RET);
                    oos.writeObject(sendback);
                    System.out.println("okokok");
                }
            } catch (Exception e){
                e.printStackTrace();
            }

        }

        System.out.println(userid+" exit succeed");
    }

}
