package com.gao.java8inaction.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * User: wangchen
 * Date: 15/8/16
 * Time: 10:31
 */
public class TraderPractise {

    public static void main(String[] args) {

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cmbridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactionList = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );


        //find all transaction in year 2011 and sort them by name(from small to high)
        List<Transaction> collect = transactionList.stream().filter(r -> r.getYear() == 2011).sorted(Comparator.comparing(Transaction::getValue)).collect(Collectors.toList());
        System.out.println(collect);

        //what are all the unique cities where the traders work
        List<String> collect1 = transactionList.stream().map(r -> r.getTrader().getCity()).distinct().sorted().collect(Collectors.toList());
        System.out.println(collect1);

        //return a string of all traders' names sorted alphabetically
        List<String> collect2 = transactionList.stream().map(r -> r.getTrader().getName()).sorted().collect(Collectors.toList());
        System.out.println(collect2);

        //find all traders from cambridge and sort then by name
        List<Trader> cambridge = transactionList.stream().filter(transaction -> transaction.getTrader().getCity().equals("Cambridge")).map(transaction1 -> transaction1.getTrader()).sorted(Comparator.comparing(Trader::getName)).collect(Collectors.toList());
        System.out.println(cambridge);

        //are any traders based in milan
        boolean milan = transactionList.stream().anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
        System.out.println(milan);


        //print all transactions; values from the traders living in cambridge
        List<Integer> cambridge1 = transactionList.stream().filter(transaction -> transaction.getTrader().getCity().equals("Cambridge")).map(Transaction::getValue).collect(Collectors.toList());
        System.out.println(cambridge1);

        //what's the highest value of all the transactions
        Transaction transaction = transactionList.stream().max(Comparator.comparing(Transaction::getValue)).get();
        System.out.println(transaction.getValue());

        //find the transaction with the smallest value
        Integer integer = transactionList.stream().map(Transaction::getValue).reduce(Integer::min).get();
        System.out.println(integer);

    }
}
