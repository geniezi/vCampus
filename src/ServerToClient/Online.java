package ServerToClient;

import java.io.Serial;
import java.io.Serializable;

/**
 * 在线
 * 在线列表
 *
 * @author Shuheng_Gu
 * @date 2022/09/03
 */
public class Online implements Serializable {
    /**
     * id
     */
    private String id;
    /**
     * 类型
     */
    private int type; //0=student,1=teacher,2=admin

    /**
     * 在线
     *
     * @param id   id
     * @param type 类型
     */
    public Online(String id, int type) {
        this.id = id;
        this.type = type;
    }

    /**
     * 得到id
     *
     * @return {@link String}
     */
    public String getId() {
        return id;
    }

    /**
     * 得到id
     *
     * @param id id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 得到类型
     *
     * @return int
     */
    public int getType() {
        return type;
    }

    /**
     * 设置类型
     *
     * @param type 类型
     */
    public void setType(int type) {
        this.type = type;
    }
}
