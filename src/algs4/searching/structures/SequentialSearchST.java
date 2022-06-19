package algs4.searching.structures;

import algs4.fundamentals.structures.Queue;
import support.Stdlib.StdIn;
import support.Stdlib.StdOut;

public class SequentialSearchST<Key, Value> {
    private int n;
    private Node first;

    private class Node {
        private Key key;
        private Value val;
        private Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 创建一个有序符号表（基于顺序查找的无序链表）
     */
    public SequentialSearchST() {
    }

    /**
     * 返回符号表中键值对的数量
     *
     * @return 表中键值对的数量
     */
    public int size() {
        return n;
    }

    /**
     * 判断表是否为空
     *
     * @return 表为空返回true，否则返回false
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * 判断键key是否存在于表中
     *
     * @param key 键key
     * @return 键key存在于表中返回true，否则返回false
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
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return x.val;
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
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        }
        first = new Node(key, val, first);
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
        first = delete(first, key);
    }

    /**
     * 从节点x开始删除链表中的键key
     * @param x 起点节点x
     * @param key 键key
     * @return
     */
    public Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.equals(x.key)) {
            n--;
            return x.next;
        }
        x.next = delete(x.next, key);
        return x;
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (Node x = first; x != null; x = x.next) {
            queue.enqueue(x.key);
        }
        return queue;
    }

    public static void main(String[] args) {
        SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
    }
}
