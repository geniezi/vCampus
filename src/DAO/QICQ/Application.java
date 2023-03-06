package DAO.QICQ;

import java.io.Serializable;

/**
 * 站内通信加好友信息
 *
 * @author Shuheng_Gu
 * @date 2022/09/03
 */
public class Application implements Serializable {
    /**
     * 申请人id
     */
    public String from_id;
    /**
     * 申请人名字
     */
    public String from_name;
    /**
     * 状态 0=waiting 1=denied 2=accepted
     */
    int status; //0=waiting 1=denied 2=accepted
    /**
     * 申请对象id
     */
    String to_id;
    /**
     * 申请对象名字
     */
    String to_name;
    /**
     * 分组
     */
    public String group;

    public Application(String from_id, String from_name) {
        this.from_id = from_id;
        this.from_name = from_name;
        this.status = 0;
    }

    public Application() {
    }

    public String getTo_id() {
        return to_id;
    }
    public void setTo_id(String to_id) {
        this.to_id = to_id;
    }

    public String getTo_name() {
        return to_name;
    }

    public void setTo_name(String to_name) {
        this.to_name = to_name;
    }

}
