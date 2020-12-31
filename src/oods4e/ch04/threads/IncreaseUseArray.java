package oods4e.ch04.threads;

import oods4e.ch04.queues.*;

public class IncreaseUseArray implements Runnable {

    private SyncCounter sc;
    private QueueInterface<Integer> q;

    public IncreaseUseArray(SyncCounter sc, QueueInterface<Integer> q) {
        this.sc = sc;
        this.q = q;
    }

    @Override
    public void run() {
        int hold;
        while (!q.isEmpty()) {
            hold = q.dequeue();
            for (int i = 1; i <= hold; i++)
                sc.increment();
        }

    }

}
