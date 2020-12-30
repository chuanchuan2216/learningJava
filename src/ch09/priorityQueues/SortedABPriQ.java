package ch09.priorityQueues;

import java.util.Comparator;

public class SortedABPriQ<T> implements PriQueueInterface<T> {

    protected final int DEFCAP = 100;
    protected T[] elements;
    protected int numElements;

    protected Comparator<T> comp;

    public SortedABPriQ() {
        elements = (T[]) new Object[DEFCAP];
        comp = new Comparator<T>() {
            @Override
            public int compare(T element1, T element2) {
                return ((Comparable) element1).compareTo(element2);
            }
        };
    }

    public SortedABPriQ(Comparator<T> comp) {
        elements = (T[]) new Object[DEFCAP];
        this.comp = comp;
    }

    protected void enlarge() {
        T[] large = (T[]) new Object[elements.length + DEFCAP];
        for (int i = 0; i < numElements; i++) {
            large[i] = elements[i];
        }
        elements = large;
    }

    @Override
    public void enqueue(T element) {
        if (numElements == elements.length) {
            enlarge();
        }
        int index = numElements;
        while ((index > 0) && (comp.compare(elements[index - 1], element) > 0)) {
            elements[index] = elements[index - 1];
            index--;
        }
        elements[index] = element;

        numElements++;
    }

    @Override
    public T dequeue() {
        if (!isEmpty()) {
            T temp = elements[numElements - 1];
            elements[numElements - 1] = null;
            numElements--;
            return temp;
        } else {
            throw new PriQUnderflowException("优先级队列已空，不能出队");
        }
    }

    @Override
    public boolean isEmpty() {
        return (numElements == 0);
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public int size() {
        return numElements;
    }

    @Override
    public String toString() {
        String temp = "\n优先级队列：";
        for (int i = 0; i < numElements; i++) {
            temp = temp + "  " + elements[i];
        }
        temp += "\n";
        return temp;
    }
}
