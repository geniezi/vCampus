package message;
import DAO.QICQ.Filetrans;
import utils.getIP;

import java.io.Serializable;

/**
 * @author : [Tongwei_L]
 * @version : [v1.0]
 * @description : [传递的消息]
 * @createTime : [2022.08.12 09:55]
 */
public class Message implements Serializable {

    /**
     * 发送方
     */
    private String sender;    //发送者
    /**
     * 接受者
     */
    private String getter;    //接受者
    /**
     * 发送时间
     */
    private String sendTime;  //发送时间
    /**
     * 标志号
     */
    private String uid;       // 标志号
    /**
     * 消息类型
     */
    private String mesType;   // 消息类型
    /**
     * 状态码
     */
    private int code;         // 状态码
    /**
     * 数据
     */
    private Object data;      // 数据
    /**
     * 是否为文件
     */
    public int isfile;

    public Message() {
        sender=null;
        getter=null;
        sendTime=null;
        uid=null;
        mesType=null;
        code=0;
        data=null;
    }

    /**
     * 钱
     */
    private double money;    //钱
    public static String returnIP(){
        return getIP.getHostIp();
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getGetter() {
        return getter;
    }

    public void setGetter(String getter) {
        this.getter = getter;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getType() {
        return mesType;
    }

    public void setType(String type) {
        this.mesType = type;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
