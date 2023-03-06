package DAO.Login;

import ClientToServer.myInfo;
import User.Admin;
import connection.JDBC_Connector;

import java.sql.*;

/**
 * @author : [Tongwei_L]
 * @version : [v1.0]
 * @description : [服务器与数据库的连接类，用来实现管理员的相关操作]
 * @createTime : [2022.08.16 22:36]
 */
public class Admin_utils {

    /**
     * 检测管理员账号
     * <p>show 检测输入管理员账号是否存在</p>
     * @author : [Tongwei_L]
     * @param username  : 用户idcard
     * @param userpassword  : 用户密码
     * @return return :  true 表示此账号密码输入正确  false 表示此账号密码输入错误
     */
    public static boolean checkAdminAccount(String username, String userpassword) {
        try {
            Connection connection= JDBC_Connector.ConnectMySQL();                  //连接数据库
            Statement state = connection.createStatement();
            String sql="select * from admins where Admin_idcard='"+username+"' and Admin_pwd='"+userpassword+"'";
            ResultSet resultSet= state.executeQuery(sql);            //执行sql
            String passWord = "";
            while (resultSet.next()) {
                passWord = resultSet.getString("Admin_pwd").trim();
                if (passWord == userpassword || passWord.equals(userpassword)) {
                    resultSet.close();
                    state.close();
                    return true;
                } else{
                    resultSet.close();
                    state.close();
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 返回管理员的信息
     * <p>show 数据库中搜索管理员信息</p>
     * @author : [Tongwei_L]
     * @param id  : 用户idcard
     * @return return :  Admin 如查找不到返回null
     */
    public static Admin returninfo(String id) throws SQLException {
        String sql="select * from admins where Admin_idcard=?;";
        Connection connection=JDBC_Connector.ConnectMySQL();
        PreparedStatement st= connection.prepareStatement(sql);
        st.setString(1,id);
        ResultSet rs=st.executeQuery();
        Admin admin=new Admin();
        while(rs.next()){
            admin.setAdmin_idcard(id);
            admin.setAdmin_name(rs.getString("Admin_name"));
        }
        rs.close();
        st.close();
        return admin;
    }
}
