package com.gao.java8inaction.futures;


import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * User: wangchen
 * Date: 16/4/10
 * Time: 19:29
 */
public class FulterTest {

    static List<Shop> shops = Arrays.asList(new Shop("BestPrice"), new Shop("LetsSaveBig"), new Shop("MyFavoriteShop"), new Shop("BuyItAll"));

    public static void main(String[] args) {


        Shop shop = new Shop();

        long start = System.nanoTime();

        Future<Double> priceAsync = shop.getPriceAsync("my favorite product");

        long invocationTime = (System.nanoTime() - start) / 1_000_000;

        System.out.println("invokation returned after " + invocationTime + " ms");

        try {
            Double aDouble = priceAsync.get();
            System.out.printf("price is %.2f%n", aDouble);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println("donw");

        //实现非block队列


        long start1 = System.nanoTime();
        System.out.println(findPrices("myPhone27S"));
        System.out.println((System.nanoTime() - start1) / 1000000 + " msecs");


        long start2 = System.nanoTime();
        System.out.println(findPricesParall("myPhone27S"));
        System.out.println((System.nanoTime() - start2) / 1000000 + " msecs");


        long start3 = System.nanoTime();
        System.out.println(findPricess("myPhone27S"));
        System.out.println((System.nanoTime() - start3) / 1000000 + " msecs");

    }

    public static class Discount {

        public enum Code {
            NONE(0),SELVER(5),GOLD(10),PLATINUM(15),DIAMOND(20);

            private final int percentage;

            Code(int percentage) {
                this.percentage = percentage;
            }
        }

        public static String applyDiscount(Quote quote) {

            return quote.getShopName() + " price is " + Discount.apply(quote.getPrice(), quote.getDiscountCode());
        }

        private static double apply(double price, Code code) {

            return price * (100 - code.percentage) / 100;
        }
    }

    public static class Quote {

        private final String shopName;

        private final double price;

        private final Discount.Code discountCode;

        public Quote(String shopName, double price, Discount.Code discountCode) {
            this.shopName = shopName;
            this.price = price;
            this.discountCode = discountCode;
        }

        public static Quote parse(String s) {

            String[] split = s.split(":");
            String shopName = split[0];
            double price = Double.parseDouble(split[1]);
            Discount.Code discountCode = Discount.Code.valueOf(split[2]);
            return new Quote(shopName, price, discountCode);
        }

        public String getShopName() {
            return shopName;
        }

        public double getPrice() {
            return price;
        }

        public Discount.Code getDiscountCode() {
            return discountCode;
        }
    }

    public static List<String> findPrices(String product) {

        return shops.stream().map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product))).collect(Collectors.toList());

    }

    public static List<String> findPricess(String product) {

        return shops.stream().map(shop -> shop.getPrice2(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(Collectors.toList());
    }

    public static List<String> findPricesParall(String product) {

        return shops.parallelStream().map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product))).collect(Collectors.toList());
    }



    static class Shop {

        private String name;

        static Random random = new Random(47);

        public Shop() {
        }

        public Shop(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public static void delay() {

            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        public Future<Double> getPriceAsync(String product) {

            CompletableFuture<Double> futurePrice = new CompletableFuture<>();

            new Thread(() -> {
                try {
                    double price = calculatePrice(product);
                    futurePrice.complete(price);
                } catch (Exception e) {
                    futurePrice.completeExceptionally(e);
                }
            }).start();

            return futurePrice;
        }

        public Future<Double> getPriceAsync2(String product) {
            return CompletableFuture.supplyAsync(() -> calculatePrice(product));
        }

        public String getPrice2(String product) {

            double price = calculatePrice(product);

            Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];

            return String.format("%s:%.2f:%s", name, price, code);
        }

        public double getPrice(String product) {
            return calculatePrice(product);
        }

        private double calculatePrice(String product) {

            delay();
            return random.nextDouble() * product.charAt(0) + product.charAt(1);
        }

    }
}
