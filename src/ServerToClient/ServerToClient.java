package ServerToClient;

import User.Student;
import connection.*;
import message.Message;
import message.MessageType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author : [Tongwei_L]
 * @version : [v1.0]
 * @description : [服务器，监听8080，等待客户端连接，保持通信]
 * @createTime : [2022.08.14 19:36]
 */
public class ServerToClient extends Thread{
    private ServerSocket ss = null;

    public ServerToClient() throws IOException {
        try{
            System.out.println("服务器在8080端口监听中...");
            ss=new ServerSocket(8080);

            while (true){
                Socket socket = ss.accept();
                //得到socket关联的对象输入流
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                //读取客户端发送的Student对象
                Student s = (Student) ois.readObject();
                //创建Message对象，准备回复客户端
                Message m=new Message();
                //得到socket关联的对象输出流
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                //连接数据库查询对错
                if(JDBC_Connector.checkAccount(s.getStudent_id(),s.getStudent_pwd())){
                    m.setType(MessageType.MESSAGE_LOGIN_SUCCEED);
                    //将message对象回复客户端
                    oos.writeObject(m);
                    //创建一个线程，和客户端通信，该线程持有socket对象
                    ServerToClientThread stct = new ServerToClientThread(socket,s.getStudent_id());
                    stct.start();
                    //放入线程集合中
                    ManageServerToClientThread.addThread(s.getStudent_id(),stct);
                }else {  //登录失败
                    m.setType(MessageType.MESSAGE_LOGIN_FAIL);
                    //将message对象回复客户端
                    oos.writeObject(m);
                    socket.close();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //服务器退出while，服务器不再监听
            ss.close();
        }
    }
    public static void main(String[] args) throws IOException {
        new ServerToClient();
    }
}
