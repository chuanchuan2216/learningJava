package oods4e.ch04.queues;

public interface GlassQueueInterface<T> extends QueueInterface<T> {

    public T peekFront();

    public T peekRear();

}
