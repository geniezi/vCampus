package DAO.Library;

import java.io.Serializable;

/**
 * 借书人可以查看的图书信息
 *
 * @author Shuheng_Gu
 * @date 2022/09/03
 */
public class Book_borrower implements Serializable {
    /**
     * 可用
     */
    int available;
    /**
     * 名字
     */
    String name;
    /**
     * 作者
     */
    String author;
    /**
     * 借出人
     */
    String borrow_to;
    /**
     * 出版商
     */
    String publisher;
    /**
     * 馆藏地
     */
    String place;
    /**
     * 国家
     */
    String country;
    /**
     * id
     */
    String id;
    /**
     * 借出日期
     */
    String date_borrow;
    /**
     * 到期日期
     */
    String date_expire;//借阅、到期日期

    /**
     * 获得可用
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
     * 得到出人
     *
     * @return {@link String}
     */
    public String getBorrow_to() {
        return borrow_to;
    }

    /**
     * 设置借出人
     *
     * @param borrow_to 借钱
     */
    public void setBorrow_to(String borrow_to) {
        this.borrow_to = borrow_to;
    }

    /**
     * 让出版商
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
     * 得到id
     *
     * @return {@link String}
     */
    public String getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取借出日期
     *
     * @return {@link String}
     */
    public String getDate_borrow() {
        return date_borrow;
    }

    /**
     * 设置借出日期
     *
     * @param date_borrow 日期借
     */
    public void setDate_borrow(String date_borrow) {
        this.date_borrow = date_borrow;
    }

    /**
     * 获取到期日期
     *
     * @return {@link String}
     */
    public String getDate_expire() {
        return date_expire;
    }

    /**
     * 设置到期日期
     *
     * @param date_expire 到期日期
     */
    public void setDate_expire(String date_expire) {
        this.date_expire = date_expire;
    }
}
