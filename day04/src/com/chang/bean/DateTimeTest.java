package com.chang.bean;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * jdk 8之前的日期时间的API测试
 * 1.System类中的currentTimeMills()
 * 2.java.util.Date和子类java.sql.Date
 * 3.SimpleDateFormat
 * 4.Calendar
 * @Description
 * @Author Chang Zhi
 * @Version
 * @Time 2020-03-24 9:43
 */
public class DateTimeTest {
    /**
     * SimpleDateFormat的使用：SimpleDateFormat对日期Date类的格式化和解析
     *
     * 1.两个操作：
     * 1.1 格式化：日期 ---> 字符串
     * 1.2 解析：格式化的逆过程，字符串 ---> 日期
     *
     * 2.SimpleDateFormat的实例化
     */

    @Test
    public void testSimpleDateFormat() throws ParseException {
        //实例化SimpleDateFormat:使用默认的构造器
        SimpleDateFormat sdf = new SimpleDateFormat();

        //格式化：日期--->字符串
        Date date = new Date();
        System.out.println(date);

        String format = sdf.format(date);
        System.out.println(format);

        //解析：格式化的逆过程，字符串 ---> 日期
        String str = " 2020/3/24 上午10:02";
        Date date1 = sdf.parse(str);
        System.out.println(date1);

        //*************按照指定方式格式化和解析：调用带参数的构造器*******************
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        //格式化
        String format1 = sdf1.format(date);
        System.out.println(format1);
        //解析
        Date date2 = sdf1.parse("2020-03-24 10:03:44");
        System.out.println(date2);

    }
    /**
     * 练习一：字符串“2020-09-08"转换为java.sql.Date
     */

    @Test
    public void testExer() throws ParseException {
        String birth = "1996-02-27";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(birth);
        java.sql.Date birthDate = new java.sql.Date(date.getTime());
        System.out.println(birthDate);
    }


    /**
     * 练习二：“三天打鱼两天晒网” 1990-01-01 xxxx-xx-xx打鱼？晒网？
     * 举例：2020-09-08？总天数
     * 总天数%5 == 1,2,3 :打鱼
     * 总天数%5 == 4,0：晒网
     *
     * 总天数的计算？
     * 方式一：(date2.getTime()-date1.getTime()) / (1000 * 60 * 60 * 24) + 1
     * 方式二：1990-01-01 --> 2019-12-31 + 2020-01-01 --> 2020-09-08
     */


    /**
     * Calendar日历类(抽象类)的使用
     *
     */
    @Test
    public void testCalendar(){
        //1.实例化
        //方式一：创建其子类（GregorianCalendar）的对象
        //方式二：调用其静态方法getInstance()
        Calendar calendar = Calendar.getInstance();
//        System.out.println(calendar.getClass());
        //2.常用方法
        //get()
        int days = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(days);
        System.out.println(calendar.get(Calendar.DAY_OF_YEAR));
        //set()
        calendar.set(Calendar.DAY_OF_MONTH,22);
        days = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(days);

        //add()
        calendar.add(Calendar.DAY_OF_MONTH,3);
        days = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(days);
        //getTime():日历类--->Date
        Date date = calendar.getTime();
        System.out.println(date);

        //setTime():Date ---> 日历类
        Date date1 = new Date();
        calendar.setTime(date1);
    }
}
