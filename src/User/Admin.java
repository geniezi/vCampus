package User;

import java.io.Serializable;

/**
 * 管理员
 *
 * @author 28468
 * @version : [v1.0]
 * @description : [管理员类]
 * @createTime : [2022.08.14 19:43]
 * @date 2022/09/03
 */
public class Admin implements Serializable {

    /**
     * 管理员一卡通
     */
    String Admin_idcard;   //一卡通
    /**
     * 管理员id
     */
    String Admin_id;       //学（工）号
    /**
     * 管理员密码
     */
    String Admin_pwd;      //密码
    /**
     * 管理员名字
     */
    String Admin_name;     //管理员姓名
    /**
     * 管理员年龄
     */
    int Admin_age;         //年龄
    /**
     * 管理员性别
     */
    String Admin_gender;   //性别
    /**
     * 管理员电子邮件
     */
    String Admin_email;    //邮箱

    /**
     * 得到管理员名字
     *
     * @return {@link String}
     */
    public String getAdmin_name() {
        return Admin_name;
    }

    /**
     * 集管理员名字
     *
     * @param admin_name 管理员名字
     */
    public void setAdmin_name(String admin_name) {
        Admin_name = admin_name;
    }

    /**
     * 得到管理员id
     *
     * @return {@link String}
     */
    public String getAdmin_id() {
        return Admin_id;
    }

    /**
     * 集管理员id
     *
     * @param admin_id 管理员id
     */
    public void setAdmin_id(String admin_id) {
        Admin_id = admin_id;
    }

    /**
     * 得到管理员密码
     *
     * @return {@link String}
     */
    public String getAdmin_pwd() {
        return Admin_pwd;
    }

    /**
     * 集管理员松材线虫病
     *
     * @param admin_pwd 管理员密码
     */
    public void setAdmin_pwd(String admin_pwd) {
        Admin_pwd = admin_pwd;
    }

    /**
     * 得到管理员年龄
     *
     * @return int
     */
    public int getAdmin_age() {
        return Admin_age;
    }

    /**
     * 集管理员年龄
     *
     * @param admin_age 管理员年龄
     */
    public void setAdmin_age(int admin_age) {
        Admin_age = admin_age;
    }

    /**
     * 得到管理员性别
     *
     * @return {@link String}
     */
    public String getAdmin_gender() {
        return Admin_gender;
    }

    /**
     * 集管理员性别
     *
     * @param admin_gender 管理员性别
     */
    public void setAdmin_gender(String admin_gender) {
        Admin_gender = admin_gender;
    }

    /**
     * 得到管理员电子邮件
     *
     * @return {@link String}
     */
    public String getAdmin_email() {
        return Admin_email;
    }

    /**
     * 管理员电子邮件
     *
     * @param admin_email 管理员电子邮件
     */
    public void setAdmin_email(String admin_email) {
        Admin_email = admin_email;
    }

    /**
     * 得到管理员一卡通
     *
     * @return {@link String}
     */
    public String getAdmin_idcard() {
        return Admin_idcard;
    }

    /**
     * 管理员一卡通
     *
     * @param admin_idcard 管理员一卡通
     */
    public void setAdmin_idcard(String admin_idcard) {
        Admin_idcard = admin_idcard;
    }
}
