package oods4e.ch08.maps;

public interface MapInterface<K,V> extends Iterable<MapEntry<K,V>> {
    V put(K key,V value);

    V get(K key);

    V remove(K key);

    boolean contains(K key);

    boolean isEmpty();

    boolean isFull();

    int size();
}
