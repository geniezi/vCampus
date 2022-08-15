package ClientToServer;

import java.util.HashMap;

/**
 * @author : [Tongwei_L]
 * @version : [v1.0]
 * @description : [管理客户端到服务端线程的类]
 * @createTime : [2022.08.14 20:37]
 */
public class ManageClientToServerThread {
    //将多个线程放入HashMap中， key是用户id , value 为线程
    private static HashMap<String,ClientToServerThread> hm = new HashMap<>();

    //将某个线程加入到集合中
    public static void addThread(String id, ClientToServerThread ctst){
        hm.put(id,ctst);
    }

    //通过id得到对应线程
    public static ClientToServerThread getThread(String id){
        return hm.get(id);
    }

}
