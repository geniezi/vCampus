package User;
import java.util.*;
import java.io.Serializable;

/**
 * 老师
 * The type Teacher.
 *
 * @author 28468
 * @version : [v1.0]
 * @description : [老师类]
 * @createTime : [2022.08.14 19:43]
 * @date 2022/09/03
 */
public class Teacher implements Serializable {
    /**
     * 老师id
     */
    String Teacher_id;
    /**
     * 老师pwd
     */
    String Teacher_pwd;      //密码
    /**
     * 老师名字
     */
    String Teacher_name;     //老师姓名
    /**
     * 老师年龄
     */
    int Teacher_age;         //年龄
    /**
     * 教师性别
     */
    String Teacher_gender;   //性别
    /**
     * 老师邮件
     */
    String Teacher_email;    //邮箱
    /**
     * 老师经办人身份证
     */
    String Teacher_idcard;   //一卡通
    /**
     * 教师称号
     */
    int Teacher_title;       //老师职称
    /**
     * 老师课程
     */
    List<String> Teacher_courses; //老师教授的所有课程
    /**
     * 老师钱
     */
    double Teacher_money;    //用户余额

    /**
     * 得到老师名字
     * Gets teacher name.
     *
     * @return the teacher name
     */
    public String getTeacher_name() {
        return Teacher_name;
    }

    /**
     * 组老师名字
     * Sets teacher name.
     *
     * @param teacher_name the teacher name
     */
    public void setTeacher_name(String teacher_name) {
        Teacher_name = teacher_name;
    }

    /**
     * 得到老师id
     * Gets teacher id.
     *
     * @return the teacher id
     */
    public String getTeacher_id() {
        return Teacher_id;
    }

    /**
     * 老师设置id
     * Sets teacher id.
     *
     * @param teacher_id the teacher id
     */
    public void setTeacher_id(String teacher_id) {
        Teacher_id = teacher_id;
    }

    /**
     * 得到老师pwd
     * Gets teacher pwd.
     *
     * @return the teacher pwd
     */
    public String getTeacher_pwd() {
        return Teacher_pwd;
    }

    /**
     * 设置教师pwd
     * Sets teacher pwd.
     *
     * @param teacher_pwd the teacher pwd
     */
    public void setTeacher_pwd(String teacher_pwd) {
        Teacher_pwd = teacher_pwd;
    }

    /**
     * 得到老师年龄
     * Gets teacher age.
     *
     * @return the teacher age
     */
    public int getTeacher_age() {
        return Teacher_age;
    }

    /**
     * 设定老师年龄
     * Sets teacher age.
     *
     * @param teacher_age the teacher age
     */
    public void setTeacher_age(int teacher_age) {
        Teacher_age = teacher_age;
    }

    /**
     * 得到老师性别
     * Gets teacher gender.
     *
     * @return the teacher gender
     */
    public String getTeacher_gender() {
        return Teacher_gender;
    }

    /**
     * 设置教师性别
     * Sets teacher gender.
     *
     * @param teacher_gender the teacher gender
     */
    public void setTeacher_gender(String teacher_gender) {
        Teacher_gender = teacher_gender;
    }

    /**
     * 得到老师电子邮件
     * Gets teacher email.
     *
     * @return the teacher email
     */
    public String getTeacher_email() {
        return Teacher_email;
    }

    /**
     * 组老师电子邮件
     * Sets teacher email.
     *
     * @param teacher_email the teacher email
     */
    public void setTeacher_email(String teacher_email) {
        Teacher_email = teacher_email;
    }

    /**
     * 得到老师经办人身份证
     * Gets teacher idcard.
     *
     * @return the teacher idcard
     */
    public String getTeacher_idcard() {
        return Teacher_idcard;
    }

    /**
     * 设置教师经办人身份证
     * Sets teacher idcard.
     *
     * @param teacher_idcard the teacher idcard
     */
    public void setTeacher_idcard(String teacher_idcard) {
        Teacher_idcard = teacher_idcard;
    }

    /**
     * 得到老师标题
     * Gets teacher title.
     *
     * @return the teacher title
     */
    public int getTeacher_title() {
        return Teacher_title;
    }

    /**
     * 设置教师标题
     * Sets teacher title.
     *
     * @param teacher_title the teacher title
     */
    public void setTeacher_title(int teacher_title) {
        Teacher_title = teacher_title;
    }

    /**
     * 得到老师课程
     * Gets teacher courses.
     *
     * @return the teacher courses
     */
    public List<String> getTeacher_courses() {
        return Teacher_courses;
    }

    /**
     * 课程组老师
     * Sets teacher courses.
     *
     * @param teacher_courses the teacher courses
     */
    public void setTeacher_courses(List<String> teacher_courses) {
        Teacher_courses = teacher_courses;
    }

    /**
     * 得到老师钱
     * Gets teacher money.
     *
     * @return the teacher money
     */
    public double getTeacher_money() {
        return Teacher_money;
    }

    /**
     * 组老师钱
     * Sets teacher money.
     *
     * @param teacher_money the teacher money
     */
    public void setTeacher_money(double teacher_money) {
        Teacher_money = teacher_money;
    }
}
