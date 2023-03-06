package DAO.QICQ;

import java.io.Serializable;

/**
 * 站内通信好友信息
 *
 * @author Shuheng_Gu
 * @date 2022/09/03
 */
public class Friend implements Serializable {
    /**
     * id
     */
    String id;
     /**
     * 名字
     */
    String name;
    /**
     * 分组
     */
    String type;
    /**
     * 未读 0=此朋友处没有未读消息 1=次朋友处有未读消息
     */
    int unread; //0=此朋友处没有未读消息 1=次朋友处有未读消息
    /**
     * 在线 0=不在线 1=在线
     */
    int online; //0=不在线 1=在线
    /**
     * 头像
     */
    public byte[]image;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getUnread() {
        return unread;
    }

    public void setUnread(int unread) {
        this.unread = unread;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }
}
