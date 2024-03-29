package oods4e.ch04.concurrency;

import oods4e.ch04.threads.*;
import oods4e.ch04.queues.*;

public class Demo06 {

    public static void main(String[] args) throws InterruptedException {

        int LIMIT = 100;
        SyncCounter sc = new SyncCounter();
        QueueInterface<Integer> q;
        q = new ArrayBoundedQueue<Integer>(LIMIT);

        for (int i = 1; i <= LIMIT; i++)
            q.enqueue(i);

        Runnable r1 = new IncreaseUseArray(sc, q);
        Runnable r2 = new IncreaseUseArray(sc, q);
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Count is:" + sc.getCount());
    }

}
