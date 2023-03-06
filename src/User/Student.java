package User;
import java.io.Serializable;

/**
 * 学生
 *
 * @author 28468
 * @version : [v1.0]
 * @description : [学生类]
 * @createTime : [2022.08.14 19:42]
 * @date 2022/09/03
 */
public class Student implements Serializable {

    /**
     * 学生一卡通
     */
    String Student_idcard;   //一卡通
    /**
     * 学生pwd
     */
    String Student_pwd;      //密码
    /**
     * 学生证
     */
    String Student_id;       //学（工）号
    /**
     * 学生名字
     */
    String Student_name;     //学生姓名
    /**
     * 学生年龄
     */
    int Student_age;         //年龄
    /**
     * 学生性别
     */
    String Student_gender;   //性别
    /**
     * 学生电子邮件
     */
    String Student_email;    //邮箱
    /**
     * 学生类
     */
    String Student_class;       //学生年级
    /**
     * sclass
     */
    String Sclass;              //学生班级
    /**
     * 学生钱
     */
    double Student_money;    //用户余额

    /**
     * 国家
     */
    String nation;           //民族
    /**
     * 生日
     */
    String birthday;         //出生日期
    /**
     * id
     */
    String ID;               //身份证号
    /**
     * 籍贯
     */
    String Native_place;     //籍贯
    /**
     * 主要
     */
    String major;            //专业
    /**
     * 学生类型
     */
    String Student_type;             //学生类别：本科生，研究生，博士生
    /**
     * 由于毕业日期
     */
    String Due_graduate_date;       //预计毕业日期
    /**
     * 校园
     */
    String Campus;          //校区

    /**
     * 得到籍贯
     *
     * @return {@link String}
     */
    public String getNative_place() {
        return Native_place;
    }

    /**
     * 设置籍贯
     *
     * @param native_place 籍贯
     */
    public void setNative_place(String native_place) {
        Native_place = native_place;
    }

    /**
     * 得到主要
     *
     * @return {@link String}
     */
    public String getMajor() {
        return major;
    }

    /**
     * 设置主要
     *
     * @param major 主要
     */
    public void setMajor(String major) {
        this.major = major;
    }

    /**
     * 获得国家
     *
     * @return {@link String}
     */
    public String getNation() {
        return nation;
    }

    /**
     * 集国家
     *
     * @param nation 国家
     */
    public void setNation(String nation) {
        this.nation = nation;
    }

    /**
     * 得到生日
     *
     * @return {@link String}
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * 设置生日
     *
     * @param birthday 生日
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
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
     * 组id
     *
     * @param ID id
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * 获得学生证
     *
     * @return {@link String}
     */
    public String getStudent_id() {
        return Student_id;
    }

    /**
     * 将学生证
     *
     * @param student_id 学生证
     */
    public void setStudent_id(String student_id) {
        Student_id = student_id;
    }

    /**
     * 得到学生名字
     *
     * @return {@link String}
     */
    public String getStudent_name() {
        return Student_name;
    }

    /**
     * 设置学生名字
     *
     * @param student_name 学生名字
     */
    public void setStudent_name(String student_name) {
        Student_name = student_name;
    }

    /**
     * 让学生pwd
     *
     * @return {@link String}
     */
    public String getStudent_pwd() {
        return Student_pwd;
    }

    /**
     * 设置学生pwd
     *
     * @param student_pwd 学生pwd
     */
    public void setStudent_pwd(String student_pwd) {
        Student_pwd = student_pwd;
    }

    /**
     * 让学生时代
     *
     * @return int
     */
    public int getStudent_age() {
        return Student_age;
    }

    /**
     * 设定学生年龄
     *
     * @param student_age 学生年龄
     */
    public void setStudent_age(int student_age) {
        Student_age = student_age;
    }

    /**
     * 得到学生性别
     *
     * @return {@link String}
     */
    public String getStudent_gender() {
        return Student_gender;
    }

    /**
     * 设置学生性别
     *
     * @param student_gender 学生性别
     */
    public void setStudent_gender(String student_gender) {
        Student_gender = student_gender;
    }

    /**
     * 得到学生电子邮件
     *
     * @return {@link String}
     */
    public String getStudent_email() {
        return Student_email;
    }

    /**
     * 设置学生电子邮件
     *
     * @param student_email 学生电子邮件
     */
    public void setStudent_email(String student_email) {
        Student_email = student_email;
    }

    /**
     * 得到学生一卡通
     *
     * @return {@link String}
     */
    public String getStudent_idcard() {
        return Student_idcard;
    }

    /**
     * 集学生一卡通
     *
     * @param student_idcard 学生一卡通
     */
    public void setStudent_idcard(String student_idcard) {
        Student_idcard = student_idcard;
    }

    /**
     * 让学生类
     *
     * @return {@link String}
     */
    public String getStudent_class() {
        return Student_class;
    }

    /**
     * 设置学生类
     *
     * @param student_class 学生类
     */
    public void setStudent_class(String student_class) {
        Student_class = student_class;
    }

    /**
     * 得到学生钱
     *
     * @return double
     */
    public double getStudent_money() {
        return Student_money;
    }

    /**
     * 设置学生钱
     *
     * @param student_money 学生钱
     */
    public void setStudent_money(double student_money) {
        Student_money = student_money;
    }

    /**
     * 让学生类型
     *
     * @return {@link String}
     */
    public String getStudent_type() {
        return Student_type;
    }

    /**
     * 设置学生类型
     *
     * @param student_type 学生类型
     */
    public void setStudent_type(String student_type) {
        Student_type = student_type;
    }

    /**
     * 得到sclass
     *
     * @return {@link String}
     */
    public String getSclass() {
        return Sclass;
    }

    /**
     * 设置sclass
     *
     * @param sclass sclass
     */
    public void setSclass(String sclass) {
        Sclass = sclass;
    }

    /**
     * 得到由于毕业日期
     *
     * @return {@link String}
     */
    public String getDue_graduate_date() {
        return Due_graduate_date;
    }

    /**
     * 设置由于毕业日期
     *
     * @param due_graduate_date 由于毕业日期
     */
    public void setDue_graduate_date(String due_graduate_date) {
        Due_graduate_date = due_graduate_date;
    }

    /**
     * 获得校园
     *
     * @return {@link String}
     */
    public String getCampus() {
        return Campus;
    }

    /**
     * 集校园
     *
     * @param campus 校园
     */
    public void setCampus(String campus) {
        Campus = campus;
    }
}
