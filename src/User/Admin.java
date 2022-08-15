package User;

import java.io.Serializable;

/**
 * @author : [Tongwei_L]
 * @version : [v1.0]
 * @description : [管理员类]
 * @createTime : [2022.08.14 19:43]
 */
public class Admin implements Serializable {
    String Admin_id;       //学（工）号
    String Admin_pwd;      //密码
    int Admin_age;         //年龄
    String Admin_gender;   //性别
    String Admin_email;    //邮箱
    String Admin_idcard;   //一卡通

    public String getAdmin_id() {
        return Admin_id;
    }

    public void setAdmin_id(String admin_id) {
        Admin_id = admin_id;
    }

    public String getAdmin_pwd() {
        return Admin_pwd;
    }

    public void setAdmin_pwd(String admin_pwd) {
        Admin_pwd = admin_pwd;
    }

    public int getAdmin_age() {
        return Admin_age;
    }

    public void setAdmin_age(int admin_age) {
        Admin_age = admin_age;
    }

    public String getAdmin_gender() {
        return Admin_gender;
    }

    public void setAdmin_gender(String admin_gender) {
        Admin_gender = admin_gender;
    }

    public String getAdmin_email() {
        return Admin_email;
    }

    public void setAdmin_email(String admin_email) {
        Admin_email = admin_email;
    }

    public String getAdmin_idcard() {
        return Admin_idcard;
    }

    public void setAdmin_idcard(String admin_idcard) {
        Admin_idcard = admin_idcard;
    }
}
