package oods4e.ch09.apps;

import oods4e.ch09.priorityQueues.PriQueueInterface;
import oods4e.ch09.priorityQueues.SortedABPriQ;

public class UseSortedABPriQ {
    public static void main(String[] args) {
        PriQueueInterface<String> pq;
        pq = new SortedABPriQ<>();

        pq.enqueue("C");
        pq.enqueue("O");
        pq.enqueue("M");
        pq.enqueue("P");
        pq.enqueue("U");
        pq.enqueue("T");
        pq.enqueue("E");
        pq.enqueue("R");

        System.out.println(pq);

        System.out.println(pq.dequeue());
        System.out.println(pq.dequeue());

        System.out.println(pq);
    }
}
