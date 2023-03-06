package DAO.Shop;

import java.io.Serializable;

/**
 * The type Product.
 *
 * @author : [Tongwei_L]
 * @version : [v1.0]
 * @description : [商店中的商品类]
 * @createTime : [2022.08.19 15:55]
 */
public class Product implements Serializable {
    /**
     * The Product name.
     */
    String Product_name;
    /**
     * The Product id.
     */
    int Product_id;
    /**
     * The Product price.
     */
    double Product_price;
    /**
     * The Product current numbers.
     */
    int Product_currentNumbers;
    /**
     * The Product sum numbers.
     */
    int Product_sumNumbers;
    /**
     * The Product takeaway.
     */
    Boolean Product_takeaway;
    /**
     * The Product toshop.
     */
    int Product_toshop;
    /**
     * The Product type.
     */
    String Product_type;

    /**
     * Gets product name.
     *
     * @return the product name
     */
    public String getProduct_name() {
        return Product_name;
    }

    /**
     * Sets product name.
     *
     * @param product_name the product name
     */
    public void setProduct_name(String product_name) {
        Product_name = product_name;
    }

    /**
     * Gets product id.
     *
     * @return the product id
     */
    public int getProduct_id() {
        return Product_id;
    }

    /**
     * Sets product id.
     *
     * @param product_id the product id
     */
    public void setProduct_id(int product_id) {
        Product_id = product_id;
    }

    /**
     * Gets product price.
     *
     * @return the product price
     */
    public double getProduct_price() {
        return Product_price;
    }

    /**
     * Sets product price.
     *
     * @param product_price the product price
     */
    public void setProduct_price(double product_price) {
        Product_price = product_price;
    }

    /**
     * Gets product current numbers.
     *
     * @return the product current numbers
     */
    public int getProduct_currentNumbers() {
        return Product_currentNumbers;
    }

    /**
     * Sets product current numbers.
     *
     * @param product_currentNumbers the product current numbers
     */
    public void setProduct_currentNumbers(int product_currentNumbers) {
        Product_currentNumbers = product_currentNumbers;
    }

    /**
     * Gets product sum numbers.
     *
     * @return the product sum numbers
     */
    public int getProduct_sumNumbers() {
        return Product_sumNumbers;
    }

    /**
     * Sets product sum numbers.
     *
     * @param product_sumNumbers the product sum numbers
     */
    public void setProduct_sumNumbers(int product_sumNumbers) {
        Product_sumNumbers = product_sumNumbers;
    }

    /**
     * Gets product takeaway.
     *
     * @return the product takeaway
     */
    public Boolean getProduct_takeaway() {
        return Product_takeaway;
    }

    /**
     * Sets product takeaway.
     *
     * @param product_takeaway the product takeaway
     */
    public void setProduct_takeaway(Boolean product_takeaway) {
        Product_takeaway = product_takeaway;
    }

    /**
     * Gets product toshop.
     *
     * @return the product toshop
     */
    public int getProduct_toshop() {
        return Product_toshop;
    }

    /**
     * Sets product toshop.
     *
     * @param product_toshop the product toshop
     */
    public void setProduct_toshop(int product_toshop) {
        Product_toshop = product_toshop;
    }

    /**
     * Gets product type.
     *
     * @return the product type
     */
    public String getProduct_type() {
        return Product_type;
    }

    /**
     * Sets product type.
     *
     * @param product_type the product type
     */
    public void setProduct_type(String product_type) {
        Product_type = product_type;
    }
}
