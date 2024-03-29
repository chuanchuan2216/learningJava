package algs4.fundamentals;

import support.Stdlib.StdOut;

import java.util.Arrays;
import java.util.Comparator;

public class Transaction implements Comparable<Transaction>{
    private final String who;
    private final Date when;
    private final double amount;

    public Transaction(String who, Date when, double amount) {
        if(Double.isNaN(amount)||Double.isInfinite(amount)){
            throw new IllegalArgumentException("数量必须是一个有效的数值");
        }
        this.who = who;
        this.when = when;
        this.amount = amount;
    }

    public Transaction(String transaction) {
        String[] a = transaction.split("\\s+");
        who = a[0];
        when= new Date(a[1]);
        amount = Double.parseDouble(a[2]);
        if(Double.isNaN(amount)||Double.isInfinite(amount)){
            throw new IllegalArgumentException("数量必须是一个有效的数值");
        }
    }

    public String who(){
        return who;
    }

    public Date when(){
        return when;
    }

    public double amount(){
        return amount;
    }

    @Override
    public String toString() {
        return String.format("%-10s %10s %8.2f",who,when,amount);
    }

    @Override
    public int compareTo(Transaction that) {
        return Double.compare(this.amount, that.amount);
    }

    @Override
    public boolean equals(Object other) {
        if(other == this){
            return true;
        }
        if(other == null){
            return false;
        }
        if(other.getClass()!=this.getClass()){
            return false;
        }
        Transaction that = (Transaction) other;
        return (this.amount == that.amount)&&(this.who==that.who)&&(this.when==that.when);
    }

    @Override
    public int hashCode() {
        int hash =1;
        hash = 31*hash+who.hashCode();
        hash = 31*hash+when.hashCode();
        hash = 31*hash+((Double)amount).hashCode();
        return hash;
    }

    public static class WhoOrder implements Comparator<Transaction>{
        @Override
        public int compare(Transaction v, Transaction w) {
            return v.who.compareTo(w.who);
        }
    }

    public static class WhenOrder implements Comparator<Transaction>{
        @Override
        public int compare(Transaction v, Transaction w) {
            return v.when.compareTo(w.when);
        }
    }

    public static class HowMuchOrder implements Comparator<Transaction>{
        @Override
        public int compare(Transaction v, Transaction w) {
            return Double.compare(v.amount,w.amount);
        }
    }

    public static void main(String[] args) {
        Transaction[] a = new Transaction[4];
        a[0] = new Transaction("Turing   1990.6.17  644.08");
        a[1] = new Transaction("Tarjan   2002.3.26 4121.85");
        a[2] = new Transaction("Knuth    1999.6.14  288.34");
        a[3] = new Transaction("Dijkstra 2007.8.22 2678.40");

        StdOut.println("Unsorted");
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
        StdOut.println();

        StdOut.println("Sort by date");
        Arrays.sort(a, new Transaction.WhenOrder());
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
        StdOut.println();

        StdOut.println("Sort by customer");
        Arrays.sort(a, new Transaction.WhoOrder());
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
        StdOut.println();

        StdOut.println("Sort by amount");
        Arrays.sort(a, new Transaction.HowMuchOrder());
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
        StdOut.println();
    }
}
