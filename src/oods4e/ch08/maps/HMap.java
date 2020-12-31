package oods4e.ch08.maps;

import java.util.Iterator;

public class HMap<K, V> implements MapInterface<K, V> {

    protected MapEntry[] map;

    protected final int DEFCAP = 1000;
    protected final double DEFLOAD = 0.75;

    protected int origCap;
    protected int currCap;
    protected double load;

    protected int numPairs = 0;

    public HMap() {
        map = new MapEntry[DEFCAP];
        origCap = DEFCAP;
        currCap = DEFCAP;
        load = DEFLOAD;
    }

    public HMap(int initCap, double initLoad) {
        map = new MapEntry[initCap];
        origCap = initCap;
        currCap = initCap;
        load = initLoad;
    }

    private void enlarge() {
        Iterator<MapEntry<K, V>> i = iterator();
        int count = numPairs;

        map = new MapEntry[currCap + origCap];
        currCap += origCap;
        numPairs = 0;

        MapEntry entry;
        for (int n = 1; n <= count; n++) {
            entry = i.next();
            this.put((K) entry.getKey(), (V) entry.getValue());
        }
    }

    @Override
    public V put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Map不允许键为空值");
        }
        MapEntry<K, V> entry = new MapEntry<K, V>(key, value);

        int location = Math.abs(key.hashCode()) % currCap;
        while ((map[location] != null) && !(map[location].getKey().equals(key))) {
            location = (location + 1) % currCap;
        }
        if (map[location] == null) {
            map[location] = entry;
            numPairs++;
            if ((float) numPairs / currCap > load) {
                enlarge();
            }
            return null;
        } else {
            V temp = (V) map[location].getValue();
            map[location] = entry;
            return temp;
        }
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Map不允许键为空值");
        }
        int location = Math.abs(key.hashCode()) % currCap;
        while ((map[location] != null) && !(map[location].getKey().equals(key))) {
            location = (location + 1) % currCap;
        }
        if ((map[location] == null)) {
            return null;
        } else {
            return (V) map[location].getValue();
        }
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("HMap不允许remove操作");
    }

    @Override
    public boolean contains(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Map不允许键为空值");
        }
        int location = Math.abs(key.hashCode()) % currCap;
        while (map[location] != null) {
            if (map[location].getKey().equals(key)) {
                return true;
            } else {
                location = (location + 1) % currCap;
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return (numPairs == 0);
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public int size() {
        return numPairs;
    }

    @Override
    public Iterator<MapEntry<K, V>> iterator() {
        return new MapIterator();
    }

    private class MapIterator implements Iterator<MapEntry<K, V>> {

        int listSize = size();
        private MapEntry[] list = new MapEntry[listSize];
        private int previousPos = -1;

        public MapIterator() {
            int next = -1;
            for (int i = 0; i < listSize; i++) {
                next++;
                while (map[next] == null) {
                    next++;
                }
                list[i] = map[next];
            }
        }

        @Override
        public boolean hasNext() {
            return (previousPos < (listSize - 1));
        }

        @Override
        public MapEntry<K, V> next() {
            if (!hasNext()) {
                throw new IndexOutOfBoundsException("HMap迭代器无法访问下一个元素。");
            }
            previousPos++;
            return list[previousPos];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("HMap迭代器不支持remove元素。");
        }
    }
}
