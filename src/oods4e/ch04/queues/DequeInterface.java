package oods4e.ch04.queues;

public interface DequeInterface<T> {

    void enqueueFront(T element) throws QueueOverflowException;

    void enqueueRear(T element) throws QueueOverflowException;

    T dequeueFront() throws QueueUnderflowException;

    T dequeueRear() throws QueueUnderflowException;

    boolean isFull();

    boolean isEmpty();

    int size();

}
