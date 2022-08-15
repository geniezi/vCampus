package User;

import java.io.Serializable;

/**
 * @author : [Tongwei_L]
 * @version : [v1.0]
 * @description : [学生类]
 * @createTime : [2022.08.14 19:42]
 */
public class Student implements Serializable {
    String Student_id;       //学（工）号
    String Student_pwd;      //密码
    int Student_age;         //年龄
    String Student_gender;   //性别
    String Student_email;    //邮箱
    String Student_idcard;   //一卡通
    int Student_class;       //学生年级
    double Student_money;    //用户余额

    public String getStudent_id() {
        return Student_id;
    }

    public void setStudent_id(String student_id) {
        Student_id = student_id;
    }

    public String getStudent_pwd() {
        return Student_pwd;
    }

    public void setStudent_pwd(String student_pwd) {
        Student_pwd = student_pwd;
    }

    public int getStudent_age() {
        return Student_age;
    }

    public void setStudent_age(int student_age) {
        Student_age = student_age;
    }

    public String getStudent_gender() {
        return Student_gender;
    }

    public void setStudent_gender(String student_gender) {
        Student_gender = student_gender;
    }

    public String getStudent_email() {
        return Student_email;
    }

    public void setStudent_email(String student_email) {
        Student_email = student_email;
    }

    public String getStudent_idcard() {
        return Student_idcard;
    }

    public void setStudent_idcard(String student_idcard) {
        Student_idcard = student_idcard;
    }

    public int getStudent_class() {
        return Student_class;
    }

    public void setStudent_class(int student_class) {
        Student_class = student_class;
    }

    public double getStudent_money() {
        return Student_money;
    }

    public void setStudent_money(double student_money) {
        Student_money = student_money;
    }
}
