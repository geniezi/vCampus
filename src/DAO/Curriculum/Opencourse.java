package DAO.Curriculum;

import java.io.Serializable;

/**
 * 教师新开课类
 * @description 可存储数据库中新开可的数据用于传输
 * @author Shuheng_Gu
 * @date 2022/09/03
 */
public class Opencourse implements Serializable {
    /**
     * id
     */
    String id;
    /**
     * 老师
     */
    String teacher;
    /**
     * 名字
     */
    String name;
    /**
     * 上课时间
     */
    String timestring;
    /**
     * 老师id
     */
    String teacher_id;
    /**
     * 学分
     */
    double point;
    /**
     * 大小
     */
    int size;
    /**
     * 状态 2=approve 1=refuse 0=waiting
     */
    int status; //2=approve 1=refuse 0=waiting
    /**
     * 结果
     */
    String result; //admin's comments
    /**
     * 课时
     */
    int hour;

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
     * 得到老师
     *
     * @return {@link String}
     */
    public String getTeacher() {
        return teacher;
    }

    /**
     * 设置老师
     *
     * @param teacher 老师
     */
    public void setTeacher(String teacher) {
        this.teacher = teacher;
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
     * 得到timestring
     *
     * @return {@link String}
     */
    public String getTimestring() {
        return timestring;
    }

    /**
     * 设置timestring
     *
     * @param timestring timestring
     */
    public void setTimestring(String timestring) {
        this.timestring = timestring;
    }

    /**
     * 得到老师id
     *
     * @return {@link String}
     */
    public String getTeacher_id() {
        return teacher_id;
    }

    /**
     * 设置老师id
     *
     * @param teacher_id 老师id
     */
    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    /**
     * 得到学分
     *
     * @return double
     */
    public double getPoint() {
        return point;
    }

    /**
     * 设置学分
     *
     * @param point 点
     */
    public void setPoint(double point) {
        this.point = point;
    }

    /**
     * 得到大小
     *
     * @return int
     */
    public int getSize() {
        return size;
    }

    /**
     * 设置大小
     *
     * @param size 大小
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * 获得地位
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

    /**
     * 得到结果
     *
     * @return {@link String}
     */
    public String getResult() {
        return result;
    }

    /**
     * 设置结果
     *
     * @param result 结果
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * 得到课时
     *
     * @return int
     */
    public int getHour() {
        return hour;
    }

    /**
     * 设置课时
     *
     * @param hour 小时
     */
    public void setHour(int hour) {
        this.hour = hour;
    }
}
