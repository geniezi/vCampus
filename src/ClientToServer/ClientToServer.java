package ClientToServer;
import User.*;
import message.Message;
import message.MessageType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author : [Tongwei_L]
 * @version : [v1.0]
 * @description : [一句话描述该类的功能]
 * @createTime : [2022.08.14 20:01]
 */

public class ClientToServer {

    private Student s = new Student();
    private Socket socket;

    public boolean checkStudent(String id, String pwd) throws IOException, ClassNotFoundException {
        s.setStudent_id(id);
        s.setStudent_pwd(pwd);
        socket = new Socket(InetAddress.getByName("127.0.0.1"),8080);
        //得到Object对象
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        //发送学生对象
        oos.writeObject(s);
        //读取从服务端回复的Message对象
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Message ms = (Message) ois.readObject();
        if(ms.getType().equals(MessageType.MESSAGE_LOGIN_SUCCEED)){
            //创建一个和服务器端保持通信的线程
            ClientToServerThread ctst = new ClientToServerThread(socket);
            //启动线程
            ctst.start();
            ManageClientToServerThread.addThread(id,ctst);
            return true;
        }
        else{
            socket.close();
            return false;
        }
    }

}
