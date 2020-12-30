package ch09.priorityQueues;

import java.util.ArrayList;
import java.util.Comparator;

public class HeapPriQ<T> implements PriQueueInterface<T> {

    protected ArrayList<T> elements;
    protected int lastIndex;
    protected int maxIndex;

    protected Comparator<T> comp;

    public HeapPriQ(int maxSize) {
        elements = new ArrayList<T>(maxSize);
        lastIndex = -1;
        maxIndex = maxSize - 1;

        comp = new Comparator<T>() {
            @Override
            public int compare(T element1, T element2) {
                return ((Comparable) element1).compareTo(element2);
            }
        };
    }

    public HeapPriQ(int maxSize, Comparator<T> comp) {
        elements = new ArrayList<T>(maxSize);
        lastIndex = -1;
        maxIndex = maxSize - 1;

        this.comp = comp;
    }

    @Override
    public void enqueue(T element) throws PriQOverflowException {
        if (lastIndex == maxIndex) {
            throw new PriQOverflowException("优先级队列已满。");
        } else {
            lastIndex++;
            elements.add(lastIndex, element);
            reheadUp(element);
        }
    }

    protected void reheadUp(T element) {
        int hole = lastIndex;
        while ((hole > 0)
                &&
                (comp.compare(element, elements.get((hole - 1) / 2)) > 0)) {
            elements.set(hole, elements.get((hole - 1) / 2));
            hole = (hole - 1) / 2;
        }
        elements.set(hole, element);
    }

    @Override
    public T dequeue() {
        T hold;
        T toMove;

        if (lastIndex == -1) {
            throw new PriQUnderflowException("优先级队列已空。");
        } else {
            hold = elements.get(0);
            toMove = elements.remove(lastIndex);
            lastIndex--;
            if (lastIndex != -1) {
                reheadDown(toMove);
            }
            return hold;
        }
    }

    private void reheadDown(T element) {
        int hole = 0;
        int next;

        next = newHole(hole, element);

        while (next != hole) {
            elements.set(hole, element);
            hole = next;
            next = newHole(hole, element);
        }
        elements.set(hole, element);
    }

    private int newHole(int hole, T element) {
        int left = (hole * 2) + 1;
        int right = (hole * 2) + 2;

        if (left > lastIndex) {
            return hole;
        } else if (left == lastIndex) {
            if (comp.compare(element, elements.get(left)) < 0) {
                return left;
            } else {
                return hole;
            }
        } else if (comp.compare(elements.get(left), elements.get(right)) < 0) {
            if (comp.compare(elements.get(right), element) <= 0) {
                return hole;
            } else {
                return right;
            }
        } else if (comp.compare(elements.get(left), element) <= 0) {
            return hole;
        } else {
            return left;
        }
    }

    @Override
    public boolean isEmpty() {
        return (lastIndex == -1);
    }

    @Override
    public boolean isFull() {
        return (lastIndex == maxIndex);
    }

    @Override
    public int size() {
        return (lastIndex + 1);
    }

    @Override
    public String toString() {
        String theHeap = new String("堆里的元素是：\n");
        for (int index = 0; index <= lastIndex; index++) {
            theHeap = theHeap + index + ". " + elements.get(index) + "\n";
        }
        return theHeap;
    }
}
