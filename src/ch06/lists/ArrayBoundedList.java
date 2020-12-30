package ch06.lists;

import java.util.Iterator;

public class ArrayBoundedList<T> implements ListInterface<T> {

    protected final int DEFCAP = 100;
    protected int origCap;
    protected T[] elements;
    protected int numElements;

    protected boolean found;
    protected int location;

    public ArrayBoundedList() {
        elements = (T[]) new Object[DEFCAP];
        origCap = DEFCAP;
    }

    public ArrayBoundedList(int origCap) {
        elements = (T[]) new Object[origCap];
        this.origCap = origCap;
    }

    public void enlarge() {
        T[] larger = (T[]) new Object[elements.length + origCap];
        for (int i = 0; i < numElements; i++) {
            larger[i] = elements[i];
        }
        elements = larger;
    }

    public void find(T target) {
        location = 0;
        found = false;
        while (location < numElements) {
            if (elements[location].equals(target)) {
                found = true;
                return;
            } else {
                location++;
            }
        }
    }

    @Override
    public boolean add(T element) {
        if (numElements == elements.length)
            enlarge();
        elements[numElements] = element;
        numElements++;
        return true;
    }

    @Override
    public T get(T target) {
        find(target);
        if (found)
            return elements[location];
        else
            return null;
    }

    @Override
    public boolean contains(T target) {
        find(target);
        return found;
    }

    @Override
    public boolean remove(T target) {
        find(target);
        if (found) {
            for (int i = location; i <= numElements - 2; i++) {
                elements[i] = elements[i + 1];
            }
            elements[numElements - 1] = null;
            numElements--;
        }
        return found;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return (numElements == 0);
    }

    @Override
    public int size() {
        return numElements;
    }

    @Override
    public void add(int index, T element) {
        if ((index < 0) || (index >= size())) {
            throw new IndexOutOfBoundsException("索引值：" + index + "超出了ArrayBoundedList列表索引范围，无法add()。");
        }
        if (numElements == elements.length)
            enlarge();
        for (int i = numElements; i > index; i--)
            elements[i] = elements[i - 1];

        elements[index] = element;
        numElements++;
    }

    @Override
    public T set(int index, T newElement) {
        if ((index < 0) || (index >= size())) {
            throw new IndexOutOfBoundsException("索引值：" + index + "超出了ArrayBoundedList列表索引范围，无法set()。");
        }
        T hold = elements[index];
        elements[index] = newElement;
        return hold;
    }

    @Override
    public T get(int index) {
        if ((index < 0) || (index >= size())) {
            throw new IndexOutOfBoundsException("索引值：" + index + "超出了ArrayBoundedList列表索引范围。");
        }
        return elements[index];
    }

    @Override
    public int indexOf(T target) {
        find(target);
        if (found)
            return location;
        else
            return -1;
    }

    @Override
    public T remove(int index) {
        if ((index < 0) || (index >= size())) {
            throw new IndexOutOfBoundsException("索引值：" + index + "超出了ArrayBoundedList列表索引范围，无法remove()。");
        }
        T hold = elements[index];
        for (int i = index; i < numElements - 1; i++)
            elements[i] = elements[i + 1];
        elements[numElements - 1] = null;
        numElements--;
        return hold;
    }

    public Iterator<T> interator() {
        return new Iterator<T>() {
            private int previousPos = -1;

            @Override
            public boolean hasNext() {
                return (previousPos < (size() - 1));
            }

            @Override
            public T next() {
                if (!hasNext())
                    throw new IndexOutOfBoundsException("ArrayBoundedList列表迭代器无法访问下一个元素。");
                previousPos++;
                return elements[previousPos];
            }

            public void remove() {
                for (int i = previousPos; i <= numElements; i++) {
                    elements[i] = elements[i + 1];
                }
                elements[numElements - 1] = null;
                numElements--;
                previousPos--;
            }
        };
    }

}
