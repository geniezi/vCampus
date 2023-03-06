package DAO.QICQ;

import java.io.Serializable;

/**
 * 文件传输类
 * @author Shuheng_Gu
 * @date 2022/09/03
 */
public class Filetrans implements Serializable {
    /**
     * 源文件路径
     */
    String src;
    /**
     * 目标文件路径
     */
    String dest;
    /**
     * 文件大小
     */
    long filelen;
    /**
     * 内容
     */
    byte[] content;
    /**
     * 文件名
     */
    String name;
    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public long getFilelen() {
        return filelen;
    }

    public void setFilelen(int filelen) {
        this.filelen = filelen;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
