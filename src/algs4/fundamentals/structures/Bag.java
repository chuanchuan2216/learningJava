package algs4.fundamentals.structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Bag<Item> implements Iterable<Item> {

    private Node<Item> first;
    private int n;

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    /**
     * 初始化一个空的背包
     */
    public Bag() {
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
        Node<Item> oldFirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldFirst;
        n++;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedIterator(first);
    }

    private class LinkedIterator implements Iterator<Item> {
        private Node<Item> current;

        public LinkedIterator(Node<Item> first) {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
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
}
