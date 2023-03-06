package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * The type Image utils.
 *
 * @author : [Tongwei_L]
 * @version : [v1.0]
 * @description : [保存图片到数据库]
 * @createTime : [2022.08.24 08:21]
 */
public class Image_utils {
    /**
     * Read image file input stream.
     *
     * @param path the path
     * @return the file input stream
     * @throws IOException the io exception
     */
// 读取本地图片获取输入流
    public static FileInputStream readImage(String path) throws IOException {
        return new FileInputStream(new File(path));
    }

    /**
     * Read bin 2 image.
     *
     * @param in the in
     * @param id the id
     */
// 读取表中图片获取输出流
    public static void readBin2Image(InputStream in,String id) {
        String path = ".\\src\\image";
        File file = new File(path);
        if (!file.exists()) {
            new File(path).mkdir();
        }
        FileOutputStream fos = null;
        File fout=new File(path+"\\"+id+".jpg ");
        try {
            fos = new FileOutputStream(fout);
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = in.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != fos) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
