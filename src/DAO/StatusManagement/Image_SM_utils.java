package DAO.StatusManagement;

import java.sql.Blob;
import connection.JDBC_Connector;
import utils.Image_utils;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Image_SM_utils {
    // 将图片插入数据库
    public static void sendImageDB(String path,String id,String name) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        FileInputStream in = null;
        try {
            in = Image_utils.readImage(path);
            conn = JDBC_Connector.ConnectMySQL();
            String sql = "insert into userphotos (Photo_id,Photo_name,photo) values(?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2,name);
            ps.setBinaryStream(3, in, in.available());
            int count = ps.executeUpdate();
            if (count > 0) {
           //     System.out.println("插入成功！");
            } else {
            //    System.out.println("插入失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           // JDBC_Connector.close(null,ps,conn);
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
    // 读取数据库中图片
    public static byte[] readDBImage(String id) throws SQLException {
        /*String path = "src/image/temp.jpg";
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBC_Connector.ConnectMySQL();
            String sql = "select * from userphotos where Photo_id =?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Blob picture= rs.getBlob("photo");
                InputStream in = picture.getBinaryStream();
              //  OutputStream out = new FileOutputStream(file);
                int len = 0;
                byte[] buf = new byte[(int)picture.length()];
                while ((len = in.read(buf)) != -1) {
                 //   out.write(buf, 0, len);
                }
              //  System.out.println("图片读取成功！");
             //   JDBC_Connector.close(rs,ps,conn);
                rs.close();
                ps.close();
                return buf;
            }
            else
            {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}