package oods4e.ch06.lists;

import java.util.Comparator;
import java.util.Iterator;

public class SortedABList<T> implements ListInterface<T> {

    protected final int DEFCAP = 100;
    protected T[] list;
    protected int numElements = 0;

    protected Comparator<T> comp;

    protected boolean found;
    protected int location;

    public SortedABList() {
        list = (T[]) new Object[DEFCAP];
        comp = new Comparator<T>() {
            @Override
            public int compare(T element1, T element2) {
                return ((Comparable) element1).compareTo(element2);
            }
        };
    }

    public SortedABList(Comparator<T> comp) {
        list = (T[]) new Object[DEFCAP];
        this.comp = comp;
    }

    protected void enlarge() {
        T[] larger = (T[]) new Object[list.length + DEFCAP];

        for (int i = 0; i < numElements; i++)
            larger[i] = list[i];

        list = larger;
    }

    protected void find(T target) {
        location = 0;
        found = false;
        if (!isEmpty())
            recFind(target, 0, numElements - 1);
    }

    protected void recFind(T target, int first, int last) {
        int result;
        if (first > last) {
            found = false;
            result = comp.compare(target, list[location]);
            if (result > 0)
                location++;
        } else {
            location = (first + last) / 2;
            result = comp.compare(target, list[location]);
            if (result == 0)
                found = true;
            else if (result > 0)
                recFind(target, location + 1, last);
            else
                recFind(target, first, location - 1);
        }
    }

    @Override
    public boolean add(T element) {
        if (numElements == list.length)
            enlarge();
        find(element);
        for (int index = numElements; index > location; index--)
            list[index] = list[index - 1];
        list[location] = element;
        numElements++;
        return true;
    }

    @Override
    public T get(T target) {
        find(target);
        if (found)
            return list[location];
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
            for (int i = location; i < numElements - 2; i++)
                list[i] = list[i + 1];
            list[numElements - 1] = null;
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
        throw new UnsupportedOperationException("有序列表不支持按索引add方法");
    }

    @Override
    public T set(int index, T newElement) {
        throw new UnsupportedOperationException("有序列表不支持按索引set方法");
    }

    @Override
    public T get(int index) {
        if ((index < 0) || (index > size()))
            throw new IndexOutOfBoundsException("无效的索引值：" + index);
        return list[index];
    }

    @Override
    public int indexOf(T target) {
        find(target);
        if (found) {
            while ((location != 0) && (comp.compare(list[location - 1], target) == 0))
                location--;
            return location;
        } else
            return -1;
    }

    @Override
    public T remove(int index) {
        if ((index < 0) || (index > size()))
            throw new IndexOutOfBoundsException("无效的索引值：" + index);

        T hold = list[index];
        for (int i = index; i < numElements - 1; i++)
            list[i] = list[i + 1];
        list[numElements - 1] = null;
        numElements--;
        return hold;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private int previousPos = -1;

            @Override
            public boolean hasNext() {
                return (previousPos < (size() - 1));
            }

            @Override
            public T next() {
                if (!hasNext())
                    throw new IndexOutOfBoundsException("SortedABList列表迭代器无法访问下一个元素。");
                previousPos++;
                return list[previousPos];
            }

            @Override
            public void remove() {
                for (int i = previousPos; i <= numElements - 2; i++)
                    list[i] = list[i + 1];
                list[numElements - 1] = null;
                numElements--;
                previousPos--;
            }
        };
    }
}
