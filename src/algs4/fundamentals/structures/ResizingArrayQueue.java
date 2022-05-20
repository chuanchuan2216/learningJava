package algs4.fundamentals.structures;

import support.Stdlib.StdIn;
import support.Stdlib.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayQueue<Item> implements Iterable<Item> {
    private static final int INIT_CAPACITY = 8;

    private Item[] q;
    private int n;
    private int first;
    private int last;

    /**
     * 初始化一个空的FIFO队列
     */
    public ResizingArrayQueue() {
        q = (Item[]) new Object[INIT_CAPACITY];
        n = 0;
        first = 0;
        last = 0;
    }

    /**
     * 判断FIFO队列是否为空
     *
     * @return {@code true} 队列为空返回true
     * {@code false} 队列不空返回false
     */
    public boolean isEmpty() {
        return n == 0;
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
     * 将队列内元素移动到新数组
     *
     * @param capacity 新数组的元素个数
     */
    private void resize(int capacity) {
        assert capacity >= n;
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = q[(first + i) % q.length];
        }
        q = copy;
        first = 0;
        last = n;
    }

    /**
     * 向队列添加一个元素
     *
     * @param item 需要添加的元素
     */
    public void enqueue(Item item) {
        if (n == q.length) {
            resize(2 * q.length);
        }
        q[last++] = item;
        if (last == q.length) {
            last = 0;
        }
        n++;
    }

    /**
     * 删除并返回队列的第一个元素
     *
     * @return 队列的第一个元素
     */
    public Item dequeue() {
        if ((isEmpty())) {
            throw new NoSuchElementException("空队列,操作下溢");
        }
        Item item = q[first];
        q[first] = null;
        n--;
        first++;
        if (first == q.length) {
            first = 0;
        }
        if (n > 0 && n == q.length / 4) {
            resize(q.length / 2);
        }
        return item;
    }

    /**
     * 返回队列的第一个元素
     *
     * @return 队列的第一个元素
     */
    public Item peek() {
        if ((isEmpty())) {
            throw new NoSuchElementException("空队列,操作下溢");
        }
        return q[first];
    }

    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int i = 0;

        @Override
        public boolean hasNext() {
            return i < n;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = q[(i + first) % q.length];
            i++;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        ResizingArrayQueue<String> queue = new ResizingArrayQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) queue.enqueue(item);
            else if (!queue.isEmpty()) StdOut.print(queue.dequeue() + " ");
        }
        StdOut.println("(" + queue.size() + " left on queue)");
    }
}
