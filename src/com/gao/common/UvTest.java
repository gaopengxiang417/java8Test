package com.gao.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * User: wangchen
 * Date: 15/11/26
 * Time: 22:03
 */
public class UvTest {
    public static void main(String[] args) throws IOException {

        Path path = Paths.get("/Users/wangchen/Downloads/uv.txt");

        List<String> list = Files.readAllLines(path);

        //进行循环来解决分割
        Set<Long> userList = new HashSet<>();
        for (String str : list) {

            String[] split = str.split(" ");

            Long user = Long.valueOf(split[1]);

            userList.add(user);
        }

        System.out.println(userList.size());

        //String regex = "\d{6}19";

    }
}
