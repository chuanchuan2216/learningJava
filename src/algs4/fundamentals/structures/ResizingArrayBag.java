package algs4.fundamentals.structures;

import support.Stdlib.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayBag<Item> implements Iterable<Item> {
    private static final int INIT_CAPACITY = 8;

    private Item[] a;
    private int n;

    /**
     * 初始化一个空的背包
     */
    public ResizingArrayBag() {
        a = (Item[]) new Object[INIT_CAPACITY];
        n = 0;
    }

    /**
     * 判断背包是否为空
     *
     * @return {@code true} 背包为空返回true
     * {@code false} 背包不空返回false
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * 返回背包元素的个数
     *
     * @return 背包元素的个数
     */
    public int size() {
        return n;
    }

    /**
     * 将背包内元素移动到新数组
     *
     * @param capacity 新数组的元素个数
     */
    private void resize(int capacity) {
        assert capacity >= n;
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = a[i];
        }
        a = copy;
    }

    /**
     * 向背包添加元素
     *
     * @param item 需要添加的元素
     */
    public void add(Item item) {
        if (n == a.length) {
            resize(2 * a.length);
        }
        a[n++] = item;
    }

    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int i = 0;

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return i < n;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return a[i++];
        }
    }

    public static void main(String[] args) {
        ResizingArrayBag<String> bag = new ResizingArrayBag<String>();
        bag.add("Hello");
        bag.add("World");
        bag.add("how");
        bag.add("are");
        bag.add("you");

        for (String s : bag)
            StdOut.println(s);
    }
}
