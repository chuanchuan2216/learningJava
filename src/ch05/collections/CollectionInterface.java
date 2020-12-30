package ch05.collections;

public interface CollectionInterface<T> {

    boolean add(T element);

    T get(T target);

    boolean contains(T target);

    boolean remove(T target);

    boolean isFull();

    boolean isEmpty();

    int size();

}
