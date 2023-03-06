package DAO.Library;

import java.io.Serializable;

/**
 * 登录信息
 *
 * @author Shuheng_Gu
 * @date 2022/09/03
 */
public class Info implements Serializable {
    /**
     * id
     */
    String id; /**
     * 名字
     */
    String name;

    /**
     * 得到id
     *
     * @return {@link String}
     */
    public String getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 得到名字
     *
     * @return {@link String}
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名字
     *
     * @param name 名字
     */
    public void setName(String name) {
        this.name = name;
    }
}
