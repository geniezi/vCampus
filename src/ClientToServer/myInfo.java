package ClientToServer;

/**
 * 我的个人信息
 * @description 用于在登陆进去以后展示个人信息
 * @author Shuheng_Gu
 * @date 2022/09/03
 */
public class myInfo {
    /**
     * id
     */
    static String id;
    /**
     * 类型 1-学生 2-老师 3-管理员
     */
    static int type; //1-学生 2-老师 3-管理员
    /**
     * 名字
     */
    static String name;
    /**
     * 钱
     */
    static double money;

    /**
     * 得到id
     *
     * @return {@link String}
     */
    public static String getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id1 id1
     */
    public static void setId(String id1) {
        id = id1;
    }

    /**
     * 得到类型
     *
     * @return int
     */
    public static int getType() {
        return type;
    }

    /**
     * 设置类型
     *
     * @param ty 泰
     */
    public static void setType(int ty) {
        type = ty;
    }

    /**
     * 得到名字
     *
     * @return {@link String}
     */
    public static String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param n1 n1
     */
    public static void setName(String n1) {
        name = n1;
    }

    /**
     * 得到钱
     *
     * @return double
     */
    public static double getMoney() {
        return money;
    }

    /**
     * 设置钱
     *
     * @param m 米
     */
    public static void setMoney(double m) {
        money = m;
    }

    /**
     * 一次性设置所有信息
     *
     * @param id   id
     * @param type 类型
     * @param name 名字
     */
    public static void setall(String id, int type, String name){
        setName(name);
        setId(id);
        setType(type);
    }

}
