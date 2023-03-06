package DAO.Shop;

import java.io.Serializable;

/**
 * The type Product pair.
 *
 * @author : [Tongwei_L]
 * @version : [v1.0]
 * @description : [商店已购信息以及准备购买物品信息类]
 * @createTime : [2022.08.30 20:30]
 */
public class ProductPair implements Serializable {

    /**
     * 商品id
     */
    public int id;

    /**
     * 商品购买数量
     */
    public int num;

    /**
     * 返回学生id
     * <p>返回学生id</p>
     *
     * @return return :  id为学生id
     * @author : [Tongwei_L]
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets num.
     *
     * @return the num
     */
    public int getNum() {
        return num;
    }

    /**
     * Sets num.
     *
     * @param num the num
     */
    public void setNum(int num) {
        this.num = num;
    }
}
