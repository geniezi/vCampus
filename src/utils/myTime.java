package utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自己定义的字符串和Date数据转换类
 *
 * @author Shuheng_Gu
 * @date 2022/09/03
 */
public class myTime {
    /**
     * Gets current time.
     *
     * @return the current time
     */
    public static String getCurrentTime()
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String t= df.format(new Date());
        return t;
    }

    /**
     * Gets current day.
     *
     * @return the current day
     */
    public static String getCurrentDay()
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String t= df.format(new Date());
        return t;
    }

    /**
     * Date to string string.
     *
     * @param d the d
     * @return the string
     */
    public static String dateToString(Date d){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String t= df.format(d);
        return t;
    }

    /**
     * Datetime to string string.
     *
     * @param t the t
     * @return the string
     */
    public static String datetimeToString(Timestamp t){
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(t.getTime());
        return timeStamp;
    }
}
