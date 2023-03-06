package DAO.StatusManagement;

import User.Student;
import connection.JDBC_Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author : [Tongwei_L]
 * @version : [v1.0]
 * @description : [一句话描述该类的功能]
 * @createTime : [2022.08.23 15:32]
 */
public class User_SM_utils {

    /**
     * 返回学生信息
     * <p>show 返回学生信息</p>
     * @author : [Tongwei_L]
     * @param username  : 学生idcard
     * @return return :  返回学生对象，可以为NULL
     */
    public static Student returnStudentAllInfo(String username) {
        try {
            Connection connection= JDBC_Connector.ConnectMySQL();                  //连接数据库
            String sql="select * from students where Student_idcard=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            Student s = new Student();
            if (rs.next()){
                s.setStudent_idcard(rs.getString(1));
                s.setStudent_id(rs.getString(2));
                s.setStudent_pwd(rs.getString(3));
                s.setStudent_name(rs.getString(4));
                s.setStudent_age(rs.getInt(5));
                s.setStudent_gender(rs.getString(6));
                s.setStudent_email(rs.getString(7));
                s.setStudent_class(rs.getString(8));
                s.setStudent_money(rs.getDouble(9));
                s.setNation(rs.getString(10));
                s.setBirthday(rs.getString(11));
                s.setNative_place(rs.getString(12));
                s.setMajor(rs.getString(13));
                s.setStudent_type(rs.getString(14));
                s.setDue_graduate_date(rs.getString(15));
                s.setCampus(rs.getString(16));
                s.setSclass(rs.getString(17));
                s.setID(rs.getString(18));
                rs.close();
                ps.close();
                return s;
            }
        //    JDBC_Connector.close(rs, ps, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean search(String id) throws SQLException{
        Connection connection= JDBC_Connector.ConnectMySQL();                  //连接数据库
        String sql="select * from students where Student_idcard=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            rs.close();ps.close();
            return true;
        }
        else {
            rs.close();ps.close();
            return false;
        }
    }
}
