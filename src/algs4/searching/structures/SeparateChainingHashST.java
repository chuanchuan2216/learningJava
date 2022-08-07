package algs4.searching.structures;

import algs4.fundamentals.structures.Queue;
import support.Stdlib.StdIn;
import support.Stdlib.StdOut;

public class SeparateChainingHashST<Key,Value> {
    private static final int INIT_CAPACITY = 4;

    private int n;      // 散列表中键值对的数量
    private int m;      // 散列表中链表的数量
    private SequentialSearchST<Key, Value>[] st;    // 存放链表对象的数组

    /**
     * 创建一个散列表表（基于拉链法）
     */
    public SeparateChainingHashST() {
        this(INIT_CAPACITY);
    }

    /**
     * 创建一个具有m条链表的散列表
     * @param m 指定散列表中链表的数量m
     */
    public SeparateChainingHashST(int m) {
        this.m = m;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[m];
        for (int i = 0; i < m; i++) {
            st[i] = new SequentialSearchST<>();
        }
    }

    /**
     * 根据给定的链表的数量chains重置散列表
     * @param chains 给定的链表的数量chains
     */
    private void resize(int chains) {
        SeparateChainingHashST<Key, Value> temp = new SeparateChainingHashST<>(chains);
        for (int i = 0; i < m; i++) {
            for (Key key : st[i].keys()) {
                temp.put(key, st[i].get(key));
            }
        }
        this.m = temp.m;
        this.n = temp.n;
        this.st = temp.st;
    }

    /**
     * 返回键key的散列值（0到m-1之间）
     * @param key 键key
     * @return 键key的散列值
     */
    private int hashTextbook(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    /**
     * 返回键key的散列值（0到m-1之间），假定m是2的幂
     * @param key 键key
     * @return 键key的散列值
     */
    private int hash(Key key) {
        int h = key.hashCode();
        h ^= (h >>> 20) ^ (h >>> 12) ^ (h >>> 7) ^ (h >>> 4);
        return h & (m - 1);
    }

    /**
     * 返回散列表中键值对的数量
     * @return 散列表中键值对的数量
     */
    public int size() {
        return n;
    }

    /**
     * 判断散列表是否为空
     *
     * @return 散列表为空返回true，否则返回false
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 判断键key是否存在于散列表中
     *
     * @param key 键key
     * @return 键key存在于散列表中返回true，否则返回false
     */
    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("调用contains()时没有给定键key");
        }
        return get(key) != null;
    }

    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("调用get()时没有给定键key");
        }
        int i = hash(key);
        return st[i].get(key);
    }

    public void put(Key key, Value val) {
        if (key == null) {
            throw new IllegalArgumentException("调用put()时没有给定键key");
        }
        if (val == null) {
            delete(key);
            return;
        }
        if (n >= 10 * m) {
            resize(2 * m);
        }
        int i = hash(key);
        if (!st[i].contains(key)) {
            n++;
        }
        st[i].put(key, val);
    }

    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("调用delete()时没有给定键key");
        }
        int i = hash(key);
        if (st[i].contains(key)) {
            n--;
        }
        st[i].delete(key);
        if (m > INIT_CAPACITY && n <= 2 * m) {
            resize(m / 2);
        }
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        for (int i = 0; i < m; i++) {
            for (Key key : st[i].keys()) {
                queue.enqueue(key);
            }
        }
        return queue;
    }

    public static void main(String[] args) {
        SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
    }
}
