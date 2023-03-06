package UIhandler.StatusManagement;

import ClientToServer.ManageClientToServerThread;
import ClientToServer.myInfo;
import DAO.StatusManagement.ImageAndTable;
import UIviewer.login.functionChoose;
import UIviewer.status_manage.manage_status;
import UIviewer.status_manage.student_status;
import User.Admin;
import User.Student;
import message.Message;
import message.MessageType;
import utils.MyObjectOutputStream;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * 客户端状态
 *
 * @author Zhangyang_Ge
 * @date 2022/09/03
 */
public class Client_status {

    public static volatile Student s = null;   //当前已登录账号的那个学生对象
    public static String id;                   //当前已登录账号的那个学生对象id
    public static volatile Student s_s = null; //管理员查找的那个学生
    public static String id_certain;           //管理员查找的那个学生id
    public static volatile Admin a;
    static MyObjectOutputStream oos=null;

    /**
     * 设置oos
     *
     * @param mos 金属氧化物半导体
     */
    public static void setOos(MyObjectOutputStream mos) {
        Client_status.oos = mos;
    }

    /**
     * 斯图输入
     *
     * @throws Exception 异常
     *//*public static String sign_renew;          //是否更新成功的标志 1 正在更新  2 成功  3 失败

    public static void resetS(){
        s=null;
    }
    public static void resetS_S(){
        s_s=null;
    }

    public static String getSign_renew() {
        return sign_renew;
    }

    public static void setSign_renew(String sign_renew) {
        Client_status.sign_renew = sign_renew;
    }

    public static String getId() {
        return id;
    }
    public static void setId(String id) {
        Client_status.id = id;
    }

    public Student getS() {
        return s;
    }

    public static void setS(Student s) {
        Client_status.s = s;
    }

    public static Student getS_s() {
        return s_s;
    }

    public static void setS_s(Student s_s) {
        Client_status.s_s = s_s;
    }

    public Admin getA() {
        return a;
    }

    public void setA(Admin a) {
        Client_status.a = a;
    }

    public static void getphoto(String idcard) throws Exception {
        Message message = new Message();
        message.setType(MessageType.RETURN_PHOTO);
        message.setSender(idcard);
        if(ManageClientToServerThread.getThread(id).getSocket()!=null) {
            MyObjectOutputStream oos= new MyObjectOutputStream(ManageClientToServerThread.getThread(id).getSocket().getOutputStream());
            oos.writeObject(message);
        }
    }

    public static Student returnStatusInfo(String idcard) throws Exception {
        Message message = new Message();
        message.setType(MessageType.RETURN_STUDENT_INFO);
        message.setSender(idcard);
        //得到Object对象
       // MyObjectOutputStream oos = new MyObjectOutputStream(ManageClientToServerThread.getThread(id).getSocket().getOutputStream());
        //发送学生对象
        oos.writeObject(message);
        //等待接受学生
        while (s == null) Thread.onSpinWait();
        return s;
    }

    public static Boolean renewInfo(Student s) throws Exception {
        setSign_renew("1");
        Message message = new Message();
        message.setType(MessageType.RENEW_STUDENT_INFO);
        message.setData(s);
        //得到Object对象
        //MyObjectOutputStream oos = new MyObjectOutputStream(ManageClientToServerThread.getThread(id).getSocket().getOutputStream());
        //发送学生对象
        oos.writeObject(message);
        //等待接受学生
        while (sign_renew.equals("1")) Thread.onSpinWait();
        return sign_renew.equals("2");
    }


    //记得查找另一个学生时让对象为null
    public static Student returnStatus_Admin(String idcard) throws IOException {
        Message message = new Message();
        message.setType(MessageType.ADMIN_RETURN_STUDENT_INFO);
        message.setSender(idcard);
        //得到Object对象
        //MyObjectOutputStream oos = new MyObjectOutputStream(ManageClientToServerThread.getThread(id).getSocket().getOutputStream());
        //发送学生对象
        oos.writeObject(message);
        //等待接受学生
        while (s_s == null) Thread.onSpinWait();
        return s_s;
    }*/
    public static void stu_enter()  throws Exception{
        Message message = new Message();
        message.setType(MessageType.MESSAGE_STATUS_STU_ENTER);
        message.setData(myInfo.getId());
        oos.writeObject(message);
    }

    /**
     * 显示studata
     *
     * @param iat iat
     * @throws Exception 异常
     */
    public static void show_studata(ImageAndTable iat) throws Exception {
      //  System.out.println("opening  "+width+" "+height);
        //functionChoose.jf.remove(functionChoose.jf.getContentPane());
        functionChoose.jf.setContentPane(new student_status(width,height,iat));
        functionChoose.jf.setTitle("Status management");
    }
    static double width_r,height_r;
    static int height,width,icon1_width,icon1_height;

    /**
     * ini
     */
    public static void ini()
    {
        Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
        width=(int ) screensize.getWidth(); //得到宽度
        height=(int ) screensize.getHeight();//获得高度
        width_r=(double)(width)/1920;
        height_r=(double)(height)/1080;
        icon1_width =160;
        icon1_height=50;
    }

    /**
     * 显示信息
     *
     * @param iat iat
     * @throws Exception 异常
     */
    public static void show_info(ImageAndTable iat) throws Exception {

        manage_status.status_jpanel=student_status.status_panel(width_r,height_r,width-2*(60+icon1_width)*width_r,(1080-70-icon1_height)*height_r,iat);
        manage_status.status_jpanel.setBounds((int)((60+icon1_width)*width_r),(int)((47+icon1_height)*height_r), (int)(width-2*(60+icon1_width)*width_r),(int)((1080-70-icon1_height)*height_r));
        manage_status.manage_panel.add(manage_status.status_jpanel,0);
        manage_status.search_panel.setVisible(false);
        manage_status.status_jpanel.setVisible(true);
        //manage_status.status_jpanel.setVisible(true);
    }

    /**
     * 改变
     *
     * @param s 年代
     * @throws IOException ioexception
     */
    public static void change(Student s) throws IOException {
        Message message=new Message();
        message.setData(s);
        message.setType(MessageType.MESSAGE_STATUS_CONFIRM);
        oos.writeObject(message);
    }

    /**
     * 需要信息
     *
     * @param s 年代
     * @throws IOException ioexception
     */
    public static void requireInfo(String s) throws IOException {
        Message message=new Message();
        message.setData(s);
        message.setType(MessageType.MESSAGE_STATUS_ADMIN_QUERY);
        oos.writeObject(message);
    }
}
