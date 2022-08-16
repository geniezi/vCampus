package DAO.Login;

import User.Student;
import User.Teacher;
import connection.JDBC_Connector;

import java.sql.*;

/**
 * @author : [Tongwei_L]
 * @version : [v1.0]
 * @description : [一句话描述该类的功能]
 * @createTime : [2022.08.15 17:53]
 */
public class Teacher_utils {

    static Connection connection;

    static {
        try {
            connection = JDBC_Connector.ConnectMySQL();       //连接数据库
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkTeachersAccount(String username, String userpassword) {
        try {
            Statement state = connection.createStatement();
            String sql="select * from students where Teacher_idcard='"+username+"' and Teacher_pwd='"+userpassword+"'";
            ResultSet resultSet= state.executeQuery(sql);            //执行sql
            String passWord = "";
            while (resultSet.next()) {
                passWord = resultSet.getString("Teacher_pwd").trim();
                if (passWord == userpassword || passWord.equals(userpassword)) {
                    return true;
                } else
                    return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean addTeachersAccount(Teacher s) {
        try {
            String sql="insert into teachers (Teacher_idcard,Teacher_id,Teacher_pwd,Teacher_name," +
                    "Teacher_age,Teacher_gender,Teacher_email,Teacher_title,Teacher_money,Teacher_courses)" +
                    "values(?,?,null,null,null,null,null,null,null,null)" ;
            PreparedStatement ps=connection.prepareStatement(sql);
            ps.setString(1,s.getTeacher_idcard());
            ps.setString(2,s.getTeacher_pwd());
            int count= ps.executeUpdate();                   //执行sql
            if (count>0){
                System.out.println("教师"+s.getTeacher_idcard()+"添加成功!");
                return true;
            }else {
                System.out.println("教师添加失败!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

//    public static boolean changeStudentsAccount(String username) {
//        try {
//            JDBC_Connector.ConnectMySQL();                                         //连接数据库
//            Statement state = connection.createStatement();
//            String sql="insert into teachers (Teacher_id,Teacher_name,Teacher_pwd,Teacher_age,Teacher_gender," +
//                    "Teacher_email,Teacher_idcard,Teacher_title,Teacher_money,Teacher_courses) " +
//                    "values ('" + username + "','" + userpassword + "'," + userpassword + ",\" + userpassword + \",\" + userpassword + \",\" + userpassword + \",\" + userpassword + \",\" + userpassword + \",\" + userpassword + \",\" + userpassword + \")";
//            int count = state.executeUpdate(sql);                   //执行sql
//            if (count>0){
//                System.out.println("添加成功");
//            }else {
//                System.out.println("添加失败");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

    public static void main(String[]s){
        Teacher st= new Teacher();
        st.setTeacher_idcard("555555");
        st.setTeacher_pwd("111111");
        addTeachersAccount(st);
    }
}
