package algs4.searching.structures;

import algs4.fundamentals.structures.Queue;
import support.Stdlib.In;
import support.Stdlib.StdIn;
import support.Stdlib.StdOut;

public class LinearProbingHashST<Key, Value> {

    private static final int INIT_CAPACITY = 4;      // 必须是2的次方

    private int n;      // 散列表中键值对的数量
    private int m;      // 散列表的大小
    private Key[] keys;      // 保存键的数组
    private Value[] vals;      //保存值的数组

    /**
     * 创建一个散列表表（基于线性探测法）
     */
    public LinearProbingHashST() {
        this(INIT_CAPACITY);
    }

    /**
     * 创建一个大小为capacity的散列表表（基于线性探测法）
     *
     * @param capacity 指定散列表的大小为capacity
     */
    public LinearProbingHashST(int capacity) {
        m = capacity;
        n = 0;
        keys = (Key[]) new Object[m];
        vals = (Value[]) new Object[m];
    }

    /**
     * 根据给定的散列表的大小capacity重置散列表
     *
     * @param capacity 给定的散列表的大小capacity
     */
    private void resize(int capacity) {
        LinearProbingHashST<Key, Value> temp = new LinearProbingHashST<>(capacity);
        for (int i = 0; i < m; i++) {
            if (keys[i] != null) {
                temp.put(keys[i], vals[i]);
            }
        }
        keys = temp.keys;
        vals = temp.vals;
        m = temp.m;
    }

    /**
     * 返回键key的散列值（0到m-1之间）
     *
     * @param key 键key
     * @return 键key的散列值
     */
    private int hashTextbook(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    /**
     * 返回键key的散列值（0到m-1之间），假定m是2的幂
     *
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
     *
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

    /**
     * 获取键key对应的值
     *
     * @param key 键key
     * @return 键key对应的值
     */
    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("调用get()时没有给定键key");
        }
        for (int i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                return vals[i];
            }
        }
        return null;
    }

    /**
     * 将键值对存入表中，若值为null则将键key删除
     *
     * @param key 键key
     * @param val 值val
     */
    public void put(Key key, Value val) {
        if (key == null) {
            throw new IllegalArgumentException("调用put()时没有给定键key");
        }
        if (val == null) {
            delete(key);
            return;
        }
        if (n >= m / 2) {
            resize(2 * m);
        }

        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
        }
        keys[i] = key;
        vals[i] = val;
        n++;
    }

    /**
     * 从表中删除键key及其对应的值
     *
     * @param key 键key
     */
    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("调用delete()时没有给定键key");
        }
        if (!contains(key)) {
            return;
        }
        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = (i + 1) % m;
        }

        keys[i] = null;
        vals[i] = null;

        i = (i + 1) % m;
        while (keys[i] != null) {
            Key keyToRehash = keys[i];
            Value valToRehash = vals[i];
            keys[i] = null;
            vals[i] = null;
            n--;
            put(keyToRehash, valToRehash);
            i = (i + 1) % m;
        }
        n--;
        if (n > 0 && n <= m / 8) {
            resize(m / 2);
        }

        assert check();
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        for (int i = 0; i < m; i++) {
            if (keys[i] != null) {
                queue.enqueue(keys[i]);
            }
        }
        return queue;
    }

    private boolean check() {
        if (m < 2 * n) {
            System.err.println("哈希表大小m为" + m + "，键值对个数n为" + n);
            return false;
        }
        for (int i = 0; i < m; i++) {
            if (keys[i] == null) {
                continue;
            } else if (get(keys[i]) != vals[i]) {
                System.err.println("get[" + keys[i] + "]=" + get(keys[i]) + ":vals[i]=" + vals[i]);
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LinearProbingHashST<String, Integer> st = new LinearProbingHashST<>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        for (String s :
                st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
    }
}
