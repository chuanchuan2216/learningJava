package ch08.maps;

public class MapEntry<K, V> {
    protected K key;
    protected V value;

    public MapEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Key:" + key + "\nValue:" + value;
    }
}
