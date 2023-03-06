package utils;

import connection.JDBC_Connector;
import net.coobird.thumbnailator.Thumbnails;

import java.io.*;
import java.sql.*;

/**
 * 在数据库保存图像
 *
 * @author Shuheng_Gu
 * @date 2022/09/03
 */
public class Save_image {
    /**
     * 加入图片
     *
     * @param args the input arguments
     * @throws FileNotFoundException the file not found exception
     * @throws SQLException          the sql exception
     */
    public static void main(String[] args) throws FileNotFoundException, SQLException {
     //   JDBC_Connector.first_connect();
        String p="src/image/";
      //  for(int i=41;i<=41;i++)
      //  {
            /*try {
                Thumbnails.of(new File(p+i+".jpg"))
                        .size(250, 250)
                        .toFile(new File(p+i+"_min.jpg"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }*/
            String path = p+"try.png";
         //   System.out.println(path);
            File file = new File(path);
            FileInputStream fis=new FileInputStream(file);
            try {
                Connection conn = JDBC_Connector.ConnectMySQL();
                String sql = "insert into qqimage(id,image) values(?,?);";
                PreparedStatement ps = JDBC_Connector.ConnectMySQL().prepareStatement(sql);
                ps.setString(1, null);
                ps.setBinaryStream(2,fis,(int)file.length());
                int change=ps.executeUpdate();
              //  System.out.println(change);
              //  ps.close();
              //  JDBC_Connector.ConnectMySQL().close();
            } catch (Exception e) {
                e.printStackTrace();
            }
      //  }

    }
}
