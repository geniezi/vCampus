package DAO.Library;

import java.io.Serializable;
import java.util.Date;

/**
 * 图书管理员
 * 图书管理员能获取的图书信息
 *
 * @author Shuheng_Gu
 * @date 2022/09/03
 */
public class Book_admin implements Serializable{
    /**
     * 名字
     */
    String name;
    /**
     * 作者
     */
    String author;
    /**
     * 借阅人
     */
    String borrow_to;
    /**
     * id
     */
    String ID;
    /**
     * 馆藏地
     */
    String place;
    /**
     * 出版商
     */
    String publisher;
    /**
     * 国家
     */
    String country;
    /**
     * 日期借
     */
    String date_borrow;
    /**
     * 到期日期
     */
    String date_expire; //借阅、到期日期
    /**
     * 价格
     */
    double price;
    /**
     * 可借 1=可借 0=已经借出
     */
    int available;

    /**
     * 得到名字
     *
     * @return {@link String}
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名字
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 得到作者
     *
     * @return {@link String}
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设置作者
     *
     * @param author 作者
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 得到借书人
     *
     * @return {@link String}
     */
    public String getBorrow_to() {
        return borrow_to;
    }

    /**
     * 设置借书人
     *
     * @param borrow_to 借钱
     */
    public void setBorrow_to(String borrow_to) {
        this.borrow_to = borrow_to;
    }

    /**
     * 得到id
     *
     * @return {@link String}
     */
    public String getID() {
        return ID;
    }

    /**
     * 设置id
     *
     * @param ID id
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * 得到馆藏地
     *
     * @return {@link String}
     */
    public String getPlace() {
        return place;
    }

    /**
     * 设置馆藏地
     *
     * @param place 地方
     */
    public void setPlace(String place) {
        this.place = place;
    }

    /**
     * 得到出版商
     *
     * @return {@link String}
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * 设置出版商
     *
     * @param publisher 出版商
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * 获得国家
     *
     * @return {@link String}
     */
    public String getCountry() {
        return country;
    }

    /**
     * 设置国家
     *
     * @param country 国家
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 获取日期借
     *
     * @return {@link String}
     */
    public String getDate_borrow() {
        return date_borrow;
    }

    /**
     * 设置日期借
     *
     * @param date_borrow 日期借
     */
    public void setDate_borrow(String date_borrow) {
        this.date_borrow = date_borrow;
    }

    /**
     * 获取日期到期
     *
     * @return {@link String}
     */
    public String getDate_expire() {
        return date_expire;
    }

    /**
     * 设置日期到期
     *
     * @param date_expire 到期日期
     */
    public void setDate_expire(String date_expire) {
        this.date_expire = date_expire;
    }

    /**
     * 得到价格
     *
     * @return double
     */
    public double getPrice() {
        return price;
    }

    /**
     * 设置价格
     *
     * @param price 价格
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * 得到可用
     *
     * @return int
     */
    public int getAvailable() {
        return available;
    }

    /**
     * 设置可用
     *
     * @param available 可用
     */
    public void setAvailable(int available) {
        this.available = available;
    }
}
