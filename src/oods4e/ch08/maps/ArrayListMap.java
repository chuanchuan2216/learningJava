package oods4e.ch08.maps;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class ArrayListMap<K, V> implements MapInterface<K, V> {

    protected ArrayList<MapEntry<K, V>> map;

    public ArrayListMap() {
        this.map = new ArrayList<MapEntry<K, V>>();
    }

    public ArrayListMap(int initCapacity) {
        this.map = new ArrayList<MapEntry<K, V>>(initCapacity);
    }

    @Override
    public V put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Map不允许键为空值");
        }

        MapEntry<K, V> entry = new MapEntry<K, V>(key, value);

        MapEntry<K, V> temp;
        Iterator<MapEntry<K, V>> search = map.iterator();
        while (search.hasNext()) {
            temp = search.next();
            if (temp.getKey().equals(key)) {
                search.remove();
                map.add(entry);
                return temp.getValue();
            }
        }
        map.add(entry);
        return null;
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Map不允许键为空值");
        }

        for (MapEntry<K, V> temp : map) {
            if (temp.getKey().equals(key)) {
                return temp.getValue();
            }
        }
        return null;
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Map不允许键为空值");
        }
        MapEntry<K,V> temp;
        Iterator<MapEntry<K,V>> search = map.iterator();
        while(search.hasNext()){
            temp = search.next();
            if (temp.getKey().equals(key)){
                search.remove();
                return temp.getValue();
            }
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Map不允许键为空值");
        }

        for (MapEntry<K,V> temp : map){
            if (temp.getKey().equals(key)){
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean isEmpty() {
        return (map.size()==0);
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public Iterator<MapEntry<K, V>> iterator() {
        return map.iterator();
    }
}
