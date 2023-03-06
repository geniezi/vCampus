package UIhandler.Shop;

import java.io.IOException;
import java.util.List;
import DAO.Shop.Product;
import DAO.Shop.ProductPair;
import message.Message;
import message.MessageType;
import utils.MyObjectOutputStream;
import javax.swing.*;


/**
 * The type Client shop.
 */
public class Client_shop {

    /**
     * 区分 1：学生  2：老师  3：管理员
     */
    public static String id;
    /**
     * 用户当前余额
     */
    public static double current_money;
    /**
     * 当前已登录账号的那个对象一卡通
     */
    public static String idcard;
    /**
     * 存放已购商品
     */
    public static List<ProductPair> Buyed;
    /**
     * 存放购物车商品
     */
    public static List<ProductPair> ReadyToBuy;
    /**
     * 存放所有商品
     */
    public static List<Product> products;
    /**
     * 存放特定商品
     */
    public static Product CertainProducts;
    /**
     * 要买的商品标志  1 正在找  2 成功  3 失败
     */
    public static String Now_Buy_product;
    /**
     * 特定商品标志 1 正在找  2 成功  3 失败
     */
    public static String sign_Certain;
    /**
     * 商品是否删除成功的标志 1 正在删除  2 成功  3 失败
     */
    public static String sign_delete;
    /**
     * 查找了但数据库有-1 或者没有-0
     */
    public static boolean sign_zero ;
    /**
     * 商品是否add成功的标志 1 正在add  2 成功  3 失败
     */
    public static String sign_add;
    /**
     * 商品找到的标志 1 正在找  2 成功  3 失败
     */
    public static String sign_find_type;

    /**
     * 查找得到的商品
     */
    public static List<Product> checkproducts;

    /**
     * 按照类型查找得到的商品
     */
    public static List<Product> checkproductsType;

    /**
     * 输出流
     */
    static MyObjectOutputStream oos=null;

    /**
     * Sets oos.
     *
     * @param mos the mos
     */
    public static void setOos(MyObjectOutputStream mos) {
        Client_shop.oos = mos;
    }

    /**
     * Reset now buy product.
     */
    public static void resetNow_Buy_product(){
        Now_Buy_product="正在买";
    }

    /**
     * Reset certain product sign.
     */
    public static void resetCertainProduct_sign(){
        sign_Certain="1";
    }

    /**
     * Reset sign add.
     */
    public static void resetSign_add(){
        sign_add="1";
    }

    /**
     * Reset sign delete.
     */
    public static void resetSign_delete(){
        sign_delete="1";
    }

    /**
     * Reset sign find tpye.
     */
    public static void resetSign_find_tpye(){
        sign_find_type="1";
    }

    /**
     * Reset buyed.
     */
    public static void resetBuyed(){
        Buyed=null;
    }

    /**
     * Reset readyto buy.
     */
    public static void resetReadytoBuy(){
        ReadyToBuy=null;
    }

    /**
     * Reset sign zero.
     */
    public static void resetSign_zero(){
        sign_zero=true;
    }

    /**
     * Reset certain products.
     */
    public static void resetCertainProducts(){
        CertainProducts=null;
    }

    /**
     * Reset all products.
     */
    public static void resetAllProducts(){
        products=null;
    }

    /**
     * Reset checked products.
     */
    public static void resetCheckedProducts(){
            checkproducts=null;
    }

    /**
     * Reset checkedtype products.
     */
    public static void resetCheckedtypeProducts(){
        checkproductsType=null;
    }

    /**
     * Gets certain products.
     *
     * @return the certain products
     */
    public static Product getCertainProducts() {
        return CertainProducts;
    }

    /**
     * Gets current money.
     *
     * @return the current money
     */
    public static double getCurrent_money() {
        return current_money;
    }

    /**
     * Sets current money.
     *
     * @param current_money the current money
     */
    public static void setCurrent_money(double current_money) {
        Client_shop.current_money = current_money;
    }

    /**
     * Sets certain products.
     *
     * @param certainProducts the certain products
     */
    public static void setCertainProducts(Product certainProducts) {
        CertainProducts = certainProducts;
    }

    /**
     * Gets sign certain.
     *
     * @return the sign certain
     */
    public static String getSign_Certain() {
        return sign_Certain;
    }

    /**
     * Sets sign certain.
     *
     * @param sign_Certain the sign certain
     */
    public static void setSign_Certain(String sign_Certain) {
        Client_shop.sign_Certain = sign_Certain;
    }

    /**
     * Gets buyed.
     *
     * @return the buyed
     */
    public static List<ProductPair> getBuyed() {
        return Buyed;
    }

    /**
     * Sets buyed.
     *
     * @param buyed the buyed
     */
    public static void setBuyed(List<ProductPair> buyed) {
        Buyed = buyed;
    }

    /**
     * Gets ready to buy.
     *
     * @return the ready to buy
     */
    public static List<ProductPair> getReadyToBuy() {
        return ReadyToBuy;
    }

    /**
     * Sets ready to buy.
     *
     * @param readyToBuy the ready to buy
     */
    public static void setReadyToBuy(List<ProductPair> readyToBuy) {
        ReadyToBuy = readyToBuy;
    }

    /**
     * Gets sign add.
     *
     * @return the sign add
     */
    public static String getSign_add() {
        return sign_add;
    }

    /**
     * Sets sign add.
     *
     * @param sign_add the sign add
     */
    public static void setSign_add(String sign_add) {
        Client_shop.sign_add = sign_add;
    }

    /**
     * Gets sign find type.
     *
     * @return the sign find type
     */
    public static String getSign_find_type() {
        return sign_find_type;
    }

    /**
     * Sets sign find type.
     *
     * @param sign_find_type the sign find type
     */
    public static void setSign_find_type(String sign_find_type) {
        Client_shop.sign_find_type = sign_find_type;
    }

    /**
     * Gets checkproducts type.
     *
     * @return the checkproducts type
     */
    public static List<Product> getCheckproductsType() {
        return checkproductsType;
    }

    /**
     * Sets checkproducts type.
     *
     * @param checkproductsType the checkproducts type
     */
    public static void setCheckproductsType(List<Product> checkproductsType) {
        Client_shop.checkproductsType = checkproductsType;
    }

    /**
     * Gets sign.
     *
     * @return the sign
     */
    public static Boolean getSign() {
        return sign_zero;
    }

    /**
     * Sets sign.
     *
     * @param sign the sign
     */
    public static void setSign(Boolean sign) {
        Client_shop.sign_zero = sign;
    }

    /**
     * Gets sign delete.
     *
     * @return the sign delete
     */
    public static String getSign_delete() {
        return sign_delete;
    }

    /**
     * Sets sign delete.
     *
     * @param sign_delete the sign delete
     */
    public static void setSign_delete(String sign_delete) {
        Client_shop.sign_delete = sign_delete;
    }

    /**
     * Gets checkproducts.
     *
     * @return the checkproducts
     */
    public static List<Product> getCheckproducts() {
        return checkproducts;
    }

    /**
     * Sets checkproducts.
     *
     * @param checkproducts the checkproducts
     */
    public static void setCheckproducts(List<Product> checkproducts) {
        Client_shop.checkproducts = checkproducts;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public static String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public static void setId(String id) {
        Client_shop.id = id;
    }

    /**
     * Gets idcard.
     *
     * @return the idcard
     */
    public static String getIdcard() {
        return idcard;
    }

    /**
     * Sets idcard.
     *
     * @param idcard the idcard
     */
    public static void setIdcard(String idcard) {
        Client_shop.idcard = idcard;
    }

    /**
     * Gets sign zero.
     *
     * @return the sign zero
     */
    public static Boolean getSign_zero() {
        return sign_zero;
    }

    /**
     * Sets sign zero.
     *
     * @param sign_zero the sign zero
     */
    public static void setSign_zero(Boolean sign_zero) {
        Client_shop.sign_zero = sign_zero;
    }

    /**
     * Gets products.
     *
     * @return the products
     */
    public static List<Product> getProducts() {
        return products;
    }

    /**
     * Gets now buy product.
     *
     * @return the now buy product
     */
    public static String getNow_Buy_product() {
        return Now_Buy_product;
    }

    /**
     * Sets now buy product.
     *
     * @param now_Buy_product the now buy product
     */
    public static void setNow_Buy_product(String now_Buy_product) {
        Now_Buy_product = now_Buy_product;
    }

    /**
     * Sets products.
     *
     * @param products the products
     */
    public static void setProducts(List<Product> products) {
        Client_shop.products = products;
    }

    /**
     * Return 所有商品列表
     * 刚开始登录界面就显示所有的商品
     * @return the 所有商品list
     * @throws Exception the exception
     */
    public static List<Product> returnAllProduct() throws Exception {
        setSign(true);
        resetAllProducts();
        setSign_delete("1");
        setSign_find_type("1");
        setSign_add("1");
        Message message = new Message();
        message.setType(MessageType.RETURN_ALL_PRODUCT);
        oos.writeObject(message);
        //等待接受
        while (products==null) Thread.onSpinWait();
        return products;
    }

    /**
     * 查找商品
     * @param name 商品名称
     * @return 商品列表
     * @throws Exception the exception
     */
    public static List<Product> checkProduct(String name) throws Exception {
        resetSign_find_tpye();
        Message message = new Message();
        message.setType(MessageType.FIND_PRODUCT);
        message.setSender(name);
        oos.writeObject(message);
        //等待接受
        while (sign_find_type.equals("1")) Thread.onSpinWait();
        return checkproducts;
    }

    /**
     * 查找特定商品
     *
     * @param id_product 商品id
     * @return 特定商品
     * @throws Exception the exception
     */
    public static Product checkCertainProduct(int id_product) throws Exception {
        resetCertainProducts();
        resetCertainProduct_sign();
        Message message = new Message();
        message.setType(MessageType.CHECK_CERTAIN_PRODUCT);
        message.setCode(id_product);
        oos.writeObject(message);
        while ( sign_Certain.equals("1")) Thread.onSpinWait();
        return CertainProducts;
    }

    /**
     * 删除特定商品
     * @param id 商品id
     * @return ture 删除成功 false失败
     * @throws Exception the exception
     */
    public static boolean deleteProduct(String id) throws Exception {
        resetSign_delete();
        Message message = new Message();
        message.setType(MessageType.DELETE_PRODUCT);
        message.setSender(id);
        oos.writeObject(message);
        while (sign_delete.equals("1")) Thread.onSpinWait();
        return sign_delete.equals("2");
    }

    /**
     * 查找特定商品
     *
     * @param type_name 商品类型名称
     * @return 此类商品列表
     * @throws Exception the exception
     */
    public static List<Product> checktypeProduct(String type_name) throws Exception {
        resetCheckedProducts();
        resetSign_find_tpye();
        Message message = new Message();
        message.setType(MessageType.FIND_TYPE_PRODUCT);
        message.setSender(type_name);
        oos.writeObject(message);
        //等待接受学生
        while (sign_find_type.equals("1")) Thread.onSpinWait();
        return checkproductsType;
    }

    /**
     * 添加商品
     *
     * @param p  商品实例
     * @return ture成功 false失败
     * @throws Exception the exception
     */
    public static boolean addProduct(Product p) throws Exception{
        resetSign_add();
        Message message = new Message();
        message.setType(MessageType.ADD_PRODUCT);
        message.setData(p);
        oos.writeObject(message);
        //等待接受学生
        while (sign_add.equals("1")) Thread.onSpinWait();
        return sign_add.equals("2");
    }

    /**
     * 购买商品.
     *
     * @param user_idcard 用户id
     * @param id          商品id
     * @param num        商品数量
     * @param money       用户余额
     * @return  ture成功 false失败
     * @throws Exception the exception
     */
    public static boolean buyProduct(String user_idcard, String id, int num, double money) throws Exception {
        resetNow_Buy_product();
        Message message = new Message();
        message.setType(MessageType.BUY_CERTAIN_PRODUCT);
        message.setGetter(user_idcard); //用户id
        message.setSender(id);  //商品id
        message.setCode(num);   //商品数量
        message.setMoney(money); //商品总价格
        oos.writeObject(message);
        while (Now_Buy_product.equals("正在买")) Thread.onSpinWait();
        if(Now_Buy_product.equals("数量不够"))
            JOptionPane.showMessageDialog(null,"数量不够！");
        return Now_Buy_product.equals("购买成功");
    }

    /**
     * 老师购买商品.
     *
     * @param user_idcard 用户id
     * @param id          商品id
     * @param num        商品数量
     * @param money       用户余额
     * @return  ture成功 false失败
     * @throws Exception the exception
     */
    public static boolean buyProduct_Teacher(String user_idcard, String id, int num, double money) throws Exception {
        resetNow_Buy_product();
        Message message = new Message();
        message.setType(MessageType.BUY_CERTAIN_PRODUCT_TEACHER);
        message.setGetter(user_idcard); //用户id
        message.setSender(id);  //商品id
        message.setCode(num);   //商品数量
        message.setMoney(money); //商品总价格
        oos.writeObject(message);
        while (Now_Buy_product.equals("正在买")) Thread.onSpinWait();
        if(Now_Buy_product.equals("数量不够"))
            JOptionPane.showMessageDialog(null,"数量不够！");
        return Now_Buy_product.equals("购买成功");
    }


    /**
     * 添加购物车
     *
     * @param user_idcard 用户idcard
     * @param id          商品id
     * @param num         商品数量
     * @return ture成功 false失败
     * @throws Exception the exception
     */
    public static Boolean addToShopCar(String user_idcard, int id, int num) throws Exception {
        resetSign_add();
        resetNow_Buy_product();
        Message message = new Message();
        ProductPair t = new ProductPair();
        t.setId(id);t.setNum(num);
        message.setType(MessageType.ADD_TO_SHOPCAR);
        message.setSender(user_idcard);  //用户id
        message.setData(t);   //商品数量
        oos.writeObject(message);
        while (sign_add.equals("1")) Thread.onSpinWait();
        return sign_add.equals("2");
    }

    /**
     * 查看已购商品信息
     *
     * @param idcard 用户idcard
     * @return 用户已购商品列表
     * @throws IOException the io exception
     */
    public static List<ProductPair> checkBuyed(String idcard) throws IOException {
        resetBuyed();
        resetSign_zero();
        Message message = new Message();
        message.setType(MessageType.CHECK_BUYED_PRODUCT);
        message.setSender(idcard);
        oos.writeObject(message);
        while (sign_zero) Thread.onSpinWait();
        return Buyed;
    }

    /**
     * 查看购物车商品信息
     *
     * @param idcard 用户idcard
     * @return 用户购物车商品列表
     * @throws IOException the io exception
     */
    public static List<ProductPair> checkReadyToBuy(String idcard) throws IOException {
        resetReadytoBuy();
        resetSign_zero();
        Message message = new Message();
        message.setType(MessageType.CHECK_READYTOBUY_PRODUCT);
        message.setSender(idcard);
        oos.writeObject(message);
        while (sign_zero) Thread.onSpinWait();
        return ReadyToBuy;
    }

    /**
     * 删除购物车商品
     *
     * @param idcard 用户idcard
     * @param id     用户id
     * @param num    the num
     * @return ture 成功 false失败
     * @throws IOException the io exception
     */
    public static boolean deleteReadyToBuy(String idcard, int id, int num) throws IOException {
        resetSign_delete();
        ProductPair p = new ProductPair();p.setNum(num);p.setId(id);
        Message message = new Message();
        message.setType(MessageType.DELETE_READYTPBUY_PRODUCT);
        message.setData(p);
        message.setSender(idcard);
        oos.writeObject(message);
        while (sign_delete.equals("1")) Thread.onSpinWait();
        return sign_delete.equals("2");
    }

    /**
     * 得到学生余额.
     *
     * @param idcard 学生idcard
     * @return 学生余额
     * @throws IOException the io exception
     */
    public static double getMoney(String idcard) throws IOException {
        resetSign_delete();
        Message message = new Message();
        message.setType(MessageType.GET_MONEY);
        message.setSender(idcard);
        oos.writeObject(message);
        while (sign_delete.equals("1")) Thread.onSpinWait();
        return current_money;
    }

    /**
     * 得到老师余额.
     *
     * @param idcard 老师idcard
     * @return 老师余额
     * @throws IOException the io exception
     */
    public static double getMoney_Teacher(String idcard) throws IOException {
        resetSign_delete();
        Message message = new Message();
        message.setType(MessageType.GET_MONEY_TEACHER);
        message.setSender(idcard);
        oos.writeObject(message);
        while (sign_delete.equals("1")) Thread.onSpinWait();
        return current_money;
    }

}
