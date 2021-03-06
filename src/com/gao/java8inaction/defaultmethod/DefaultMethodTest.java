package com.gao.java8inaction.defaultmethod;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * User: wangchen
 * Date: 16/3/29
 * Time: 23:22
 */
public class DefaultMethodTest {

    public static void main(String[] args) {

        //List接口的sort方法
        List<Integer> asList = Arrays.asList(2, 5, 7, 8, 3, 65, 21);
        asList.sort(Comparator.naturalOrder());

        //default方法调用
        Elllipse elllipse = new Elllipse();
        elllipse.score();

        Monster monster = new Monster();
        monster.rotateBy(232);
        monster.moveHorizontally(343);

        //继承不同的类,但是有同样的方法
        C c = new C();
        c.hello();

        new E().hello();

        new G().hello();

        //如何创建Optional

        //创建一个empty optional
        Optional<Object> empty = Optional.empty();
        System.out.println(empty.isPresent());

        //传建一个
        Optional<Car> carOptional = Optional.of(new Car());
        System.out.println(carOptional.isPresent());
        System.out.println(carOptional.get());

        Optional<Object> o = Optional.ofNullable(null);
        System.out.println(o.isPresent());


        //System.out.println(getCarInsuranceOption(Optional.of(new PersonNew())));

        //filter方法的使用
        Insurance insurance = new Insurance();
        insurance.setName("chinese");

        Optional.of(insurance).filter(p -> "chinese".equals(p.getName())).ifPresent(x -> System.out.println("hello" + x));


    }



    static class PersonNew {

        private Optional<CarNew> car;

        public Optional<CarNew> getCar() {
            return car;
        }
    }

    //重写新的方法
    static class CarNew {

        private Optional<Insurance> insurance;

        public Optional<Insurance> getInsurance() {
            return insurance;
        }
    }

    static Optional<Insurance> nullSafeFindCheapeastInsurance(Optional<Person> person, Optional<Car> car) {

        if (person.isPresent() && car.isPresent()) {
            return Optional.of(findCheapestInsurance(person.get(), car.get()));
        }

        return Optional.empty();

        //第二种更为简单的写法
        //return person.flatMap(p -> car.map(c -> findCheapestInsurance(p, c)));
    }

    static Insurance findCheapestInsurance(Person person, Car car) {

        //查找最便宜的机票
        return new Insurance();
    }

    static String getCarInsuranceOption(Optional<PersonNew> person) {

        return person.flatMap(PersonNew::getCar).flatMap(CarNew::getInsurance).map(Insurance::getName).orElse("null");
    }

    static String getCarInsuranceNameOne(Person person) {

        //老的避免出现空指针异常的解决方案
        if (person != null) {

            Car car = person.getCar();

            if (car != null) {

                Insurance insurance = car.getInsurance();

                if (insurance != null) {
                    return insurance.getName();
                }
            }
        }

        return null;
    }

    static String getCarInsuranceName(Person person) {

        //这里可能抛出异常,尤其是NullPointException
        return person.getCar().getInsurance().getName();
    }

    static class Person {
        private Car car;

        public Car getCar() {
            return car;
        }
    }

    static class Car {
        private Insurance insurance;

        public Insurance getInsurance() {
            return insurance;
        }
    }

    static class Insurance {

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    //编译器错误,因为不能识别那个方法
    //class M implements A, H {}

    static class N implements A, H {
        @Override
        public void hello() {
            A.super.hello();
        }
    }

    static class G extends F implements B, A{

    }

    static class F implements A{
        @Override
        public void hello() {
            System.out.println("hello from e");
        }
    }

    static class E extends D implements B{

    }

    static class C implements B, A {
    }

    static class D implements A{

    }

    interface B extends A{
        @Override
        default void hello(){
            System.out.println("hello from B");
        }
    }

    interface H {
        default void hello() {
            System.out.println("hello from H");
        }
    }

    interface A {
        default void hello() {
            System.out.println("hello from A");
        }
    }

    static class Monster implements Moveable, Resizable, Rotatable{
        @Override
        public int getX() {
            return 0;
        }

        @Override
        public int getY() {
            return 0;
        }

        @Override
        public void setX(int x) {

        }

        @Override
        public void setY(int y) {

        }

        @Override
        public int getWidth() {
            return 0;
        }

        @Override
        public int getHeight() {
            return 0;
        }

        @Override
        public void setWidth(int width) {

        }

        @Override
        public void setHeight(int height) {

        }

        @Override
        public void setAbsoluteSize(int width, int height) {

        }

        @Override
        public void setRotationAngle(int angleInDegree) {

        }

        @Override
        public int getRotationAngle() {
            return 0;
        }
    }

    interface Moveable {

        int getX();

        int getY();

        void setX(int x);

        void setY(int y);

        default void moveHorizontally(int distance) {
            setX(getX() + distance);
        }

        default void moveVertically(int distance) {
            setY(getY() + distance);
        }
    }

    interface Rotatable {

        void setRotationAngle(int angleInDegree);

        int getRotationAngle();

        default void rotateBy(int angleInDegree) {
            setRotationAngle((getRotationAngle() + angleInDegree) % 360);
        }
    }

    static class Elllipse implements Resizable{
        @Override
        public int getWidth() {
            return 0;
        }

        @Override
        public int getHeight() {
            return 0;
        }

        @Override
        public void setWidth(int width) {

        }

        @Override
        public void setHeight(int height) {

        }

        @Override
        public void setAbsoluteSize(int width, int height) {

        }
    }

    interface Resizable {

        int getWidth();

        int getHeight();

        void setWidth(int width);

        void setHeight(int height);

        void setAbsoluteSize(int width, int height);

        default void score(){
            System.out.println("default method score");
        }
    }
}
