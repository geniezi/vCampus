package connection;
import java.sql.*;

public class JDBC_Connector {

    static Connection connection;        //创建java程序与数据库的连接对象

    public static void ConnectMySQL() throws SQLException{
//        Statement statement = null;                         //创建执行静态SQL语句的接口对象
//        ResultSet resultSet = null;                         //创建游标对象，返回查询结果集
        String driver = "com.mysql.cj.jdbc.Driver";         //将MySQL数据库驱动名称封装在字符串中
        //指定使用数据库的路径、编码格式、时区，并以字符串进行封装
        String url = "jdbc:mysql://localhost:3306/vcampus?useUnicode=true&characterEncoding=utf8&" +
                "serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
        String user = "root";          //指定登录账户
        String pin = "100905";         //指定账户密码
        //加载数据库驱动
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connection = DriverManager.getConnection(url,user,pin);        //连接数据库
//        statement = connection.createStatement();                      //通过Connection对象获取Statement对象
//        String sql = "select * from students";
//        resultSet = statement.executeQuery(sql);
//        while(resultSet.next()){
//            int id = resultSet.getInt("idstudents");
//            String userName = resultSet.getString("studentsname");
//            String usergender = resultSet.getString("studentsgender");
//            String useremail = resultSet.getString("studentsemail");
//            String useridcard = resultSet.getString("studentsidcard");
//            String userpin = resultSet.getString("studentspin");
//            System.out.println(id+"\t|\t\t"+userName+"\t\t|\t"+usergender+"\t\t|\t"+useremail+"\t\t|\t"+useridcard+"\t\t|\t"+userpin);
//        }
//        if (resultSet!=null)resultSet.close();
//        if (statement!=null)statement.close();
//        if (connection!=null)connection.close();
    }

    public static boolean checkAccount(String username, String userpassword) {
        try {
            ConnectMySQL();                                         //连接数据库
            Statement state = connection.createStatement();
            String sql="select * from students where idstudents='"+username+"' and studentspin='"+userpassword+"'";
            ResultSet resultSet= state.executeQuery(sql);            //执行sql
            String passWord = "";
            while (resultSet.next()) {
                passWord = resultSet.getString("studentspin").trim();
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
}
