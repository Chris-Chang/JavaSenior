package com.chang.bean;

import org.junit.Test;

import java.time.*;

/**
 * jdk 8中日期时间API的测试
 * @Description
 * @Author Chang Zhi
 * @Version
 * @Time 2020-03-26 10:07
 */
public class JDK8DateTimeTest {

    /**
     * LocalDate,LocalTime,LocalDateTime的使用
     * 说明：
     *      1.localDateTime相较于LocalDate\localTime使用频率较高
     *      2.类似于Calendar
     */
    @Test
    public void test1(){

        //now():获取当前的日期、时间、日期+时间
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println(localDate);
        System.out.println(localTime);
        System.out.println(localDateTime);

        //of():
        LocalDateTime localDateTime1 = LocalDateTime.of(2020, 10, 1, 8, 10, 14);
        System.out.println(localDateTime1);

        //getXxx():获取相关属性
        System.out.println(localDateTime.getDayOfMonth());
        System.out.println(localDateTime.getDayOfWeek());
        System.out.println(localDateTime.getMonth());
        System.out.println(localDateTime.getMonthValue());
        System.out.println(localDateTime.getMinute());

        //体现不可变性
        //withXxx():设置相关的属性
        LocalDate localDate1 = localDate.withDayOfMonth(22);
        System.out.println(localDate);
        System.out.println(localDate1);

        //
        LocalDateTime localDateTime2 = localDateTime.plusMonths(3);
        System.out.println(localDateTime);
        System.out.println(localDateTime2);

        LocalDateTime localDateTime3 = localDateTime.minusDays(6);
        System.out.println(localDateTime);
        System.out.println(localDateTime3);
    }

    /**
     * Instant使用
     * 类似于java.util.date
     */
    @Test
    public void test2(){
        //now():获取本初子午线对应的标准时间
        Instant instant = Instant.now();
        System.out.println(instant);

        //添加时间偏移量
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);

        //toEpochMilli():获取自1970年1月1日0时0分0秒(UTC)开始的毫秒数 -->Date类的getTime()方法
        long milli = instant.toEpochMilli();
        System.out.println(milli);

        //ofEpochMilli():通过给定的毫秒数，获取Instant实例
        Instant instant1 = Instant.ofEpochMilli(1585190341288L);
        System.out.println(instant1);
    }

    /**
     * DateTimeFormatter:格式化或解析日期、时间
     * 类似于SimpleDateFormat
     */
    @Test
    public void test3(){

    }

}
