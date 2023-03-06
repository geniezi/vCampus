package DAO.StatusManagement;

import User.Student;
import connection.JDBC_Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author : [Tongwei_L]
 * @version : [v1.0]
 * @description : [一句话描述该类的功能]
 * @createTime : [2022.08.23 15:33]
 */
public class Admin_SM_utils {

    /**
     * 管理员修改学生信息
     * <p>show 管理员修改学生信息</p>
     * @author : [Tongwei_L]
     * @param s  : 添加的商学生对象实例
     * @return return :  true 修改成功  false 表示修改失败
     */
    public static boolean changeStudentInfo(Student s) throws SQLException {
        Connection connection= JDBC_Connector.ConnectMySQL();
        String sql = "update students set Student_id= ?,Student_name= ?,Student_age= ?,Student_gender= ?,Student_email= ?,Student_class= ?,Student_nation= ?,Student_birthday= ?,Student_native_place= ?,Student_major= ?,Student_type= ?,Student_Due_graduate_date= ?,Student_Campus= ?,Student_Sclass= ?,Students_shenfenzheng= ? where Student_idcard=? ";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1,s.getStudent_id());
        ps.setString(2,s.getStudent_name());
        ps.setInt(3,s.getStudent_age());
        ps.setString(4,s.getStudent_gender());
        ps.setString(5,s.getStudent_email());
        ps.setString(6,s.getStudent_class());
        ps.setString(7,s.getNation());
        ps.setString(8,s.getBirthday());
        ps.setString(9,s.getNative_place());
        ps.setString(10,s.getMajor());
        ps.setString(11,s.getStudent_type());
        ps.setString(12,s.getDue_graduate_date());
        ps.setString(13,s.getCampus());
        ps.setString(14,s.getSclass());
        ps.setString(15,s.getID());
        ps.setString(16,s.getStudent_idcard());
        boolean re = ps.executeUpdate()>0;
        //JDBC_Connector.close(null, ps, connection);
        //if(re)
       //     System.out.println("学生"+s.getStudent_idcard()+"信息修改成功！");
        //else
         //   System.out.println("学生"+s.getStudent_idcard()+"信息修改失败！");
        //JDBC_Connector.close(null, ps, connection);
        ps.close();
        return re;
    }


}
