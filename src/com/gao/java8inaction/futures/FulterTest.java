package com.gao.java8inaction.futures;


import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * User: wangchen
 * Date: 16/4/10
 * Time: 19:29
 */
public class FulterTest {

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

    }



    static class Shop {

        static Random random = new Random(47);

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

        public double getPrice(String product) {
            return calculatePrice(product);
        }

        private double calculatePrice(String product) {

            delay();
            return random.nextDouble() * product.charAt(0) + product.charAt(1);
        }

    }
}
