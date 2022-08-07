package algs4.searching.structures;

import algs4.fundamentals.structures.Queue;
import support.Stdlib.StdIn;
import support.Stdlib.StdOut;

import java.util.NoSuchElementException;

public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private static final int INIT_CAPACITY = 2;
    private Key[] keys;
    private Value[] vals;
    private int n = 0;

    /**
     * 创建一个空的有序符号表（基于二分查找的有序数组）
     */
    public BinarySearchST() {
        this(INIT_CAPACITY);
    }

    /**
     * 创建一个指定键值对个数的有序符号表（基于二分查找的有序数组）
     */
    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    /**
     * 将符号表内的键值对移动到新数组
     *
     * @param capacity 新数组的元素个数
     */
    private void resize(int capacity) {
        assert capacity >= n;
        Key[] tempk = (Key[]) new Comparable[capacity];
        Value[] tempv = (Value[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            tempk[i] = keys[i];
            tempv[i] = vals[i];
        }
        keys = tempk;
        vals = tempv;
    }

    /**
     * 返回表中键值对的数量
     *
     * @return 表中键值对的数量
     */
    public int size() {
        return n;
    }

    /**
     * 判断符号表是否为空
     *
     * @return 符号表为空返回true，否则返回false
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 判断键key是否存在于符号表中
     *
     * @param key 键key
     * @return 键key存在于符号表中返回true，否则返回false
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
        if (isEmpty()) {
            return null;
        }

        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0) {
            return vals[i];
        }
        return null;
    }

    /**
     * 基于有序数组的二分查找（迭代版本）
     * 返回符号表中所有比键key小的键的个数
     *
     * @param key 键key
     * @return 符号表中所有比键key小的键的个数
     */
    public int rank(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("调用rank()时没有给定键key");
        }

        int lo = 0, hi = n - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) {
                hi = mid - 1;
            } else if (cmp > 0) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return lo;
    }

    /**
     * 基于有序数组的二分查找（递归版本）
     * 返回符号表中所有比键key小的键的个数
     *
     * @param key 键key
     * @return 符号表中所有比键key小的键的个数
     */
    public int rank(Key key, int lo, int hi) {
        if (key == null) {
            throw new IllegalArgumentException("调用rank()时没有给定键key");
        }

        if (hi < lo) {
            return lo;
        }
        int mid = lo + (hi + lo) / 2;
        int cmp = key.compareTo(keys[mid]);
        if (cmp < 0) {
            return rank(key, lo, mid - 1);
        } else if (cmp > 0) {
            return rank(key, mid + 1, hi);
        } else {
            return mid;
        }
    }

    /**
     * 将键值对存入符号表中，若值为null则将键key删除
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

        int i = rank(key);

        // 键key已经在符号表中
        if (i < n && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }

        // 插入新的键值对
        if (n == keys.length) {
            resize(2 * keys.length);
        }
        for (int j = n; j > i; j--) {
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }

        keys[i] = key;
        vals[i] = val;
        n++;

        assert check();
    }

    /**
     * 从符号表中删除键key及其对应的值
     *
     * @param key 键key
     */
    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("调用put()时没有给定键key");
        }
        if (isEmpty()) {
            return;
        }
        // 计算符号表中所有比键key小的键的个数
        int i = rank(key);
        // 要删除的键key不存在于符号表中
        if (i == n || keys[i].compareTo(key) != 0) {
            return;
        }
        // 开始删除键key
        for (int j = i; j < n - 1; j++) {
            keys[j] = keys[j + 1];
            vals[j] = vals[j + 1];
        }
        n--;
        keys[n] = null;
        vals[n] = null;
        // 删除后检查是否需要减小数组
        if (n > 0 && n == keys.length / 4) {
            resize(keys.length / 2);
        }

        assert check();
    }

    /**
     * 从符号表中删除最小的键及其关联的值
     */
    public void deleteMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("符号表为空，deleteMin()操作下溢");
        }
        delete(min());
    }

    /**
     * 从符号表中删除最大的键及其关联的值
     */
    public void deleteMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("符号表为空，deleteMax()操作下溢");
        }
        delete(max());
    }

    /**
     * 返回符号表中最小的键key
     *
     * @return 表中最小的键key
     */
    public Key min() {
        if (isEmpty()) {
            throw new NoSuchElementException("符号表为空，min()不能调用");
        }
        return keys[0];
    }

    /**
     * 返回符号表中最大的键key
     *
     * @return 表中最大的键key
     */
    public Key max() {
        if (isEmpty()) {
            throw new NoSuchElementException("符号表为空，max()不能调用");
        }
        return keys[n - 1];
    }

    /**
     * 返回此符号表中的第k个最小键
     *
     * @param k 序数k
     * @return 此符号表中的第k个最小键
     */
    public Key select(int k) {
        if (k < 0 || k > size()) {
            throw new IllegalArgumentException("调用select()时传递了错误的参数：" + k);
        }
        return keys[k];
    }

    /**
     * 返回“小于等于”键key的键
     *
     * @param key 键key
     * @return “小于等于”键key的键
     */
    public Key floor(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("调用floor()时传递了错误的参数null");
        }
        int i = rank(key);
        if (i < n && key.compareTo(keys[i]) == 0) {
            return keys[i];
        }
        if (i == 0) {
            return null;
        } else {
            return keys[i - 1];
        }
    }

    /**
     * 返回“大于等于”键key的键
     *
     * @param key 键key
     * @return “大于等于”键key的键
     */
    public Key ceiling(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("调用ceiling()时传递了错误的参数null");
        }
        int i = rank(key);
        if (i == n) {
            return null;
        } else {
            return keys[i];
        }
    }

    /**
     * 返回符号表中指定范围内的键的个数
     *
     * @param lo 范围的起点lo
     * @param hi 范围的终点hi
     * @return 符号表中指定范围内的键的个数
     */
    public int size(Key lo, Key hi) {
        if (lo == null) {
            throw new IllegalArgumentException("调用size()时传递了错误的起点参数null");
        }
        if (hi == null) {
            throw new IllegalArgumentException("调用size()时传递了错误的终点参数null");
        }

        if (lo.compareTo(hi) > 0) {
            return 0;
        }
        if (contains(hi)) {
            return rank(hi) - rank(lo) + 1;
        } else {
            return rank(hi) - rank(lo);
        }
    }

    /**
     * 返回符号表中的所有键
     *
     * @return 表中的所有键
     */
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    /**
     * 返回符号表中指定范围内的键
     *
     * @param lo 范围的起点lo
     * @param hi 范围的终点hi
     * @return 符号表中指定范围内的键
     */
    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) {
            throw new IllegalArgumentException("调用keys()时传递了错误的起点参数null");
        }
        if (hi == null) {
            throw new IllegalArgumentException("调用keys()时传递了错误的终点参数null");
        }
        Queue<Key> queue = new Queue<>();
        if (lo.compareTo(hi) > 0) {
            return queue;
        }
        for (int i = rank(lo); i < rank(hi); i++) {
            queue.enqueue(keys[i]);
        }
        if (contains(hi)) {
            queue.enqueue(keys[rank(hi)]);
        }
        return queue;
    }

    /**
     * 验证符号表
     *
     * @return 验证通过返回true，否则返回false
     */
    private boolean check() {
        return isSorted() && rankCheck();
    }

    /**
     * 判断数组中的元素是否按照升序排列
     *
     * @return 组中的元素按照升序排列返回true，否则返回false
     */
    private boolean isSorted() {
        for (int i = 1; i < size(); i++) {
            if (keys[i].compareTo(keys[i - 1]) < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断rank(select(i)) = i是否成立
     *
     * @return rank(select ( i)) = i成立返回true，否则返回false
     */
    private boolean rankCheck() {
        for (int i = 0; i < size(); i++) {
            if (i != rank(select(i))) {
                return false;
            }
        }
        for (int i = 0; i < size(); i++) {
            if (keys[i].compareTo(select(rank(keys[i]))) != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        BinarySearchST<String, Integer> st = new BinarySearchST<>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
    }
}
