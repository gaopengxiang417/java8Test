package com.gao.java8inaction.refactor;

import com.gao.java8inaction.stream.Dish;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * User: wangchen
 * Date: 16/3/19
 * Time: 19:12
 */
public class RefactorTest {
    public static void main(String[] args) {

        //菜单列表
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));

        //从匿名内部类到lambda表达式
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello");
            }
        };

        runnable1.run();

        //lambda表达式
        Runnable runnable2 = () -> System.out.println("hello runnable2");

        runnable2.run();

        //会导致歧义的两个不同的实现
        Task task = (Task) () -> System.out.println("do some task");

        task.execute();

        //方法的引用
        Integer reduce = menu.stream().map(Dish::getCalories).reduce(0, (o1, o2) -> o1 + o2);

        Integer collect = menu.stream().collect(Collectors.summingInt(Dish::getCalories));
        System.out.println(reduce + ":" + collect);

        //流式处理
        List<String> dishNames = new ArrayList<>();
        for (Dish dish : menu) {
            if (dish.getCalories() > 300) {
                dishNames.add(dish.getName());
            }
        }

        System.out.println(dishNames);

        List<String> collect1 = menu.parallelStream().filter(d -> d.getCalories() > 300).map(Dish::getName).collect(Collectors.toList());
        System.out.println(collect1);


        //策略设计模式的改进,本快是老的开发习惯
        Validator validator = new Validator(new IsLowerCaseStrategy());
        System.out.println(validator.validate("sdklsda"));

        Validator validator1 = new Validator(new IsNumberCaseStrategy());
        System.out.println(validator1.validate("2332323"));

        //使用lambda表达式
        Validator validator2 = new Validator((String s) -> s.matches("[a-z]+"));
        System.out.println(validator2.validate("3232"));

        Validator validator3 = new Validator((String s) -> s.matches("\\d+"));
        System.out.println(validator3.validate("sdsd"));

        //下面是模板设计模式的方法
        new OnlineBanking().process(232, (Integer integer) -> System.out.println(integer));

        //观察者设计模式
        Feed feed = new Feed();
        feed.registerObserver(new NYObserver());
        feed.registerObserver(new GuradinObserver());

        feed.notifyObserver("The queen said her favourite book is Java 8 in Action!");


        feed.registerObserver((String tweet) -> {
            if (tweet != null && tweet.contains("queen")) {
                System.out.println("lodon queen");
            }
        });

        //使用lambda表达式进行单元测试


    }

    static class Point{

        private final int x;

        private final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public Point moveRight(int x) {
            return new Point(this.x + x, this.y);
        }

        public final static Comparator<Point> comparByXAndY = Comparator.comparing(Point::getX).thenComparing(Point::getY);
    }

    static class Feed implements Subject{

        private List<Observer> observerList = new ArrayList<>();

        @Override
        public void registerObserver(Observer observer) {
            observerList.add(observer);
        }

        @Override
        public void notifyObserver(String tweet) {
            observerList.forEach(o -> o.notify(tweet));
        }
    }

    interface Subject{
        void registerObserver(Observer observer);

        void notifyObserver(String tweet);
    }


    static class GuradinObserver implements Observer{
        @Override
        public void notify(String tweet) {
            if (tweet != null && tweet.contains("queen")) {
                System.out.println("another news in london");
            }
        }
    }

    static class NYObserver implements Observer{
        @Override
        public void notify(String tweet) {
            if (tweet != null && tweet.contains("money")) {
                System.out.println("breaking new in NY" + tweet);
            }
        }
    }

    interface Observer{
        void notify(String tweet);
    }

     static class OnlineBanking {
        public void processCustomer(int id) {
            //dosomething
            //makeHappy(id);
        }

        //abstract void makeHappy(int id);

        //lambda表达式
        public void process(int id, Consumer<Integer> consumer) {
            //dosometing
            consumer.accept(id);
        }
    }

    static class Validator{
        private ValidationStrategy validationStrategy;

        public Validator(ValidationStrategy validationStrategy) {
            this.validationStrategy = validationStrategy;
        }

        public boolean validate(String s) {
            return validationStrategy.execute(s);
        }
    }


    interface ValidationStrategy{
        boolean execute(String s);
    }

    static class IsLowerCaseStrategy implements ValidationStrategy {
        @Override
        public boolean execute(String s) {
            return s.matches("[a-z]+");
        }
    }

    static class IsNumberCaseStrategy implements ValidationStrategy{
        @Override
        public boolean execute(String s) {
            return s.matches("\\d+");
        }
    }

    public static void dosomething(Runnable t){
        t.run();
    }

    public static void dosomething(Task t){
        t.execute();
    }


    interface Task{
        void execute();
    }
}
