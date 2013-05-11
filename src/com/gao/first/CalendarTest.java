package com.gao.first;

import java.util.Calendar;
import java.util.Locale;

import static java.util.Calendar.*;

/**
 * User: wangchen.gpx
 * Date: 13-5-11
 * Time: 下午12:50
 */
public class CalendarTest {
    public static void main(String[] args) {
        //before java8
        Calendar calendar = Calendar.getInstance();
        calendar.set(2003,4,3);
        System.out.println(calendar.getTime());

        //after jdk8
        Calendar calendar1 = new Builder().set(YEAR, 2013)
                .set(MONTH, 4)
                .set(DAY_OF_MONTH, 5).build();
        System.out.println(calendar1.getTime());

        Calendar calendar2 = new Builder().setDate(2033, 4, 2).setTimeOfDay(02, 33, 43).setLocale(Locale.CHINA).build();
        System.out.println(calendar2.getTime());

        Calendar calendar3 = new Builder().setFields(YEAR, 2011, MONTH, 11, DAY_OF_MONTH, 4).build();
        System.out.println(calendar3.getTime());
    }
}
