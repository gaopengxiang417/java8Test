package com.gao.common;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

/**
 * User: wangchen
 * Date: 15/11/26
 * Time: 13:12
 */
public class FileMAXTest {

    static long size = 0;
    static String result = "";

    public static void main(String[] args) throws IOException {


        /*Path path = Files.walkFileTree(Paths.get("/Users/wangchen/Downloads/root"), new TestVistor());
        System.out.println(path.toString());*/



    }

   /* static class TestVistor extends SimpleFileVisitor<Path> {
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

            long tmpSize = Files.size(file);
            if (tmpSize > size) {
                size = tmpSize;

                System.out.println(size);
                System.out.println(file.getFileName());
            }

            return FileVisitResult.CONTINUE;
        }
    }*/

}
