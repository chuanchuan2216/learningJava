package algs4.fundamentals.structures;

import support.Stdlib.StdIn;
import support.Stdlib.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedBag<Item> implements Iterable<Item> {
    private Node first;
    private int n;

    private class Node {
        private Item item;
        private Node next;
    }

    /**
     * 初始化一个空的背包
     */
    public LinkedBag() {
        first = null;
        n = 0;
    }

    /**
     * 判断背包是否为空
     *
     * @return {@code true} 背包为空返回true
     * {@code false} 背包不空返回false
     */
    public boolean isEmpty() {
        return first == null;
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
     * 向背包添加元素
     *
     * @param item 需要添加的元素
     */
    public void add(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        n++;
    }

    public Iterator<Item> iterator() {
        return new LinkedIterator();
    }

    private class LinkedIterator implements Iterator<Item> {
        private Node current;

        public LinkedIterator() {
            current = first;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        LinkedBag<String> bag = new LinkedBag<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            bag.add(item);
        }

        StdOut.println("size of bag = " + bag.size());
        for (String s : bag) {
            StdOut.println(s);
        }
    }
}
