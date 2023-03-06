package ServerToClient;
import DAO.Curriculum.Opencourse;
import DAO.Library.Punishment;
import DAO.Login.Admin_utils;
import DAO.Login.Students_utils;
import DAO.Login.Teachers_utils;
import DAO.QICQ.Application;
import DAO.QICQ.Filetrans;
import DAO.StatusManagement.User_SM_utils;
import User.Admin;
import User.Student;
import User.Teacher;
import connection.JDBC_Connector;
import message.Message;
import message.MessageType;
import utils.MyObjectInputStream;
import utils.MyObjectOutputStream;
import utils.myTime;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * 服务器客户端
 *
 * @author 28468
 * @version : [v1.0]
 * @description : [服务器，监听8080，等待客户端连接，保持通信]
 * @createTime : [2022.08.14 19:36]
 * @date 2022/09/04
 */
public class ServerToClient extends Thread{
    /**
     * 服务器socket
     */
    private ServerSocket ss = null;
    /**
     * 学生实例
     */
    private Student s1;
    /**
     * 老师实例
     */
    private Teacher t1;

    /**
     * 服务器客户端
     *
     * @throws IOException ioexception
     *//*private static HashSet<Punishment> punish=new HashSet<Punishment>();

    private static HashSet<Opencourse>opencourses=new HashSet<Opencourse>();
    private static HashMap<String, ArrayList<Message>>QQbox=new HashMap<String, ArrayList<Message>>();
    private static HashSet<Message>bulletin=new HashSet<Message>();
    private static HashMap<String,ArrayList<Filetrans>>QQfile=new HashMap<String,ArrayList<Filetrans>>();
    private static HashMap<String,ArrayList<Application>>QQapplication=new HashMap<>();
    private static HashMap<String,ArrayList<String>>new_message=new HashMap<>();*/
    public ServerToClient() throws IOException {
        try{
            System.out.println("服务器在"+MessageType.PORT+"端口监听中，"+"IP地址为"+Message.returnIP());
            ss=new ServerSocket(MessageType.PORT);
            while (true){
                // if(punish.size()!=0&&getOnline_admin()!=null) send_ticket(getOnline_admin());
                Socket socket = ss.accept();
                //得到socket关联的对象输入流
                MyObjectInputStream ois = new MyObjectInputStream(socket.getInputStream());
                //读取客户端发送的老师/同学/管理员对象
                Message s = (Message) ois.readObject();
                //创建Message对象，准备回复客户端
                Message m=new Message();
                //得到socket关联的对象输出流
                MyObjectOutputStream oos = new MyObjectOutputStream(socket.getOutputStream());
                if(s.getType().equals(MessageType.MESSAGE_STUDENT_LOGIN)) {
                    Student stu = (Student) s.getData();
                    if(Students_utils.checkStudentAccount(stu.getStudent_idcard(),stu.getStudent_pwd())){

                        m.setType(MessageType.MESSAGE_LOGIN_SUCCEED);
                        //将message对象回复客户端
                        m.setData(Students_utils.returninfo(stu.getStudent_idcard()));
                        oos.writeObject(m);
                        //创建一个线程，和客户端通信，该线程持有socket对象
                        ServerToClientThread stct = new ServerToClientThread(oos,ois,stu.getStudent_idcard(),socket);
                        stct.start();
                        //放入线程集合中
                        ManageServerToClientThread.addThread(stu.getStudent_idcard(),stct);
                        addOnline(stu.getStudent_idcard(),0);
                    }else {  //登录失败
                    //    System.out.println(stu.getStudent_idcard()+"  ..  "+stu.getStudent_pwd());
                        m.setType(MessageType.MESSAGE_LOGIN_FAIL);
                        //将message对象回复客户端
                        oos.writeObject(m);
                        socket.close();
                    }
                }
                else if(s.getType().equals(MessageType.MESSAGE_TEACHER_LOGIN)) {
                    Teacher t = (Teacher) s.getData();
                    if(Teachers_utils.checkTeacherAccount(t.getTeacher_idcard(), t.getTeacher_pwd())){
                        m.setType(MessageType.MESSAGE_LOGIN_SUCCEED);
                        //将message对象回复客户端
                        m.setData(Teachers_utils.returninfo(t.getTeacher_idcard()));
                        oos.writeObject(m);
                        //创建一个线程，和客户端通信，该线程持有socket对象
                        ServerToClientThread stct = new ServerToClientThread(oos,ois,t.getTeacher_idcard(),socket);
                        addOnline(t.getTeacher_idcard(),1);
                        stct.start();
                        //放入线程集合中
                        ManageServerToClientThread.addThread(t.getTeacher_idcard(),stct);
                    }else {  //登录失败
                        m.setType(MessageType.MESSAGE_LOGIN_FAIL);
                        //将message对象回复客户端
                        oos.writeObject(m);
                        socket.close();
                    }
                }
                else if(s.getType().equals(MessageType.MESSAGE_ADMIN_LOGIN)) {
                    Admin a = (Admin) s.getData();
                    if(Admin_utils.checkAdminAccount(a.getAdmin_idcard(), a.getAdmin_pwd())){
                        m.setType(MessageType.MESSAGE_LOGIN_SUCCEED);
                        m.setData(Admin_utils.returninfo(a.getAdmin_idcard()));
                        //将message对象回复客户端
                        oos.writeObject(m);
                        //创建一个线程，和客户端通信，该线程持有socket对象
                        ServerToClientThread stct = new ServerToClientThread(oos,ois,a.getAdmin_idcard(),socket);
                        stct.start();
                        //放入线程集合中
                        ManageServerToClientThread.addThread(a.getAdmin_idcard(),stct);
                        addOnline(a.getAdmin_idcard(),2);
                    }else {  //登录失败
                        m.setType(MessageType.MESSAGE_LOGIN_FAIL);
                        //将message对象回复客户端
                        oos.writeObject(m);
                        socket.close();
                    }
                }
                else if(s.getType().equals(MessageType.MESSAGE_STUDENT_REGISTER)) {
                    Student stu = (Student) s.getData();
                    if(Students_utils.addStudent(stu)){
                        m.setType(MessageType.MESSAGE_STUDENT_REGISTER_SUCCEED);
                        //将message对象回复客户端
                        oos.writeObject(m);
                        //创建一个线程，和客户端通信，该线程持有socket对象
                    }else {  //登录失败
                        m.setType(MessageType.MESSAGE_STUDENT_REGISTER_FAILED);
                        //将message对象回复客户端
                        oos.writeObject(m);
                        socket.close();
                    }
                }
                else if(s.getType().equals(MessageType.MESSAGE_TEACHER_REGISTER)) {
                    Teacher tea = (Teacher) s.getData();
                    if(Teachers_utils.addTeacher(tea)){
                        m.setType(MessageType.MESSAGE_TEACHER_REGISTER_SUCCEED);
                        //将message对象回复客户端
                        oos.writeObject(m);
                        //创建一个线程，和客户端通信，该线程持有socket对象
                    }else {  //登录失败
                        m.setType(MessageType.MESSAGE_TEACHER_REGISTER_FAILED);
                        //将message对象回复客户端
                        oos.writeObject(m);
                        socket.close();
                    }
                }
                else if(s.getType().equals(MessageType.TO_FIND_CERTAIN)) {
                    if(s.getSender().equals("教师")){
                        t1 = (Teacher) s.getData();
                        if(Teachers_utils.findForgetpwdTeacher(t1)){
                            m.setType(MessageType.HAVE_FIND_CERTAIN);
                            oos.writeObject(m);
                        }else {  //登录失败
                            m.setType(MessageType.NOT_FIND_CERTAIN);
                            oos.writeObject(m);
                            socket.close();
                        }
                    } else {
                        s1 = (Student) s.getData();
                        if(Students_utils.findForgetpwdStudent(s1)){
                            m.setType(MessageType.HAVE_FIND_CERTAIN);
                            oos.writeObject(m);
                        }else {  //登录失败
                            m.setType(MessageType.NOT_FIND_CERTAIN);
                            oos.writeObject(m);
                            socket.close();
                        }
                    }
                }
                else if(s.getType().equals(MessageType.RESET_PASSWORD)){
                    if(s.getSender().equals("教师")){
                        t1 = (Teacher) s.getData();
                        if(Teachers_utils.changeTeacherPwd(t1.getTeacher_idcard(),t1)){
                            m.setType(MessageType.RESET_PASSWORD_SUCCEED);
                            oos.writeObject(m);
                        }else {  //登录失败
                            m.setType(MessageType.RESET_PASSWORD_FAILED);
                            oos.writeObject(m);
                            socket.close();
                        }
                    } else {
                        s1 = (Student) s.getData();
                        if(Students_utils.changeStudentPwd(s1.getStudent_idcard(),s1)){
                            m.setType(MessageType.RESET_PASSWORD_SUCCEED);
                            oos.writeObject(m);
                        }else {  //登录失败
                            m.setType(MessageType.RESET_PASSWORD_FAILED);
                            oos.writeObject(m);
                            socket.close();
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //服务器退出while，服务器不再监听
            ss.close();
        }
    }

    /**
     * 在线
     */
    private static HashMap<String, Integer>online=new HashMap<>();

    /**
     * 添加在线
     *
     * @param id id
     * @param ty 泰
     */
    public static void addOnline(String id,int ty){
        online.put(id,ty);
    }

    /**
     * 删除网上
     *
     * @param id id
     */
    public static void removeOnline(String id){
       online.remove(id);
    }

    /**
     * 在线
     *
     * @param id id
     * @return int
     */
    public static int isOnline(String id){
        if(online.containsKey(id)) return online.get(id);
        else return -1;
    }

    /**
     * main方法
     *
     * @param args arg
     * @throws IOException ioexception
     */
    public static void main(String[] args) throws IOException {
        new ServerToClient();
    }
    /*    public static HashSet<Opencourse> getOpencourses() {
        return opencourses;
    }

    public static void add_opencourse(Opencourse open) {
        ServerToClient.opencourses.add(open);
    }
*/
   /* public static HashSet<Punishment> getPunish() {
        return punish;
    }
    public static String getOnline_admin () {
        for (String i : online.keySet()) {
            if(online.get(i).equals(2)) return i;
        }
        return null;
    }*/
  /*  public static void addPunish(Punishment p) {
        ServerToClient.punish.add(p);
    }

    public static ArrayList<Message> getQQbox(String id) {
        if(QQbox.containsKey(id)) return QQbox.get(id);
        else return new ArrayList<Message>();
    }

    public static void addQQbox(String to,Message m) {
        if(QQbox.containsKey(to)){
            QQbox.get(to).add(m);
        }
        else {
            ArrayList<Message>a=new ArrayList<>();
            a.add(m);
            QQbox.put(to,a);
        }

    }*/


    /*public static HashSet<Message> getBulletin() {
        return bulletin;
    }

    public static void addBulletin(Message s) {
        ServerToClient.bulletin.add(s) ;
    }

    public static ArrayList<Filetrans> getQQfile(String id) {
        return QQfile.get(id);
    }

    public static void addQQfile(String to,Filetrans f) {
        if(QQfile.containsKey(to)){
            QQfile.get(to).add(f);
        }
        else {
            ArrayList<Filetrans>a=new ArrayList<>();
            a.add(f);
            QQfile.put(to,a);
        }
    }*/


    /*public static ArrayList<Application> getQQapplication(String id) {
        if(QQapplication.containsKey(id)) return QQapplication.get(id);
        else return new ArrayList<Application>();
    }

    public static void addQQapplication(String to,Application app) {
        if(QQapplication.containsKey(to)){
            QQapplication.get(to).add(app);
        }
        else {
            ArrayList<Application>a=new ArrayList<>();
            a.add(app);
            QQapplication.put(to,a);
        }
    }

    public static ArrayList<String> getNew_message(String id) {
        if(new_message.containsKey(id)) return new_message.get(id);
        else return new ArrayList<String>();
    }

    public static void addNew_message(String to,String from) {
        if(new_message.containsKey(to)){
            new_message.get(to).add(from);
        }
        else {
            ArrayList<String>a=new ArrayList<>();
            a.add(from);
            new_message.put(to,a);
        }
    }*/
}
