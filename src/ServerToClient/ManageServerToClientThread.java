package ServerToClient;

import ClientToServer.ClientToServerThread;

import java.util.HashMap;

/**
 * @author : [Tongwei_L]
 * @version : [v1.0]
 * @description : [管理和客户端通信的线程]
 * @createTime : [2022.08.14 21:14]
 */
public class ManageServerToClientThread {
    //将多个线程放入HashMap中， key是用户id , value 为线程
    private static HashMap<String, ServerToClientThread> hm = new HashMap<>();

    //将某个线程加入到集合中
    public static void addThread(String id, ServerToClientThread ctst){
        hm.put(id,ctst);
    }

    //通过id得到对应线程
    public static ServerToClientThread getThread(String id){
        return hm.get(id);
    }


}
