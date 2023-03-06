package ClientToServer;
import javax.swing.*;
import java.awt.*;

import DAO.Curriculum.Course;
import DAO.Curriculum.Opencourse;
import DAO.Library.Book_admin;
import DAO.Library.Book_borrower;
import DAO.Library.Punishment;
import DAO.QICQ.Application;
import DAO.QICQ.Friend;
import DAO.Shop.Product;
import DAO.Shop.ProductPair;
import DAO.StatusManagement.ImageAndTable;
import UIhandler.Currirulum.Client_curriculum;
import UIhandler.Library.Client_library;
import UIhandler.QICQ.Client_qicq;
import UIhandler.Shop.Client_shop;
import UIhandler.StatusManagement.Client_status;
import UIviewer.Library.*;
import UIviewer.QQ.change_panel;
import User.Student;
import message.Message;
import message.MessageType;
import utils.MyObjectInputStream;
import utils.MyObjectOutputStream;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static UIviewer.Library.readLib.*;

/**
 * 客户端服务器线程
 *
 * @author 28468
 * @version : [v1.0]
 * @description : [客户端连接服务端线程]
 * @createTime : [2022.08.14 20:23]
 * @date 2022/09/04
 */
public class ClientToServerThread extends Thread {
    /**
     * 套接字
     */
    private Socket socket;

    /**
     * 退出
     *///volatile修饰符用来保证其它线程读取的总是该变量的最新的值
    public volatile boolean exit = false;
    /**
     * ois
     */
    private static MyObjectInputStream ois=null;

    /**
     * 客户端服务器线程
     *
     * @param mis 管理信息系统
     * @param s   socket
     */
    public ClientToServerThread(MyObjectInputStream mis,Socket s){
        ois=mis;
        socket=s;
    }

    /**
     * 获取套接字
     *
     * @return {@link Socket}
     */
    public Socket getSocket(){
        return socket;
    }

    /**
     * 运行
     */
    public void run(){
        while (!exit){
            try {
                Message message = null;
                try {
                    message = (Message) ois.readObject();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                //   System.out.println(message.getType());
                //如果服务器没有发送Message对象，线程会一直堵塞在这里
                if(message.getType().equals(MessageType.MESSAGE_LIBRARY_ADMIN_LIST_RET)){
                    ArrayList<Book_admin> books=(ArrayList<Book_admin>)message.getData();
                    try {
                        Client_library.showAllBooks(books);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                else if (message.getType().equals(MessageType.MESSAGE_LIBRARY_LIST_MY_BOOK_RET)) {
                    ArrayList<Book_borrower>mybook=(ArrayList<Book_borrower>) message.getData();
                    try {
                        Client_library.showMyBooks(mybook);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
                else if(message.getType().equals(MessageType.MESSAGE_LIBRARY_LIST_MY_TICKET_RET)){
                    ArrayList<Punishment>myPunishments=(ArrayList<Punishment>) message.getData();
                    try {
                        Client_library.showMyPunishments(myPunishments);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                else if(message.getType().equals(MessageType.MESSAGE_LIBRARY_QUERY_RET)){
                    ArrayList<Book_borrower> searchResult=(ArrayList<Book_borrower>) message.getData();
                    try {
                        Client_library.showSearchResult(searchResult);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                else if(message.getType().equals(MessageType.MESSAGE_LIBRARY_RET_SUCCEED)){
                    JOptionPane.showMessageDialog(null,"还书成功!");
                    try {
                        Client_library.RequireMyBooks();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else if(message.getType().equals(MessageType.MESSAGE_LIBRARY_RET_LATE)){
                    JOptionPane.showMessageDialog(null,"逾期还书，请记得按时还书!");
                    try {
                        Client_library.RequireMyBooks();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else if(message.getType().equals(MessageType.MESSAGE_LIBRARY_EXTEND_SUCCEED)){
                    JOptionPane.showMessageDialog(null,"续借成功!");
                    try {
                        Client_library.RequireMyBooks();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else if(message.getType().equals(MessageType.MESSAGE_LIBRARY_EXTEND_FAIL)){
                    JOptionPane.showMessageDialog(null,"续借失败，已经续借一次!");
                }
                else if(message.getType().equals(MessageType.MESSAGE_LIBRARY_BORROW_SUCCEED)){
                    JOptionPane.showMessageDialog(null,"借阅成功!");
                    try {
                        Client_library.RequireSearchResult(Client_library.lastsearch);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else if(message.getType().equals(MessageType.MESSAGE_LIBRARY_BORROW_FAIL_TOO_MANY)){
                    JOptionPane.showMessageDialog(null,"个人借书超数，借书失败！");
                }
                else if(message.getType().equals(MessageType.MESSAGE_LIBRARY_BORROW_FAIL_RETURN_FIRST)){
                    JOptionPane.showMessageDialog(null,"请先归还逾期未还的图书！");
                }
                else if(message.getType().equals(MessageType.MESSAGE_LIBRARY_PAY_SUCCEED)){
                    JOptionPane.showMessageDialog(null,"缴费成功！");
                    try {
                        Client_library.RequireMyPunishments();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else if(message.getType().equals(MessageType.MESSAGE_LIBRARY_PAY_FAIL)){
                    JOptionPane.showMessageDialog(null,"余额不足，缴费失败！");
                }
                else if(message.getType().equals(MessageType.MESSAGE_LIBRARY_ENTER_RET)){
                    try {
                        Client_library.admin_enter_result((ArrayList<Book_admin>) message.getData());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                else if(message.getType().equals(MessageType.MESSAGE_LIBRARY_ADMIN_DELETE_SUCCEED)){
                    JOptionPane.showMessageDialog(null,"删除成功");
                }
                else if(message.getType().equals(MessageType.MESSAGE_LIBRARY_ADMIN_DELETE_FAIL)){
                    JOptionPane.showMessageDialog(null,"删除失败，未找到书籍");
                }


                //商店具体操作
                Message send = new Message();

                if(message.getType().equals(MessageType.RETURN_ALL_PRODUCT_SUCCEED)){
                    List<Product> ps = ((List<Product>) message.getData());
             //       System.out.println("@@"+ps.size());
                    Client_shop.setProducts(ps);
                }

                else if(message.getType().equals(MessageType.FIND_PRODUCT_SUCCEED)){
                    List<Product> ps = ((List<Product>) message.getData());
                    Client_shop.setCheckproducts(ps);
                    Client_shop.setSign_find_type("2");
                }
                else if(message.getType().equals(MessageType.FIND_PRODUCT_FAILED)){
                    Client_shop.resetCheckedProducts();
                    Client_shop.setSign_find_type("3");
                }

                else if(message.getType().equals(MessageType.FIND_TYPE_PRODUCT_SUCCEED)){
                    List<Product> ps = ((List<Product>) message.getData());
                    Client_shop.setCheckproductsType(ps);
                    Client_shop.setSign_find_type("2");
                }
                else if(message.getType().equals(MessageType.FIND_TYPE_PRODUCT_FAILED)){
                    Client_shop.resetCheckedtypeProducts();
                    Client_shop.setSign_find_type("3");
                }
                else if(message.getType().equals(MessageType.FIND_PRODUCT_SUCCEED_ZERO)){
                    List<Product> ps = ((List<Product>) message.getData());
                    Client_shop.setSign(false);
                }
                else if(message.getType().equals(MessageType.CHECK_BUYED_PRODUCT_SUCCEED)){
                    List<ProductPair> s  = (List<ProductPair>) message.getData();
                    Client_shop.setBuyed(s);
                    Client_shop.setSign_zero(false);
                }
                else if(message.getType().equals(MessageType.CHECK_BUYED_PRODUCT_FAILED)){
                    Client_shop.setBuyed(null);
                    Client_shop.setSign_zero(false);
                }

                else if(message.getType().equals(MessageType.CHECK_READYTOBUY_PRODUCT_SUCCEED)){
                    List<ProductPair> s  = (List<ProductPair>) message.getData();
                    Client_shop.setReadyToBuy(s);
                    Client_shop.setSign_zero(false);
                }
                else if(message.getType().equals(MessageType.CHECK_READYTOBUY_PRODUCT_FAILED)){
                    Client_shop.setReadyToBuy(null);
                    Client_shop.setSign_zero(false);
                }

                else if(message.getType().equals(MessageType.DELETE_READYTPBUY_PRODUCT_SUCCEED)){
                    Client_shop.setSign_delete("2");
                }
                else if(message.getType().equals(MessageType.DELETE_READYTPBUY_PRODUCT_FAILED)){
                    Client_shop.setSign_delete("3");
                }

                else if(message.getType().equals(MessageType.ADD_TO_SHOPCAR_SUCCEED)){
                    Client_shop.setSign_add("2");
                }
                else if(message.getType().equals(MessageType.ADD_TO_SHOPCAR_FAILED)){
                    Client_shop.setSign_add("3");
                }

                else if(message.getType().equals(MessageType.GET_MONEY_SUCCEED)){
                    Client_shop.setCurrent_money((double) message.getData());
                    Client_shop.setSign_delete("2");
                }
                else if(message.getType().equals(MessageType.GET_MONEY_FAILED)){
                    Client_shop.setSign_delete("3");
                }

                else if(message.getType().equals(MessageType.GET_MONEY_TEACHER_SUCCEED)){
                    Client_shop.setCurrent_money((double) message.getData());
                    Client_shop.setSign_delete("2");
                }
                else if(message.getType().equals(MessageType.GET_MONEY_TEACHER_FAILED)){
                    Client_shop.setSign_delete("3");
                }

                else if(message.getType().equals(MessageType.CHECK_CERTAIN__PRODUCT_SUCCEED)){
                    Product ps = (Product) message.getData();
                    Client_shop.setCertainProducts(ps);
                    Client_shop.setSign_Certain("2");
                }
                else if(message.getType().equals(MessageType.CHECK_CERTAIN__PRODUCT_FAILED)){
                    Client_shop.setCertainProducts(null);
                    Client_shop.setSign_Certain("2");
                }

                else if(message.getType().equals(MessageType.BUY_CERTAIN__PRODUCT_SUCCEED)){
                    String ps = (String) message.getData();
                    Client_shop.setNow_Buy_product(ps);
                }
                else if(message.getType().equals(MessageType.BUY_CERTAIN__PRODUCT_FAILED)){
                    String ps = (String) message.getData();
                    Client_shop.setNow_Buy_product(ps);
                }

                else if(message.getType().equals(MessageType.BUY_CERTAIN_PRODUCT_TEACHER_SUCCEED)){
                    String ps = (String) message.getData();
                    Client_shop.setNow_Buy_product(ps);
                }
                else if(message.getType().equals(MessageType.BUY_CERTAIN_PRODUCT_TEACHER_FAILED)){
                    String ps = (String) message.getData();
                    Client_shop.setNow_Buy_product(ps);
                }

                else if(message.getType().equals(MessageType.DELETE_PRODUCT_SUCCEED)){
                    Client_shop.setSign_delete("2");
                }
                else if(message.getType().equals(MessageType.DELETE_PRODUCT_FAILED)){
                    Client_shop.setSign_delete("3");
                }
                else if(message.getType().equals(MessageType.ADD_PRODUCT_SUCCEED)){
                    Client_shop.setSign_add("2");
                }
                else if(message.getType().equals(MessageType.ADD_PRODUCT_FAILED)){
                    Client_shop.setSign_add("3");
                }

                //学籍管理
                /*if(message.getType().equals(MessageType.RENEW_STUDENT_INFO_SUCCEED)){
                    Client_status.setSign_renew("2");
                }
                else if(message.getType().equals(MessageType.RENEW_STUDENT_INFO_FAILED)){
                    Client_status.setSign_renew("3");
                }
                else if(message.getType().equals(MessageType.RETURN_STUDENT_INFO_SUCCEED)){
                    Student stu = ((Student) message.getData());
                    Client_status.setS(stu);
                }
                else if(message.getType().equals(MessageType.ADMIN_RETURN_STUDENT_INFO_SUCCEED)){
                    Student stu = ((Student) message.getData());
                    Client_status.setS_s(stu);
                }*/
                if(message.getType().equals(MessageType.MESSAGE_STATUS_STU_ENTER_RET)){
                    try {
                        Client_status.show_studata((ImageAndTable)message.getData());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                else if(message.getType().equals(MessageType.MESSAGE_STATUS_ADMIN_QUERY_RET)){
                    try {
                        Client_status.show_info((ImageAndTable)message.getData());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                else if(message.getType().equals(MessageType.MESSAGE_STATUS_ADMIN_QUERY_FAIL)){
                    JOptionPane.showMessageDialog(null,"    查询失败! 学生不存在");
                }
                else if(message.getType().equals(MessageType.MESSAGE_STATUS_CONFIRM_RET)){
                    JOptionPane.showMessageDialog(null, "   修改学生信息成功!");
                }
                //站内通信
                if(message.getType().equals(MessageType.MESSAGE_QICQ_LIST_FRIENDS_RET)){
                     HashMap<String,ArrayList<Friend>>friends=(HashMap<String,ArrayList<Friend>>)message.getData();
              //       System.out.println("shit1");
                     Client_qicq.show_friend(friends);
                }
                else if(message.getType().equals(MessageType.MESSAGE_QICQ_GET_MESSAGE_RET)){
                     ArrayList<Message>messages=(ArrayList<Message>)message.getData();
                     Client_qicq.show_message(messages);
                }
                else if(message.getType().equals(MessageType.MESSAGE_QICQ_RECERIVE_MESSAGE)){
                     String sender=message.getSender();
                     Client_qicq.receive_message(sender);
                }
                else if (message.getType().equals(MessageType.MESSAGE_QICQ_RECERIVE_FILE)){
                   // System.out.println("receive_file");
                    //Client_qicq.get_file(message,"C:/Users/Lenovo/shit.txt");
                    String sender=message.getSender();
                    Client_qicq.receive_message(sender);
                }
                else if(message.getType().equals(MessageType.MESSAGE_QICQ_FRIEND_ONLINE_RET)){
          //          System.out.println("received...");
                    try {
                        Client_qicq.Require_friend_list();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                else if(message.getType().equals(MessageType.MESSAGE_QICQ_FRIEND_OFFLINE_RET)){
                    try {
                        Client_qicq.Require_friend_list();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                else if (message.getType().equals(MessageType.MESSAGE_QICQ_ADD_FRIEND_SUCCEED)) {
                    Client_qicq.add_friend_succeed();
                    try {
                        Client_qicq.Require_friend_list();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                else if(message.getType().equals(MessageType.MESSAGE_QICQ_ADD_FRIEND_FAIL_CANNOT_FIND_USER)){
                    Client_qicq.add_friend_fail();
                }
                else if(message.getType().equals(MessageType.MESSAGE_QICQ_LIST_APPLICATION_RET)){
                    ArrayList<Application>messages=(ArrayList<Application>)message.getData();
                    Client_qicq.list_application(messages);
                }
                else if(message.getType().equals(MessageType.MESSAGE_QICQ_MODIFY_RET)){
                    JOptionPane.showMessageDialog(null,"修改成功");
                    try {
                        Client_qicq.Require_friend_list();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    change_panel.change_succeed();
                }
               // else if(message.getType().equals(MessageType.MESSAGE_QICQ_GET_ANNOUNCEMENT_RET)){
//                    System.out.println(2);
//                    ArrayList<Message>messages=(ArrayList<Message>)message.getData();
//                    Client_qicq.Show_announcement(messages);
//                }
            //    System.out.println("next");

                //选课
                if(message.getType().equals(MessageType.MESSAGE_CURRICULUM_QUERY_RET)){
                    Client_curriculum.showConsultResult((ArrayList<Course>)message.getData());
                }
                else if(message.getType().equals(MessageType.MESSAGE_CURRICULUM_MY_CHOOSING_RET)){
                    Client_curriculum.show_choosable((ArrayList<Course>)message.getData());
                }
                else if(message.getType().equals(MessageType.MESSAGE_CURRICULUM_LIST_ALL_RET)){
                    Client_curriculum.admin_all_course((ArrayList<Course>)message.getData());
                }
                else if(message.getType().equals(MessageType.MESSAGE_CURRICULUM_LIST_APPLICATION_RET)){
                    Client_curriculum.showApplyResult((ArrayList<Opencourse>)message.getData());
                }
                else if(message.getType().equals(MessageType.MESSAGE_CURRICULUM_LIST_MINE_RET)){
                    if(myInfo.getType()==1) Client_curriculum.showMyChoice((ArrayList<Course>)message.getData());
                    //if(myInfo.getType()==2) Client_curriculum.showTeacherChoice((ArrayList<Course>)message.getData());
                }
                else if(message.getType().equals(MessageType.MESSAGE_CURRICULUM_CHOOSE_SUCCEED)){
                    JOptionPane.showMessageDialog(null,"选课成功");
                }
                else if(message.getType().equals(MessageType.MESSAGE_CURRICULUM_CHOOSE_FULL)){
                    JOptionPane.showMessageDialog(null,"选课失败，课程已满");
                }
                else if(message.getType().equals(MessageType.MESSAGE_CURRICULUM_CHOOSE_CONFLICT)){
                    JOptionPane.showMessageDialog(null,"选课失败，课程冲突");
                }
                else if(message.getType().equals(MessageType.MESSAGE_CURRICULUM_APPLY_SUCCEED)){
                    JOptionPane.showMessageDialog(null,"课程申报成功！");
                }
                else if(message.getType().equals(MessageType.MESSAGE_CURRICULUM_APPLY_FAIL)){
                    JOptionPane.showMessageDialog(null,"课程申报失败，已经存在相同课程！");
                }
                else if(message.getType().equals(MessageType.MESSAGE_CURRICULUM_SHOW_STU_RET)){
               //     System.out.println("return");
                     Client_curriculum.show_my_students((ArrayList<Student>)message.getData());

                }
                else if(message.getType().equals(MessageType.MESSAGE_CURRICULUM_SHOW_SCHEDULE_RET)){
                    if(myInfo.getType()==2) Client_curriculum.show_tea_schedule((String[][][])message.getData());
                    if(myInfo.getType()==1) Client_curriculum.show_my_schedule((String[][][])message.getData());
                }
                else if(message.getType().equals(MessageType.MESSAGE_CURRICULUM_LIST_ADMIN_APPLICATION_RET)){
                    Client_curriculum.show_all_application((ArrayList<Opencourse>)message.getData());
                }
                else if(message.getType().equals(MessageType.MESSAGE_CURRICULUM_LIST_TEACHER_COURSE_RET)){
                    Client_curriculum.showTeacherChoice((ArrayList<Course>) message.getData());
                }
             //   System.out.println("next");
            } catch (InterruptedIOException e){
                break;
            }
//            catch (EOFException e) {
//                System.out.println("已从流中读完,安全退出");
//            }
            catch (Exception e) {
 //               e.printStackTrace();
            }
        }
    }

}
