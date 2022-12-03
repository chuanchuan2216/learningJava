package algs4.searching.structures;

import support.Stdlib.StdIn;
import support.Stdlib.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeMap;

public class ST<Key extends Comparable<Key>, Value> implements Iterable<Key> {
    private TreeMap<Key, Value> st;

    /**
     * 创建一个有序符号表（基于Java的TreeMap）
     */
    public ST() {
        st = new TreeMap<Key, Value>();
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
        return st.get(key);
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
            st.remove(key);
        } else {
            st.put(key, val);
        }
    }

    /**
     * 从符号表中删除键key及其对应的值
     *
     * @param key 键key
     */
    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("调用delete()时没有给定键key");
        }
        st.remove(key);
    }

    /**
     * 从表中删除键key及其对应的值
     *
     * @param key 键key
     */
    public void remove(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("调用remove()时没有给定键key");
        }
        st.remove(key);
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
        return st.containsKey(key);
    }

    /**
     * 返回表中键值对的数量
     *
     * @return 表中键值对的数量
     */
    public int size() {
        return st.size();
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
     * 返回符号表中的所有键
     *
     * @return 表中的所有键
     */
    public Iterable<Key> keys() {
        return st.keySet();
    }

    /**
     * 返回表中的所有键
     *
     * @return 表中的所有键
     */
    @Deprecated
    public Iterator<Key> iterator() {
        return st.keySet().iterator();
    }

    /**
     * 返回符号表中最小的键key
     *
     * @return 表中最小的键key
     */
    public Key min() {
        if (isEmpty()) {
            throw new NoSuchElementException("不能在表为空调用min()时");
        }
        return st.firstKey();
    }

    /**
     * 返回符号表中最大的键key
     *
     * @return 表中最大的键key
     */
    public Key max() {
        if (isEmpty()) {
            throw new NoSuchElementException("不能在表为空调用max()时");
        }
        return st.lastKey();
    }

    /**
     * 返回“大于等于”键key的键
     *
     * @param key 键key
     * @return “大于等于”键key的键
     */
    public Key ceiling(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("调用ceiling()时没有给定键key");
        }
        Key k = st.ceilingKey(key);
        if (k == null) {
            throw new NoSuchElementException("没有“大于等于”键key：" + key + "的键");
        }
        return key;
    }

    /**
     * 返回“小于等于”键key的键
     *
     * @param key 键key
     * @return “小于等于”键key的键
     */
    public Key floor(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("调用floor()时没有给定键key");
        }
        Key k = st.floorKey(key);
        if (k == null) {
            throw new NoSuchElementException("没有“小于等于”键key：" + key + "的键");
        }
        return k;
    }

    public static void main(String[] args) {
        ST<String, Integer> st = new ST<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
    }
}
