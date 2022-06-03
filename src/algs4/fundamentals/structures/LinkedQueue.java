package algs4.fundamentals.structures;

import support.Stdlib.StdIn;
import support.Stdlib.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<Item> implements Iterable<Item> {
    private int n;
    private Node first;
    private Node last;

    private class Node {
        private Item item;
        private Node next;
    }

    /**
     * 初始化一个空的FIFO队列
     */
    public LinkedQueue() {
        first = null;
        last = null;
        n = 0;
        assert check();
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
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldlast.next = last;
        }
        n++;
        assert check();
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
        assert check();
        return item;
    }

    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        for (Item item : this) {
            s.append(item + " ");
        }
        return s.toString();
    }


    private boolean check() {
        if (n < 0) {
            return false;
        } else if (n == 0) {
            if (first != null) {
                return false;
            }
            if (last != null) {
                return false;
            }
        } else if (n == 1) {
            if (first == null || last == null) {
                return false;
            }
            if (first != last) {
                return false;
            }
            if (first.next != null) {
                return false;
            }
        } else {
            if (first == null || last == null) {
                return false;
            }
            if (first == last) {
                return false;
            }
            if (first.next == null) {
                return false;
            }
            if (last.next != null) {
                return false;
            }
        }
        int numberOfNodes = 0;
        for (Node x = first; x != null && numberOfNodes <= n; x = x.next) {
            numberOfNodes++;
        }
        if ((numberOfNodes != n)) {
            return false;
        }

        Node lastNode = first;
        while (lastNode.next != null) {
            lastNode = lastNode.next;
        }
        if (last != lastNode) {
            return false;
        }

        return true;
    }

    public Iterator<Item> iterator() {
        return new LinkedIterator();
    }

    private class LinkedIterator implements Iterator<Item> {
        private Node current = first;

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
        LinkedQueue<String> queue = new LinkedQueue<String>();
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
