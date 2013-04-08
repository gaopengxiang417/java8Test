package com.gao.first;

/**
 * User: wangchen.gpx
 * Date: 13-4-5
 * Time: 下午12:03
 */
public class JavaAPP {
    public interface Task{
        void run();
        default void cancel(){};
    }

    public static void schdule(Task task) {
        task.run();
    }

    public static void main(String[] args) {
        schdule(() -> System.out.println("hello world"));
    }
}
