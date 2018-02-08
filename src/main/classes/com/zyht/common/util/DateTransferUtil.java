package com.zyht.common.util;/********************************************************************/
/**
 * @Project: Java
 * @Package com.zyht.homework
 * @author caoxin
 * @date 2018/1/15 15:52
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author caoxin
 * @ClassName DateFormat
 * @Description 日期格式转换类
 * @date 2018/1/15
 */
public class DateTransferUtil {

     public static final String timeType = "yyyy-MM-dd HH:mm:ss";
    /**
     * year 年
     */
    private int year;
    /**
     * month 月
     */
    private int month;
    /**
     * day 日
     */
    private int day;
    /**
     * hour 时
     */
    private int hour;
    /**
     * minute 分
     */
    private int minute;
    /**
     * second 秒
     */
    private int second;

    public DateTransferUtil(){

    }

    public DateTransferUtil(int year, int month, int day, int hour, int minute, int second) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    /**
      * @Title: dateToString
      * @Description: 时间转字符串
      * @author renxu
      * @date 2018/1/15
      * @param date 时间
      * @return simpleDateFormat.format(date) 字符串
      */
    public static String dateToString(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timeType);
        return simpleDateFormat.format(date);
    }
    /**
     * @Title: stringToDate
     * @Description:
     * @author renxu
     * @date 2018/1/15
     * @param str 要转换的字符串
     * @return simpleDateFormat.parse(str) 时间
     * @throw Exception
     */
    public static Date stringToDate(String str)throws Exception{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timeType);
        return simpleDateFormat.parse(str);
    }

    /** 
     * @Title: getFirstDayOfWeek
     * @Description:  获得一星期的第一天
     * @author renxu
     * @date 2018/1/15
     * @return day 星期的第一天
     */
    public static int getFirstDayOfWeek(){
        Calendar calendar = new GregorianCalendar();
        int day = calendar.getFirstDayOfWeek();
        return day;
    }
    /**
     * @Title: dateToSql
     * @Description:  java.util中的时间转到数据库的时间
     * @author renxu
     * @date 2018/1/25
     * @return date1
     */
    public static java.sql.Date dateToSql(java.util.Date date){
        java.sql.Date date1 =new java.sql.Date(date.getTime());
       return date1;
    }

}
