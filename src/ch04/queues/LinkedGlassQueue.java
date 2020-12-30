package ch04.queues;

public class LinkedGlassQueue<T> extends LinkedQueue<T> implements GlassQueueInterface<T> {

    public LinkedGlassQueue() {
        super();
    }

    @Override
    public T peekFront() {
        if (isEmpty()) {
            return null;
        } else {
            return front.getInfo();
        }
    }

    @Override
    public T peekRear() {
        if (isEmpty()) {
            return null;
        } else {
            return rear.getInfo();
        }
    }

}
