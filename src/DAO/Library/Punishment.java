package DAO.Library;

import java.io.Serializable;

/**
 * 罚单
 * @description 罚单信息
 * @author Shuheng_Gu
 * @date 2022/09/03
 */
public class Punishment implements Serializable {
    /**
     * 罚单id
     */
    String punishmentID;
    /**
     * 客户id
     */
    String Customer_iD;  //处罚用户id

    /**
     * 书id
     */
    String Book_id;

    /**
     * 处罚通知
     */
    String notice;  //处罚通知
    /**
     * 赔偿金额
     */
    double price;// 赔偿金额
    /**
     * 状态 status=0没处理 status=1已处理
     */
    int status;//status=0没处理 status=1已处理


    /**
     * 得到书id
     *
     * @return {@link String}
     */
    public String getBook_id() {
        return Book_id;
    }

    /**
     * 设置书id
     *
     * @param book_id 书id
     */
    public void setBook_id(String book_id) {
        Book_id = book_id;
    }

    /**
     * 得到惩罚id
     *
     * @return {@link String}
     */
    public String getPunishmentID() {
        return punishmentID;
    }

    /**
     * 设置惩罚id
     *
     * @param punishmentID 惩罚id
     */
    public void setPunishmentID(String punishmentID) {
        this.punishmentID = punishmentID;
    }

    /**
     * 得到客户id
     *
     * @return {@link String}
     */
    public String getCustomer_iD() {
        return Customer_iD;
    }

    /**
     * 设置客户id
     *
     * @param customer_iD 客户id
     */
    public void setCustomer_iD(String customer_iD) {
        Customer_iD = customer_iD;
    }

    /**
     * 得到处罚通知
     *
     * @return {@link String}
     */
    public String getNotice() {
        return notice;
    }

    /**
     * 设置处罚通知
     *
     * @param notice 请注意
     */
    public void setNotice(String notice) {
        this.notice = notice;
    }

    /**
     * 得到赔偿金额格
     *
     * @return double
     */
    public double getPrice() {
        return price;
    }

    /**
     * 设置赔偿金额格
     *
     * @param price 价格
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * 获得状态
     *
     * @return int
     */
    public int getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(int status) {
        this.status = status;
    }
}
