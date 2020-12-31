package oods4e.ch06.lists;

import oods4e.ch05.collections.CollectionInterface;

public interface ListInterface<T> extends CollectionInterface<T> {

    void add(int index, T element);

    T set(int index, T newElement);

    T get(int index);

    int indexOf(T target);

    T remove(int index);
}
