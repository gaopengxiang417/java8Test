package com.gao.lambda;

import java.time.LocalDate;

/**
 * User: wangchen.gpx
 * Date: 14-1-4
 * Time: 下午2:43
 */
public class Person {
    public enum Sex{
        MALE , FEMALE;
    }

    /**
     * 姓名
     */
    private String name;

    /**
     * 出生日期
     */
    private int birthday;

    /**
     * 性别
     */
    private Sex gender;

    /**
     * email地址
     */
    private String emailAddress;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthday() {
        return birthday;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    public Sex getGender() {
        return gender;
    }

    public void setGender(Sex gender) {
        this.gender = gender;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void pringPerson(){
        System.out.println("[name:" + this.name +
                ",birthday:" + birthday +
                ",gendar:" + gender +
                ",emailaddress:" + emailAddress + "]");
    }
}
