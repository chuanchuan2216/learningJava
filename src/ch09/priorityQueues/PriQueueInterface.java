package ch09.priorityQueues;

public interface PriQueueInterface<T> {

    void enqueue(T element);

    T dequeue();

    boolean isEmpty();

    boolean isFull();

    int size();
}
