package User;
import java.util.*;
import java.io.Serializable;

/**
 * @author : [Tongwei_L]
 * @version : [v1.0]
 * @description : [老师类]
 * @createTime : [2022.08.14 19:43]
 */
public class Teacher implements Serializable {
    String Teacher_id;       //学（工）号
    String Teacher_pwd;      //密码
    int Teacher_age;         //年龄
    String Teacher_gender;   //性别
    String Teacher_email;    //邮箱
    String Teacher_idcard;   //一卡通
    int Teacher_title;       //老师职称
    List<String> Teacher_courses; //老师教授的所有课程
    double Teacher_money;    //用户余额

    public String getTeacher_id() {
        return Teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        Teacher_id = teacher_id;
    }

    public String getTeacher_pwd() {
        return Teacher_pwd;
    }

    public void setTeacher_pwd(String teacher_pwd) {
        Teacher_pwd = teacher_pwd;
    }

    public int getTeacher_age() {
        return Teacher_age;
    }

    public void setTeacher_age(int teacher_age) {
        Teacher_age = teacher_age;
    }

    public String getTeacher_gender() {
        return Teacher_gender;
    }

    public void setTeacher_gender(String teacher_gender) {
        Teacher_gender = teacher_gender;
    }

    public String getTeacher_email() {
        return Teacher_email;
    }

    public void setTeacher_email(String teacher_email) {
        Teacher_email = teacher_email;
    }

    public String getTeacher_idcard() {
        return Teacher_idcard;
    }

    public void setTeacher_idcard(String teacher_idcard) {
        Teacher_idcard = teacher_idcard;
    }

    public int getTeacher_title() {
        return Teacher_title;
    }

    public void setTeacher_title(int teacher_title) {
        Teacher_title = teacher_title;
    }

    public List<String> getTeacher_courses() {
        return Teacher_courses;
    }

    public void setTeacher_courses(List<String> teacher_courses) {
        Teacher_courses = teacher_courses;
    }

    public double getTeacher_money() {
        return Teacher_money;
    }

    public void setTeacher_money(double teacher_money) {
        Teacher_money = teacher_money;
    }
}
