package oods4e.ch04.concurrency;

import oods4e.ch04.threads.*;

public class Demo01 {

    public static void main(String[] args) {
        Counter c = new Counter();
        c.increment();
        c.increment();
        c.increment();
        System.out.println("Count is:" + c.getCount());
    }

}
