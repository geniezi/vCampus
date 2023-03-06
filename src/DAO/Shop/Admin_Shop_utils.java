package DAO.Shop;

import connection.JDBC_Connector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : [Tongwei_L]
 * @version : [v1.0]
 * @description : [管理员的操作]
 * @createTime : [2022.08.19 15:31]
 */
public class Admin_Shop_utils {

    /**
     * 管理员添加商品
     * <p>show 管理员添加商品</p>
     * @author : [Tongwei_L]
     * @param p  : 添加的商品对象实例
     * @return return :  true 添加成功  false 表示添加失败
     */
    public static boolean addProduct(Product p) throws SQLException {
        Connection connection=JDBC_Connector.ConnectMySQL();                  //连接数据库
        String sql = "insert into products values(?,?,?,?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1,p.getProduct_name());
        ps.setInt(2,p.getProduct_id());
        ps.setDouble(3,p.getProduct_price());
        ps.setInt(4,p.getProduct_currentNumbers());
        ps.setInt(5,p.getProduct_sumNumbers());
        ps.setBoolean(6,p.getProduct_takeaway());
        ps.setInt(7,p.getProduct_toshop());
        ps.setString(8,p.getProduct_type());
        boolean re = ps.executeUpdate()>0;
       // if(re)
        //    System.out.println("商品"+p.getProduct_name()+"添加成功！");
        //else
         //   System.out.println("商品添加失败！");
        ps.close();
       // JDBC_Connector.close(null, ps, connection);
        return re;
    }

    /**
     * 管理员删除商品
     * <p>show 管理员删除商品</p>
     * @author : [Tongwei_L]
     * @param id  : 管理员删除商品的id
     * @return return :  true 删除成功  false 表示删除失败
     */
    public static boolean deleteProduct(int id) throws SQLException {
        Connection connection=JDBC_Connector.ConnectMySQL();
        String sql = "delete from products where Product_id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1,id);
        boolean re = ps.executeUpdate()>0;
        if(re)
            System.out.println("删除成功！");
        else
            System.out.println("删除失败！");
        ps.close();
      //  JDBC_Connector.close(null, ps, connection);
        return re;
    }

}
