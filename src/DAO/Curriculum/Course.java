package DAO.Curriculum;

import java.io.Serializable;

/**
 * 课程
 * @description 课程类，包含课程信息
 *
 * @author Shuheng_Gu
 * @date 2022/09/03
 */
public class Course implements Serializable {
    /**
     * 课堂时间
     */
    int [][][]class_time=new int[17][6][14];   //上课时间
    /**
     * 名字
     */
    String name;
    /**
     * 老师
     */
    String teacher;
    /**
     * id
     */
    String id;
    /**
     * 教室
     */
    String classroom;
    /**
     * 上课时间
     */
    String timestring;
    /**
     * 学分
     */
    double point;   //学分
    /**
     * 课容量
     */
    int size;
    /**
     * 课时
     */
    int hour;

    /**
     * 得到上课时间
     *
     * @return {@link int[][][]}
     */
    public int[][][] getClass_time() {
        return class_time;
    }

    /**
     * 设置课堂时间
     *
     * @param class_time 课堂时间
     */
    public void setClass_time(int[][][] class_time) {
        this.class_time = class_time;
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
     * 得到老师
     *
     * @return {@link String}
     */
    public String getTeacher() {
        return teacher;
    }

    /**
     * 编辑老师
     *
     * @param teacher 老师
     */
    public void setTeacher(String teacher) {
        this.teacher = teacher;
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
     * 组id
     *
     * @param id id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 得到教室
     *
     * @return {@link String}
     */
    public String getClassroom() {
        return classroom;
    }

    /**
     * 设置课堂
     *
     * @param classroom 教室
     */
    public void setClassroom(String classroom) {
        this.classroom = classroom;
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
     * 得到学时
     *
     * @return int
     */
    public int getHour() {
        return hour;
    }

    /**
     * 设置学时
     *
     * @param hour 小时
     */
    public void setHour(int hour) {
        this.hour = hour;
    }
}
