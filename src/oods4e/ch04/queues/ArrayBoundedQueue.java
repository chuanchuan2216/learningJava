package oods4e.ch04.queues;

public class ArrayBoundedQueue<T> implements QueueInterface<T> {

    protected final int DEFCAP = 100;
    protected T[] elements;
    protected int numElements = 0;
    protected int front = 0;
    protected int rear;

    public ArrayBoundedQueue() {
        elements = (T[]) new Object[DEFCAP];
        rear = DEFCAP - 1;
    }

    public ArrayBoundedQueue(int maxSize) {
        elements = (T[]) new Object[maxSize];
        rear = maxSize - 1;
    }

    @Override
    public void enqueue(T element) throws QueueOverflowException {
        if (isFull()) {
            throw new QueueOverflowException("队列已满，不能入队");
        } else {
            rear = (rear + 1) % elements.length;
            elements[rear] = element;
            numElements++;
        }

    }

    @Override
    public T dequeue() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException("队列已空，不能出队");
        } else {
            T toReturn = elements[front];
            elements[front] = null;
            front = (front + 1) % elements.length;
            numElements--;
            return toReturn;
        }
    }

    @Override
    public boolean isFull() {
        return (numElements == elements.length);
    }

    @Override
    public boolean isEmpty() {
        return (numElements == 0);
    }

    @Override
    public int size() {
        return numElements;
    }
}
