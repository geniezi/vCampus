package DAO.Login;

import User.Teacher;
import connection.JDBC_Connector;
import java.sql.*;

/**
 * @author : [Tongwei_L]
 * @version : [v1.0]
 * @description : [服务器与数据库的连接类，用来实现老师的相关操作]
 * @createTime : [2022.08.16 22:36]
 */
public class Teachers_utils {

    /**
     * 检测老师账号
     * <p>show 检测输入老师账号是否存在</p>
     * @author : [Tongwei_L]
     * @param username  : 用户idcard
     * @param userpassword  : 用户密码
     * @return return :  true 表示此账号密码输入正确  false 表示此账号密码输入错误
     */
    public static boolean checkTeacherAccount(String username, String userpassword) {
        try {
            Connection connection= JDBC_Connector.ConnectMySQL();                  //连接数据库
            Statement state = connection.createStatement();
            String sql="select * from teachers where Teacher_idcard='"+username+"' and Teacher_pwd='"+userpassword+"'";
            ResultSet resultSet= state.executeQuery(sql);            //执行sql
            String passWord = "";
            while (resultSet.next()) {
                passWord = resultSet.getString("Teacher_pwd").trim();
                if (passWord == userpassword || passWord.equals(userpassword)) {
                    resultSet.close();
                    state.close();
                  //  connection.close();
                    return true;
                } else{
                    resultSet.close();
                    state.close();
                //    connection.close();
                    return false;
                }

            }
            resultSet.close();
            state.close();
        //    connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 返回老师的信息
     * <p>show 数据库中搜索老师信息</p>
     * @author : [Tongwei_L]
     * @param id  : 用户idcard
     * @return return :  Admin 如查找不到返回null
     */
    public static Teacher returninfo(String id)throws SQLException {
        String sql="select * from teachers where Teacher_idcard=?;";
        Connection connection=JDBC_Connector.ConnectMySQL();
        PreparedStatement st= connection.prepareStatement(sql);
        st.setString(1,id);
        ResultSet rs=st.executeQuery();
        Teacher tea=new Teacher();
        while(rs.next()){
            tea.setTeacher_idcard(id);
            tea.setTeacher_name(rs.getString("Teacher_name"));
            tea.setTeacher_money(rs.getDouble("Teacher_money"));
        }
        rs.close();
        st.close();
        return tea;
    }

    /**
     * 向数据库中添加老师
     * <p>show 向数据库中添加老师用于登录</p>
     * @author : [Tongwei_L]
     * @param s  : 老师实例对象
     * @return return :   true 数据库中添加正确  false 数据库中添加错误
     */
    public static boolean addTeacher(Teacher s) throws SQLException {
        Connection connection=JDBC_Connector.ConnectMySQL();                  //连接数据库
        if(checkTeacherAccount(s.getTeacher_idcard(),s.getTeacher_pwd())){
            return false;
        }
        String sql = "insert into teachers values(?,?,?,?,?,?,?,null,null)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1,s.getTeacher_idcard());
        ps.setString(2,s.getTeacher_id());
        ps.setString(3,s.getTeacher_pwd());
        ps.setString(4,s.getTeacher_name());
        ps.setInt(5,s.getTeacher_age());
        ps.setString(6,s.getTeacher_gender());
        ps.setString(7,s.getTeacher_email());
        boolean re = ps.executeUpdate()>0;
        ps.close();
        return re;
    }

    /**
     * 数据库中匹配老师信息
     * <p>show 数据库中匹配老师信息</p>
     * @author : [Tongwei_L]
     * @param s  : 老师实例对象
     * @return return :   true 数据库中匹配老师信息正确  false 数据库中匹配老师信息错误
     */
    public static boolean findForgetpwdTeacher(Teacher s) throws SQLException {
        try {
            Connection connection= JDBC_Connector.ConnectMySQL();                  //连接数据库
            Statement state = connection.createStatement();
            String sql="select * from teachers where Teacher_idcard='"+s.getTeacher_idcard()+"' and Teacher_email='"+s.getTeacher_email()+"'";
            ResultSet resultSet= state.executeQuery(sql);            //执行sql
            while (resultSet.next()) {
                String Teacher_idcard = resultSet.getString("Teacher_idcard").trim();
                String Teacher_email = resultSet.getString("Teacher_email").trim();
                resultSet.close();
                state.close();
                return Teacher_idcard.equals(s.getTeacher_idcard()) && Teacher_email.equals(s.getTeacher_email());
            }
            resultSet.close();
            state.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 数据库中修改老师密码
     * <p>show 数据库中修改老师密码</p>
     * @author : [Tongwei_L]
     * @param username  : 老师idcard
     * @param t  : 老师实例对象
     * @return return :   true 数据库中修改老师密码正确  false 数据库中修改老师密码错误
     */
    public static boolean changeTeacherPwd(String username, Teacher t) throws SQLException {
        Connection connection=JDBC_Connector.ConnectMySQL();
        String sql = "update teachers SET Teacher_pwd =? WHERE Teacher_idcard =" +username;
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1,t.getTeacher_pwd());
        boolean re = ps.executeUpdate()>0;
        ps.close();
        //if(re)
        //    System.out.println("教师"+username+"密码修改成功！");
        //else
        //    System.out.println("教师"+username+"密码修改失败！");
        ps.close();
        return re;
    }

}
