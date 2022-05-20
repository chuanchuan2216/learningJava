package algs4.fundamentals.structures;

import support.Stdlib.StdIn;
import support.Stdlib.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<Item> implements Iterable<Item> {
    private Node<Item> first;   //队首
    private Node<Item> last;    //队尾
    private int n;

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    /**
     * 初始化一个空的FIFO队列
     */
    public Queue() {
        first = null;
        last = null;
        n = 0;
    }

    /**
     * 判断FIFO队列是否为空
     *
     * @return {@code true} 队列为空返回true
     * {@code false} 队列不空返回false
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * 返回队列中的元素个数
     *
     * @return 队列中的元素个数
     */
    public int size() {
        return n;
    }

    /**
     * 返回队列的第一个元素
     *
     * @return 队列的第一个元素
     */
    public Item peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("队列为空，操作下溢");
        }
        return first.item;
    }

    /**
     * 向队列添加一个元素
     *
     * @param item 需要添加的元素
     */
    public void enqueue(Item item) {
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldlast.next = last;
        }
        n++;
    }

    /**
     * 删除并返回队列的第一个元素
     *
     * @return 队列的第一个元素
     */
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("队列为空，操作下溢");
        }
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) {
            last = null;
        }
        return item;
    }

    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        for (Item item : this) {
            s.append(item);
            s.append(" ");
        }
        return s.toString();
    }

    public Iterator<Item> iterator() {
        return new LinkedIterator(first);
    }


    private class LinkedIterator implements Iterator<Item> {
        private Node<Item> current;

        public LinkedIterator(Node<Item> first) {
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
        Queue<String> queue = new Queue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                queue.enqueue(item);
            else if (!queue.isEmpty())
                StdOut.print(queue.dequeue() + " ");
        }
        StdOut.println("(" + queue.size() + " left on queue)");
    }
}
