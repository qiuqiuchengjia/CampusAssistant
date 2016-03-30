package org.yanzi.Utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 这个是操作日期的工具类
 */
public class DateTools {
    /**
     * 返回Calendar对象，当输入0的时候输出的是星期一的日期，
     * @param year 年
     * @param week 周
     * @param day 星期几
     * @return
     */
    public Calendar getWeekFirst(int year, int week,int day){
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);//因为中国是一星期一为一个星期的第一天，所以设置
        calendar.set(year, 0, 1);
        int weeks = 0;
        while((weeks = calendar.get(Calendar.WEEK_OF_YEAR))<= week){
            calendar.add(Calendar.MONTH, 1);
        }
        calendar.add(Calendar.MONTH, -1);
        while((weeks = calendar.get(Calendar.WEEK_OF_YEAR))< week){
            calendar.add(Calendar.DATE, 1);
        }
        calendar.add(Calendar.DATE,day);

        return calendar;
    }

    /**
     * 获得本周一的日期
     * @return 本周一的日期
     */
    public String getMondayOFWeek(){
         int weeks = 0;
        int mondayPlus = this.getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus);
        Date monday = currentDate.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String preMonday = df.format(monday);
        return preMonday;
    }

    /**
     * 获取当天时间
     * @param dateformat 需要的事件格式
     * @return 当天时间
     */
    public String getNowTime(String dateformat){
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);//可以方便地修改日期格式
        String hehe = dateFormat.format(now);
        return hehe;
    }

    /**
     * 获得本周星期日的日期
     * @return 本周日日期
     */
    public  String getCurrentWeekday() {
         int weeks = 0;
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus+6);
        Date monday = currentDate.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String preMonday = df.format(monday);
        return preMonday;
    }
    /**
     * 获得当前日期与本周日相差的天数
     * @return 相差天数
     */
    private static int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
         // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK)-1; //因为按中国礼拜一作为第一天所以这里减1
        if (dayOfWeek == 1) {
            return 0;
        } else {
            return 1 - dayOfWeek;
        }
    }
    /**
     * 获取当月第一天
     * @return 当月第一天
     */
    public static String getFirstDayOfMonth(){
        String str = "";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE,1);//设为当前月的1 号
        str=sdf.format(lastDate.getTime());
        return str;
    }
    /**
     * 获取上个月的第一天
     * @return 上月第一天
     */
    public static String getPreviousMonthFirst(){
        String str = "";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE,1);//设为当前月的1 号
        lastDate.add(Calendar.MONTH,-1);//减一个月，变为下月的1 号
         //lastDate.add(Calendar.DATE,-1);//减去一天，变为当月最后一天
        str=sdf.format(lastDate.getTime());
        return str;
    }
    /**
     * 计算当月最后一天，返回字符串
     * @return 当月最后一天
     */
    public static String getDefaultDay(){
        String str = "";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE,1);//设为当前月的1 号
        lastDate.add(Calendar.MONTH,1);//加一个月，变为下月的1 号
        lastDate.add(Calendar.DATE,-1);//减去一天，变为当月最后一天
        str=sdf.format(lastDate.getTime());
        return str;

    }

    /**
     * 两个日期之间的天数
     * @param strDate1 日期一 yyyy-MM-dd
     * @param strDate2 日期二 yyyy-MM-dd
     * @return 日期之间的天数
     */
    public static long getDaysForTwoDate(String strDate1, String strDate2) {
        if (strDate1 == null || strDate1.equals(""))
            return 0;
        if (strDate2 == null || strDate2.equals(""))
            return 0;
        //转换成标准时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 =null;
        Date date2 =null;
        try {
            date1 =  format.parse(strDate1);
            date2 =  format.parse(strDate2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return (date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000);

    }

    /**
     * 根据一个日期，返回星期几的字符串
     * @param strDate 日期的字符串
     * @return 星期几
     */
    public static String getWeek(String strDate) {
        //转换成时间
        Date  date = DateTools.strToDate(strDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return new SimpleDateFormat("EEEE").format(calendar.getTime());

    }

    /**
     * 将短时间格式字符串转换成Date
     * @param strDate 短时间格式字符串yyyy-MM-dd
     * @return 日期
     */
    public static Date strToDate(String strDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition position = new ParsePosition(0);
        Date strtodate = format.parse(strDate,position);
        return strtodate;
    }

    /**
     * 获取两个日期之间间隔了多少天
     * @param date1 日期一
     * @param date2 日期二
     * @return 间隔天数
     */
    public static String getTowDateLength(String date1, String date2){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        long day =0;

        try {
            Date da1 = format.parse(date1);
            Date da2 = format.parse(date2);
            day = (da1.getTime() - da2.getTime()) / (24 * 60 * 60 * 1000);

        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
        return day+"";
    }
    /**
     * 获取以标尺日期为起点，多少个月之后(前)的日期
     * @param scaleDate  日期起点,这个起点的日期格式一定要为 yyyy-MM-dd ，例如 1996-11-15
     * @param month 多少个月
     * @return 日期
     */
    public static String getAfterMonth(String scaleDate , int month){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(scaleDate);//初试日期
        } catch (ParseException e) {
            e.printStackTrace();
        }

        calendar.setTime(date);//设置日历时间
        calendar.add(Calendar.MONTH,month);
        return format.format(calendar.getTime());
    }

}
