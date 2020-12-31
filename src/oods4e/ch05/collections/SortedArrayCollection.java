package oods4e.ch05.collections;

public class SortedArrayCollection<T> implements CollectionInterface<T> {

    protected final int DEFCAP = 100;
    protected int origCap;
    protected T[] elements;
    protected int numElements;

    protected boolean found;
    protected int location;

    public SortedArrayCollection() {
        elements = (T[]) new Object[DEFCAP];
        origCap = DEFCAP;
    }

    public SortedArrayCollection(int capacity) {
        elements = (T[]) new Object[capacity];
        origCap = capacity;
    }

    public void enlarge() {
        T[] larger = (T[]) new Object[elements.length + origCap];
        for (int i = 0; i < numElements; i++) {
            larger[i] = elements[i];
        }
        elements = larger;
    }

    protected void find(T target) {
        location = 0;
        found = false;
        if (!isEmpty()) {
            recFind(target, 0, numElements - 1);
        }
    }

    protected void recFind(T target, int first, int last) {
        int result;
        if (first > last) {
            found = false;
            result = ((Comparable) target).compareTo(elements[location]);
            if (result > 0) {
                location++;
            }
        } else {
            location = (first + last) / 2;
            result = ((Comparable) target).compareTo(elements[location]);
            if (result == 0) {
                found = true;
            } else if (result > 0) {
                recFind(target, location + 1, last);
            } else {
                recFind(target, first, location - 1);
            }
        }
    }

    @Override
    public boolean add(T element) {
        if (numElements == elements.length)
            enlarge();
        find(element);
        for (int index = numElements; index > location; index--) {
            elements[index] = elements[index - 1];
        }
        elements[location] = element;
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
            for (int index = location; index <= numElements - 2; index++)
                elements[index] = elements[index + 1];
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
}
