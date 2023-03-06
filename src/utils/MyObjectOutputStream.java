package utils;
import java.io.*;

/**
 * The type My object output stream.
 *
 * @author : [Tongwei_L]
 * @version : [v1.0]
 * @description : [一句话描述该类的功能]
 * @createTime : [2022.08.26 17:55]
 */
public class MyObjectOutputStream extends ObjectOutputStream {

    /**
     * Instantiates a new My object output stream.
     *
     * @param out the out
     * @throws IOException the io exception
     */
    public MyObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }

    @Override
    protected void writeStreamHeader() throws IOException {
        //重写读取头部信息方法：不写入头部信息
        super.reset();
    }
}

