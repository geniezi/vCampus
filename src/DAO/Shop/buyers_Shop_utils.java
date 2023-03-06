package DAO.Shop;
import connection.JDBC_Connector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static DAO.Shop.image_Shop_utils.readDBImage;

/**
 * @author : [Tongwei_L]
 * @version : [v1.0]
 * @description : [商店与数据库连接类]
 * @createTime : [2022.08.19 15:31]
 */

public class buyers_Shop_utils {

    /**
     * 连接数据库
     */
    public static Connection connection;    //连接数据库

    static {
        try {
            connection = JDBC_Connector.ConnectMySQL();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 用户查找商品
     * <p>show 用户查找商品</p>
     * @author : [Tongwei_L]
     * @param product_name  : 查找的商品名字
     * @return return :  返回查找到的商品，可以为null
     */
    public static List<Product> checkProduct(String product_name) throws SQLException {
        List<Product> list = new ArrayList<>();
        Statement state = connection.createStatement();
        String sql="select * from products WHERE Product_name LIKE '%" + product_name + "%' ";
        ResultSet rs= state.executeQuery(sql);            //执行sql
        while(rs.next()) {
            Product temp = new Product();
            temp.setProduct_name(rs.getString("product_name"));
            temp.setProduct_id(rs.getInt("Product_id"));
            temp.setProduct_price(rs.getDouble("Product_price"));
            temp.setProduct_currentNumbers(rs.getInt("Product_currentNumbers"));
            temp.setProduct_takeaway(rs.getBoolean("Product_takeaway"));
            temp.setProduct_sumNumbers(rs.getInt("Product_sumNumbers"));
            temp.setProduct_type(rs.getString("Product_type"));
            temp.setProduct_toshop(rs.getInt("Product_toshop"));
            list.add(temp);
        }
        return list;
    }

    /**
     * 用户查找已购信息
     * <p>show 用户查找商品</p>
     * @author : [Tongwei_L]
     * @param idcard  : 用户id
     * @return return :  返回已购商品，可以为null
     */
    public static List<ProductPair> getBuyedandNum(String idcard) throws SQLException {
        List<ProductPair> s = new ArrayList<>();
        Statement state = connection.createStatement();
        String sql= "select * from buyedproducted where Stu_Tea_id= "+idcard;
        ResultSet rs= state.executeQuery(sql);            //执行sql
        while(rs.next()) {
            ProductPair p = new ProductPair();
            p.setId(rs.getInt("buyedProductedid"));
            p.setNum(rs.getInt("buyedproductedNum"));
            s.add(p);
        }
        return s;
    }

    /**
     * 用户查找购物车商品信息
     * <p>show 用户查找购物车商品</p>
     * @author : [Tongwei_L]
     * @param idcard  : 用户id
     * @return return :  返回购物车商品，可以为null
     */
    public static List<ProductPair> getReadytoBuyandNum(String idcard) throws SQLException {
        List<ProductPair> s = new ArrayList<>();
        Statement state = connection.createStatement();
        String sql= "select * from readytobuyproducts where Stu_Tea_id= "+idcard;
        ResultSet rs= state.executeQuery(sql);            //执行sql
        while(rs.next()) {
            ProductPair p = new ProductPair();
            p.setId(rs.getInt("product_id"));
            p.setNum(rs.getInt("product_num"));
            s.add(p);
        }
        return s;
    }

    /**
     * 用户查找商店所有商品信息
     * <p>show 用户查找商店所有商品信息</p>
     * @author : [Tongwei_L]
     * @return return :  返回商店所有商品，可以为null
     */
    public static List<Product> returnAllProduct() throws SQLException {
        List<Product> list = new ArrayList<>();
        Statement state = connection.createStatement();
        String sql="select * from products";
        ResultSet rs= state.executeQuery(sql);            //执行sql
        while(rs.next()) {
            Product temp = new Product();
            temp.setProduct_name(rs.getString("Product_name"));
            temp.setProduct_id(rs.getInt("Product_id"));
            temp.setProduct_price(rs.getDouble("Product_price"));
            temp.setProduct_currentNumbers(rs.getInt("Product_currentNumbers"));
            temp.setProduct_takeaway(rs.getBoolean("Product_takeaway"));
            temp.setProduct_sumNumbers(rs.getInt("Product_sumNumbers"));
            temp.setProduct_type(rs.getString("Product_type"));
            temp.setProduct_toshop(rs.getInt("Product_toshop"));
            list.add(temp);
            readDBImage(rs.getInt("Product_id"));
        }
        return list;
    }

    /**
     * 用户查找商店某一类商品信息
     * <p>show 用户查找商店某一类商品信息</p>
     * @author : [Tongwei_L]
     * @param type  : 商品类型
     * @return return :  返回商店某一类商品，可以为null
     */
    public static List<Product> findTypeProduct(String type) throws SQLException {
        List<Product> list = new ArrayList<>();
        Statement state = connection.createStatement();
        String sql="select * from products WHERE Product_type LIKE '%" + type + "%' ";
        ResultSet rs= state.executeQuery(sql);            //执行sql
        while(rs.next()) {
            Product temp = new Product();
            temp.setProduct_name(rs.getString("product_name"));
            temp.setProduct_id(rs.getInt("Product_id"));
            temp.setProduct_price(rs.getDouble("Product_price"));
            temp.setProduct_currentNumbers(rs.getInt("Product_currentNumbers"));
            temp.setProduct_takeaway(rs.getBoolean("Product_takeaway"));
            temp.setProduct_sumNumbers(rs.getInt("Product_sumNumbers"));
            temp.setProduct_type(rs.getString("Product_type"));
            temp.setProduct_toshop(rs.getInt("Product_toshop"));
            list.add(temp);
        }
        return list;
    }

    /**
     * 查找商店某一个商品信息
     * <p>show 查找商店某一个商品信息</p>
     * @author : [Tongwei_L]
     * @param id  : 商品id
     * @return return :  返回商店某一个商品，可以为null
     */
    public static Product checkCertainProduct(int id) throws SQLException {
        Product temp = new Product();
        String sql="select * from products where Product_id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            temp.setProduct_name(rs.getString("Product_name"));
            temp.setProduct_id(rs.getInt("Product_id"));
            temp.setProduct_price(rs.getDouble("Product_price"));
            temp.setProduct_currentNumbers(rs.getInt("Product_currentNumbers"));
            temp.setProduct_takeaway(rs.getBoolean("Product_takeaway"));
            temp.setProduct_sumNumbers(rs.getInt("Product_sumNumbers"));
            temp.setProduct_type(rs.getString("Product_type"));
            temp.setProduct_toshop(rs.getInt("Product_toshop"));
        }else {
            return null;
        }
        return temp;
    }

    /**
     * 学生购买商店某一个商品
     * <p>show 学生购买商店某一个商品</p>
     * @author : [Tongwei_L]
     * @param idcard  : 用户id
     * @param id  : 商品id
     * @param num  : 用户购买数量
     * @param money  : 用户现在的余额
     * @return return :  购买情况：可以失败或者成功
     */
    public static String buyCertainProduct(String idcard, int id, int num, double money) throws SQLException {
        String sql="select * from products where Product_id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        int current = 0;
        if (rs.next()) {
            current=rs.getInt("Product_currentNumbers");
        }
        if(current<num){
       //     System.out.println("数量不够");
            return "数量不够";
        }
        else{
            sql = "update students SET Student_money =? WHERE Student_idcard =" + idcard;
            ps = connection.prepareStatement(sql);
            ps.setDouble(1,money);
            boolean re = ps.executeUpdate()>0;
            if(re){
                sql = "update products SET Product_currentNumbers =? WHERE Product_id =" + id;
                ps = connection.prepareStatement(sql);
                ps.setInt(1,current-num);
                re = ps.executeUpdate()>0;
                if(re){
          //          System.out.println("商品数量更新成功");
                }else {
            //        System.out.println("商品数量更新失败");
                    return "商品数量更新失败";
                }
            }else{
           //     System.out.println("更新钱失败");
                return "更新钱失败";
            }
        }
        addToHaveShopped(idcard,id,num);
        return "购买成功";
    }

    /**
     * 教师购买商店某一个商品
     * <p>show 教师购买商店某一个商品</p>
     * @author : [Tongwei_L]
     * @param idcard  : 教师id
     * @param id  : 商品id
     * @param num  : 用户购买数量
     * @param money  : 教师现在的余额
     * @return return :  购买情况：可以失败或者成功
     */
    public static String buyCertainProduct_Teacher(String idcard, int id, int num, double money) throws SQLException {
        String sql="select * from products where Product_id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        int current = 0;
        if (rs.next()) {
            current=rs.getInt("Product_currentNumbers");
        }
        if(current<num){
          //  System.out.println("数量不够");
            return "数量不够";
        }
        else{
            sql = "update teachers SET Teacher_money =? WHERE Teacher_idcard =" + idcard;
            ps = connection.prepareStatement(sql);
            ps.setDouble(1,money);
            boolean re = ps.executeUpdate()>0;
            if(re){
                sql = "update products SET Product_currentNumbers =? WHERE Product_id =" + id;
                ps = connection.prepareStatement(sql);
                ps.setInt(1,current-num);
                re = ps.executeUpdate()>0;
                if(re){
            //        System.out.println("商品数量更新成功");
                }else {
             //       System.out.println("商品数量更新失败");
                    return "商品数量更新失败";
                }
            }else{
            //    System.out.println("更新钱失败");
                return "更新钱失败";
            }
        }
        addToHaveShopped(idcard,id,num);
        return "购买成功";
    }

    /**
     * 用户添加购物车
     * <p>show 用户添加购物车</p>
     * @author : [Tongwei_L]
     * @param idcard  : 用户id
     * @param id  : 商品id
     * @param num  : 用户添加数量
     * @return return :  添加购物车情况：false失败 ture成功
     */
    public static boolean addToShopCar(String idcard, int id, int num) throws SQLException {
        String sql="insert into readytobuyproducts values(?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps = connection.prepareStatement(sql);
        ps.setString(1, idcard);
        ps.setInt(2,id);
        ps.setInt(3,num);
        boolean re = ps.executeUpdate()>0;
        //if(re)
         //   System.out.println("添加成功！");
        //else
         //   System.out.println("添加失败！");
        return re;
    }

    /**
     * 用户购买商品
     * <p>show 用户购买商品</p>
     * @author : [Tongwei_L]
     * @param idcard  : 用户id
     * @param id  : 商品id
     * @param num  : 用户购买商品数量
     * @return return :  添加到已购商品情况：false失败 ture成功
     */
    public static boolean addToHaveShopped(String idcard, int id, int num) throws SQLException {
        String sql="insert into buyedproducted values(?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, idcard);
        ps.setInt(2,id);
        ps.setInt(3,num);
        boolean re = ps.executeUpdate()>0;
       // if(re)
         //   System.out.println("添加成功！");
       // else
         //   System.out.println("添加失败！");
        return re;
    }

    /**
     * 用户从购物车删除商品
     * <p>show 用户从购物车删除商品</p>
     * @author : [Tongwei_L]
     * @param idcard  : 用户id
     * @param id  : 商品id
     * @param num  : 用户删除商品数量
     * @return return :  删除购物车商品情况：false失败 ture成功
     */
    public static boolean deleteShopCar(String idcard, int id, int num) throws SQLException {
        String sql="delete from readytobuyproducts where (Stu_Tea_id=? and product_id=?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1,idcard);
        ps.setInt(2,id);
        boolean re = ps.executeUpdate()>0;
        //if(re)
       //     System.out.println("删除成功！");
        //else
        //    System.out.println("删除失败！");
        return re;
    }

    /**
     * 得到学生余额
     * <p>show 得到学生余额</p>
     * @author : [Tongwei_L]
     * @param idcard  : 用户id
     * @return return :  返回学生余额
     */
    public static double getMoney(String idcard) throws SQLException {
        String sql="select * from students where Student_idcard=? ";
        PreparedStatement st= connection.prepareStatement(sql);
        st.setString(1, idcard);
        ResultSet rs=st.executeQuery();
        double money = 0;
        while(rs.next()){
            money=rs.getDouble("Student_money");
        }
        return money;
    }

    /**
     * 得到老师余额
     * <p>show 得到学生余额</p>
     * @author : [Tongwei_L]
     * @param idcard  : 用户id
     * @return return :  返回老师余额
     */
    public static double getMoney_teacher(String idcard) throws SQLException {
        String sql="select * from teachers where Teacher_idcard=? ";
        PreparedStatement st= connection.prepareStatement(sql);
        st.setString(1, idcard);
        ResultSet rs=st.executeQuery();
        double money = 0;
        while(rs.next()){
            money=rs.getDouble("Teacher_money");
        }
        return money;
    }

}
