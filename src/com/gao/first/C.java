package com.gao.first;

/**
 * User: wangchen.gpx
 * Date: 14-1-2
 * Time: 下午7:44
 */
public class C implements A,B{
    public static void main(String[] args) {
        C c = new C();
        c.hello();

        F f = new F();
        f.hello();

        M m = new M();
        m.hello();
    }
}

class M implements H,I{
    public void hello() {
        H.super.hello();
    }
}

interface I extends G{
    default void hello(){
        System.out.println("I method...");
    }
}

interface H extends G{
    default void hello(){
        System.out.println("h method....");
    }
}
interface G{
    default void hello(){
        System.out.println("g method.");
    }
}

//这里F必须重写默认的方法，否则会报错的
class F implements D,E{
    @Override
    public void hello() {
        D.super.hello();
    }
}

interface E{
    default void hello(){
        System.out.println("E method....");
    }
}
interface D{
    default void hello(){
        System.out.println("D method...");
    }
}

//这里可以通过B实现A来避免多重继承的冲突，调用的时候会使用就近原则
interface A{
    default void hello(){
        System.out.println("a method...");
    }
}

interface B extends A{
    default void hello(){
        System.out.println("b method....");
    }
}
