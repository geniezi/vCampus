package DAO.Login;
import java.sql.*;
import User.Student;
import connection.JDBC_Connector;

/**
 * @author : [Tongwei_L]
 * @version : [v1.0]
 * @description : [服务器与数据库的连接类，用来实现学生的相关操作]
 * @createTime : [2022.08.16 22:35]
 */
public class Students_utils {

    /**
     * 检测学生账号
     * <p>show 检测输入学生账号是否存在</p>
     * @author : [Tongwei_L]
     * @param username  : 用户idcard
     * @param userpassword  : 用户密码
     * @return return :  true 表示此账号密码输入正确  false 表示此账号密码输入错误
     */
    public static boolean checkStudentAccount(String username, String userpassword)  {
        try {
            Connection connection=JDBC_Connector.ConnectMySQL();                  //连接数据库
            String sql="select * from students where Student_idcard=? and Student_pwd=?;";
            PreparedStatement  state = connection.prepareStatement(sql);
            state.setString(1,username);
            state.setString(2,userpassword);
            ResultSet resultSet= state.executeQuery();            //执行sql
            if (resultSet.next()) {
                resultSet.close();
                state.close();
            //    connection.close();
                return true;
            }
            else{
                resultSet.close();
                state.close();
            //    connection.close();
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 检测学生姓名
     * <p>show 输出学生账号</p>
     * @author : [Tongwei_L]
     * @param userid  : 用户idcard
     * @return return :  String 为用户姓名
     */
    public static String getname(String userid){
        String name = null;
        try {
            Connection connection=JDBC_Connector.ConnectMySQL();                  //连接数据库
            String sql="select Student_name from students where Student_idcard=?;";
            PreparedStatement state = connection.prepareStatement(sql);
            state.setString(1,userid);
            ResultSet rs= state.executeQuery();            //执行sql
            if (rs.next()) {
                name=rs.getString(1);
            }
            rs.close();
            state.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }

    /**
     * 查找学生账号
     * <p>show 检测输入老师账号是否存在</p>
     * @author : [Tongwei_L]
     * @param username  : 用户idcard
     * @return return :  true 表示此学生存在
     */
    public static boolean findStudentAccount(String username) {
        Boolean re = null;
        try {
            Connection connection = JDBC_Connector.ConnectMySQL();
            String sql = "select * from students where Student_idcard=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 向数据库中添加学生
     * <p>show 向数据库中添加学生用于登录</p>
     * @author : [Tongwei_L]
     * @param s  : 学生实例对象
     * @return return :   true 数据库中添加正确  false 数据库中添加错误
     */
    public static boolean addStudent(Student s) throws SQLException {
        Connection connection=JDBC_Connector.ConnectMySQL();                  //连接数据库
        if(checkStudentAccount(s.getStudent_idcard(),s.getStudent_pwd())){
      //      System.out.println("学生已存在！");
            return false;
        }
        String sql = "insert into students values(?,?,?,?,?,?,?,null,null,null,null,null,null,null,null,null,null,null)";
        //(Student_idcard,Student_id,Student_pwd,Student_name,Student_age,Student_gender,Student_email,Student_class,Student_money)
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1,s.getStudent_idcard());
        ps.setString(2,s.getStudent_id());
        ps.setString(3,s.getStudent_pwd());
        ps.setString(4,s.getStudent_name());
        ps.setInt(5,s.getStudent_age());
        ps.setString(6,s.getStudent_gender());
        ps.setString(7,s.getStudent_email());
        boolean re = ps.executeUpdate()>0;
        //if(re)
       //     System.out.println("学生"+s.getStudent_idcard()+"添加成功！");
        //else
      //      System.out.println("学生添加失败！");
        ps.close();
        //JDBC_Connector.close(null, ps, connection);
        return re;
    }

    /**
     * 向数据库中删除学生
     * <p>show 向数据库中删除学生</p>
     * @author : [Tongwei_L]
     * @param username  : 学生实例对象
     * @return return :   true 数据库中删除正确  false 数据库中删除错误
     */
    public static boolean deleteStudent(String username) throws SQLException {
        Connection connection=JDBC_Connector.ConnectMySQL();
        if(findStudentAccount(username)){
     //       System.out.println("学生不存在！");
            return false;
        }
        String sql = "delete from students where Student_idcard=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1,username);
        boolean re = ps.executeUpdate()>0;
     //   JDBC_Connector.close(null, ps, connection);
        ps.close();
        //if(re)
      //      System.out.println("学生"+username+"删除成功！");
        //else
     //       System.out.println("学生删除失败！");
        ps.close();
        //JDBC_Connector.close(null, ps, connection);
        return re;
    }

    /**
     * 数据库中匹配学生信息
     * <p>show 数据库中匹配学生信息</p>
     * @author : [Tongwei_L]
     * @param s  : 学生实例对象
     * @return return :   true 数据库中匹配学生信息正确  false 数据库中匹配学生信息错误
     */
    public static boolean changeStudentPwd(String username,Student s) throws SQLException {
        Connection connection=JDBC_Connector.ConnectMySQL();
        String sql = "update students SET Student_pwd =? WHERE Student_idcard =" +username;
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1,s.getStudent_pwd());
        boolean re = ps.executeUpdate()>0;
   //     JDBC_Connector.close(null, ps, connection);
        ps.close();
      //  if(re)
      //      System.out.println("学生"+username+"密码修改成功！");
       // else
       //     System.out.println("学生"+username+"密码修改失败！");
        ps.close();
        //JDBC_Connector.close(null, ps, connection);
        return re;
    }

    /**
     * 数据库中修改学生密码
     * <p>show 数据库中修改学生密码</p>
     * @author : [Tongwei_L]
     * @param s  : 老师idcard
     * @return return :   true 数据库中修改学生密码正确  false 数据库中修改学生密码错误
     */
    public static boolean findForgetpwdStudent(Student s) throws SQLException {
        try {
            Connection connection= JDBC_Connector.ConnectMySQL();                  //连接数据库
            Statement state = connection.createStatement();
            String sql="select * from students where Student_idcard='"+s.getStudent_idcard()+"' and Student_email='"+s.getStudent_email()+"'";
            ResultSet resultSet= state.executeQuery(sql);            //执行sql
            while (resultSet.next()) {
                String Student_idcard = resultSet.getString("Student_idcard").trim();
                String Student_email = resultSet.getString("Student_email").trim();
                resultSet.close();
                state.close();
            //    connection.close();
                return Student_idcard.equals(s.getStudent_idcard()) && Student_email.equals(s.getStudent_email());
            }
            resultSet.close();
            state.close();
           // connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 数据库中返回学生信息
     * <p>show 数据库中返回学生信息</p>
     * @author : [Tongwei_L]
     * @param id  : 学生idcard
     * @return return :   true 数据库中返回学生信息正确  false 数据库中返回学生信息错误
     */
    public static Student returninfo(String id)throws SQLException {
        String sql="select * from students where Student_idcard=?;";
        Connection connection=JDBC_Connector.ConnectMySQL();
        PreparedStatement st= connection.prepareStatement(sql);
        st.setString(1,id);
        ResultSet rs=st.executeQuery();
        Student stu=new Student();
        while(rs.next()){
            stu.setStudent_idcard(id);
            stu.setStudent_name(rs.getString("Student_name"));
            stu.setStudent_money(rs.getDouble("Student_money"));
        }
        rs.close();
        st.close();
        //connection.close();
        return stu;
    }

}
