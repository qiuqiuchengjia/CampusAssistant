package org.yanzi.Utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 这个用来获取当前的时间日期等等
 */
public class GetNowTimeTools {

    static Calendar now= Calendar.getInstance();

    /**
     * 获取当前的年份
     * @return  当前的年份
     */
    public static int getYear(){

        return now.get(Calendar.YEAR);

    }

    /**
     * 获取当前周是一年中的多少周
     * @return
     */
    public static int getWeekOfYear(){
        return now.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获取当前的月份
     * @return  当前的月份
     */
    public static int getMonth(){

        return now.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前的是一个月中的多少号
     * @return 一个月中的多少号
     */
    public static int getDay(){

        return now.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当前是一天中的几小时
     * @return 一天中的小时
     */
    public static  int getHour(){
        return  now.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 这个是一小时中的多少分钟
     * @return  一个小时中的多少分钟
     */
    public static  int getMinute(){
        return now.get(Calendar.MINUTE);
    }

    /**
     * 一分钟的多少秒钟
     * @return 一分钟中的多少秒钟
     */
    public static  int getSecond(){
        return now.get(Calendar.SECOND);
    }

    /**
     * 这个是获取当前的毫秒数
     * @return 当前的毫秒数
     */
    public static long getTimeInMillis(){
        return now.getTimeInMillis();
    }

    /**
     * 这个是格式化的日期 yyyy-MM-dd HH:mm:ss
     * @return 格式化日期  2012-01-13 17:28:19
     */
    public static String getFormat(){
        Date date=new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(date);

        return time;
    }

    /**
     * 这个是格式化的日期 yyyy-MM-dd
     * @return 格式化的日期 yyyy-MM-dd
     */
    public static String getFormatYMD(){
        Date date=new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String time=format.format(date);
        return time;
    }

    /**
     * 这个是格式化的日期 yyyy-MM
     * @return 格式化的日期 yyyy-MM
     */
    public static String getFormatYM(){
        Date date=new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM");
        String time=format.format(date);
        return time;
    }
    /**
     * 这个是格式化的日期 HH:mm:ss
     * @return 格式化的日期 HH:mm:ss
     */
    public static String getFormatHMS(){
        Date date=new Date();
        DateFormat format = new SimpleDateFormat("HH:mm:ss");
        String time=format.format(date);
        return time;
    }

    /**
     * 这个是格式化的日期 HH:mm
     * @return 格式化的日期 HH:mm
     */
    public static String getFormatHM(){
        Date date=new Date();
        DateFormat format = new SimpleDateFormat("HH:mm");
        String time=format.format(date);
        return time;
    }
    /**
     * 这个是格式化的日期 mm:ss
     * @return 格式化的日期 mm:ss
     */
    public static String getFormatMS(){
        Date date=new Date();
        DateFormat format = new SimpleDateFormat("mm:ss");
        String time=format.format(date);
        return time;
    }
}
