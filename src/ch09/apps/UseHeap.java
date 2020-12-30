package ch09.apps;

import ch09.priorityQueues.HeapPriQ;
import ch09.priorityQueues.PriQueueInterface;

public class UseHeap {
    public static void main(String[] args) {
        PriQueueInterface<String> h;
        h=new HeapPriQ<>(10);
        h.enqueue("J");
        h.enqueue("A");
        h.enqueue("M");
        h.enqueue("B");
        h.enqueue("L");
        h.enqueue("E");

        System.out.println(h);

        System.out.println(h.dequeue()+"\n");

        System.out.println(h);
    }
}
