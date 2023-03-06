package ClientToServer;

import java.util.HashMap;

/**
 * 管理端服务器线程
 *
 * @author 28468
 * @version : [v1.0]
 * @description : [管理客户端到服务端线程的类]
 * @createTime : [2022.08.14 20:37]
 * @date 2022/09/04
 */
public class ManageClientToServerThread {
    /**
     * 线程池
     *///将多个线程放入HashMap中， key是用户id , value 为线程
    private static HashMap<String,ClientToServerThread> hm = new HashMap<>();

    /**
     * 添加线程
     *
     * @param id   id
     * @param ctst ctst
     *///将某个线程加入到集合中
    public static void addThread(String id, ClientToServerThread ctst){
        hm.put(id,ctst);
    }

    /**
     * 获取线程
     *
     * @param id id
     * @return {@link ClientToServerThread}
     *///通过id得到对应线程
    public static ClientToServerThread getThread(String id){
        return hm.get(id);
    }

    /**
     * 删除客户端到服务器线程
     *
     * @param id id
     */
    public static void removeClientToServerThread(String id){
//        if(ManageClientToServerThread.hm.containsKey(id)) System.out.println("contains");
        hm.remove(id);
    }

    /**
     * 获得线程数量
     *
     * @return int
     */
    public static int getNum(){
        return hm.size();
    }
}
