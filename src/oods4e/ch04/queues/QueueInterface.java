package oods4e.ch04.queues;

public interface QueueInterface<T> {

    void enqueue(T element) throws QueueOverflowException;

    T dequeue() throws QueueUnderflowException;

    boolean isFull();

    boolean isEmpty();

    int size();
}
