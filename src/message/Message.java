package message;
import java.io.Serializable;

/**
 * @author : [Tongwei_L]
 * @version : [v1.0]
 * @description : [传递的消息]
 * @createTime : [2022.08.12 09:55]
 */
public class Message implements Serializable {

    private String sender;    //发送者
    private String getter;    //接受者
    private String sendTime;  //发送时间
    private String uid;       // 标志号
    private String mesType;      // 消息类型
    private int code;         // 状态码
    private String data;      // 数据

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
