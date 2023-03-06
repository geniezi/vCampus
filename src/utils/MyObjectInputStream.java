package utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

/**
 * 重写ObjectInputStream 防止通信流读取错误
 */
public class MyObjectInputStream extends ObjectInputStream {
    /**
     * Instantiates a new My object input stream.
     *
     * @param in the in
     * @throws IOException the io exception
     */
    public MyObjectInputStream(InputStream in) throws IOException {
        super(in);
    }

    @Override
    protected void readStreamHeader() throws IOException {
        //重写读取头部信息方法：什么也不做
    }
}
