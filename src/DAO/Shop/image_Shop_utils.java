package DAO.Shop;

import connection.JDBC_Connector;
import utils.Image_utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author : [Tongwei_L]
 * @version : [v1.0]
 * @description : [一句话描述该类的功能]
 * @createTime : [2022.08.28 20:52]
 */
public class image_Shop_utils {

    /**
     * 将图片插入数据库
     * <p>show 将图片插入数据库</p>
     * @author : [Tongwei_L]
     * @param path  : 路径
     * @param id  : 图片id
     */
    public static void sendImageDB(String path,int id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        FileInputStream in = null;
        try {
            in = Image_utils.readImage(path);
            conn = JDBC_Connector.ConnectMySQL();
            String sql = "insert into productphotos (idproductphotos, photo) values(?,?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setBinaryStream(2, in, in.available());
            int count = ps.executeUpdate();
            if (count > 0) {
           //     System.out.println("插入成功！");
            } else {
            //    System.out.println("插入失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
         //   JDBC_Connector.close(null,ps,conn);
            ps.close();
            if (null != ps) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 读取数据库图片
     * <p>show 读取数据库图片</p>
     * @author : [Tongwei_L]
     * @param id  : 图片id
     */
    public static boolean readDBImage(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBC_Connector.ConnectMySQL();
            String sql = "select * from productphotos where idproductphotos =?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                InputStream in = rs.getBinaryStream("photo");
                Image_utils.readBin2Image(in,String.valueOf(id));
            //    System.out.println("图片读取成功！");
             //   JDBC_Connector.close(rs,ps,conn);
                ps.close();
                rs.close();
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
