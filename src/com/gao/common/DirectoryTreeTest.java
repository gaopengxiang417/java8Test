package com.gao.common;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * User: gaopengxiang
 * Date: 12-3-30
 * Time: 下午4:40
 */
public class DirectoryTreeTest {

    public static void main(String[] args) {
        Path path = Paths.get("/Users/wangchen/Downloads/root");

        try {
            Files.walkFileTree(path,new MyFileVisitor());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class MyFileVisitor extends SimpleFileVisitor<Path> {

    long size = 0;
    long secondSize = 0;

    public MyFileVisitor() {
        super();
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        if(Files.isDirectory(dir, LinkOption.NOFOLLOW_LINKS)){
            System.out.println("dirctory:"+dir.getFileName());
        }else{
            System.out.println("File:"+dir.getFileName());
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if(null != file && attrs != null && !file.getFileName().toString().startsWith(".")) {
            /*if (file.getFileName().toString().endsWith(".java")) {
                System.out.println("          find:"+file.getFileName().toString()+" .java files");
            }*/
            //System.out.println(Files.isDirectory(file));
            //System.out.println(Files.isRegularFile(file));
            if (!Files.isDirectory(file)) {

                long tmpSize = Files.size(file);
                System.out.println(tmpSize);
                if (tmpSize > size) {
                    size = tmpSize;
                    System.out.println("finally:" + size);
                    System.out.println(file.toAbsolutePath());
                }
            }
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        if(exc != null){
            System.out.println("some exception is occur");
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        String directory = "";
        if (Files.isDirectory(dir, LinkOption.NOFOLLOW_LINKS)) {
            directory = "Diectory";
        }else{
            directory = "file";
        }
        if(dir != null && exc == null){

            System.out.println("this "+directory+" have already searched");
        }
        return FileVisitResult.CONTINUE;
    }
}