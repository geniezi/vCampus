package ServerToClient;

import message.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * @author : [Tongwei_L]
 * @version : [v1.0]
 * @description : [该类的一个对象和某个客户端保持通信]
 * @createTime : [2022.08.14 21:05]
 */
public class ServerToClientThread extends Thread{
    private Socket socket;
    private String userid;//连接到服务器的用户id

    public ServerToClientThread(Socket socket,String userid){
        this.socket=socket;
        this.userid=userid;
    }

    public void run(){
        while (true){
            System.out.println("客户端和服务端 "+userid+" 保持通信，读取数据...");
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message m = (Message) ois.readObject();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
