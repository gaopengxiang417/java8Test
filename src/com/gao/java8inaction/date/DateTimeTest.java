package com.gao.java8inaction.date;

import java.time.*;
import java.time.temporal.ChronoField;

/**
 * User: wangchen
 * Date: 16/4/26
 * Time: 18:55
 */
public class DateTimeTest {

    public static void main(String[] args) {

        //获取当前的日期
        LocalDate localDate = LocalDate.of(2015, 3, 23);
        int year = localDate.getYear();
        Month month = localDate.getMonth();
        int dayOfMonth = localDate.getDayOfMonth();
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        int lengthOfMonth = localDate.lengthOfMonth();
        boolean leapYear = localDate.isLeapYear();

        System.out.println(year);
        System.out.println(month.getValue());
        System.out.println(dayOfMonth);
        System.out.println(dayOfWeek);
        System.out.println(lengthOfMonth);
        System.out.println(leapYear);

        System.out.println(LocalDate.now());

        //通过其他的方式来获取详细的字段信息
        int i = localDate.get(ChronoField.YEAR);
        int i1 = localDate.get(ChronoField.MONTH_OF_YEAR);
        int i2 = localDate.get(ChronoField.DAY_OF_MONTH);

        System.out.println(i);
        System.out.println(i1);
        System.out.println(i2);

        //localtime的使用
        LocalTime localTime = LocalTime.of(23, 2, 34);
        int hour = localTime.getHour();
        int minute = localTime.getMinute();
        int second = localTime.getSecond();

        //打印出来
        System.out.println(hour);
        System.out.println(minute);
        System.out.println(second);

        System.out.println(LocalDate.parse("2015-04-12"));
        //System.out.println(LocalDate.parse("2015/04/12")); 这里不符合格式
        //System.out.println(LocalDate.parse("2015/04/12", DateTimeFormatter.));

        System.out.println(LocalTime.parse("12:34:23"));

        //localdatetime的使用
        LocalDateTime dateTime = LocalDateTime.of(2015, 11, 12, 12, 42, 34, 54);
        System.out.println(dateTime);

        LocalDateTime dateTime1 = LocalDateTime.of(localDate, localTime);
        System.out.println(dateTime1);

        LocalDateTime dateTime2 = localDate.atTime(localTime);
        System.out.println(dateTime2);

        //Instant类的使用
        Instant instant = Instant.ofEpochSecond(3);
        System.out.println(instant);

        //计算两个维度之间的差距值
        Duration duration = Duration.between(dateTime2, dateTime);
        System.out.println(duration);
        System.out.println(duration.toDays());

        Period between = Period.between(localDate, dateTime2.toLocalDate());
        System.out.println(between);
        System.out.println(between.getDays());


    }
}
