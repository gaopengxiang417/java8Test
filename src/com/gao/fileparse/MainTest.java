package com.gao.fileparse;

import jdk.nashorn.internal.runtime.regexp.joni.Matcher;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.util.regex.Pattern;

/**
 * User: wangchen
 * Date: 14/10/24
 * Time: 17:44
 */
public class MainTest {
    public static void main(String[] args) {

        String regex = "mm_\\d+_\\d_\\d+";
        String string = "sdfsdfsdmm_2342342_0_0asdfasdfsda";


        Pattern pattern = Pattern.compile(regex);
        java.util.regex.Matcher matcher = pattern.matcher(string);

        while (matcher.find()) {
            System.err.println(matcher.group());
        }
    }
}
