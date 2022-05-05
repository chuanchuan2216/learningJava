package algs4.structures;

import support.Stdlib.StdIn;
import support.Stdlib.StdOut;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MaxPQ<Key> implements Iterable<Key> {
    private Key[] pq;
    private int n;
    private Comparator<Key> comparator;

    /**
     * 创建一个初始容量为initCapacity的优先队列
     *
     * @param initCapacity 初始容量
     */
    public MaxPQ(int initCapacity) {
        pq = (Key[]) new Object[initCapacity + 1];
        n = 0;
    }

    /**
     * 创建一个优先队列
     */
    public MaxPQ() {
        this(1);
    }

    /**
     * 创建一个初始容量为initCapacity，比较器为comparator的优先队列
     *
     * @param initCapacity 初始容量
     * @param comparator   比较器
     */
    public MaxPQ(int initCapacity, Comparator<Key> comparator) {
        pq = (Key[]) new Object[initCapacity + 1];
        n = 0;
        this.comparator = comparator;
    }

    /**
     * 创建一个比较器为comparator的优先队列
     *
     * @param comparator 比较器
     */
    public MaxPQ(Comparator<Key> comparator) {
        this(1, comparator);
    }

    /**
     * 用keys数组中的元素创建一个优先队列
     *
     * @param keys 数组
     */
    public MaxPQ(Key[] keys) {
        n = keys.length;
        pq = (Key[]) new Object[keys.length + 1];
        for (int i = 0; i < n; i++) {
            pq[i + 1] = keys[i];
        }
        for (int k = n / 2; k >= 1; k--) {
            sink(k);
        }
        assert isMaxHeap();
    }

    /**
     * 返回优先队列是否为空
     *
     * @return {@code true} 优先队列为空;
     * {@code false} 优先队列不为空
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * 返回优先队列中的元素个数
     *
     * @return 优先队列中的元素个数
     */
    public int size() {
        return n;
    }

    /**
     * 返回优先队列中的最大值
     *
     * @return 优先队列中的最大值
     * @throws NoSuchElementException 如果优先队列为空
     */
    public Key max() {
        if (isEmpty()) {
            throw new NoSuchElementException("优先队列为空，操作下溢。");
        }
        return pq[1];
    }

    /**
     * 根据参数扩展优先队列长度
     *
     * @param capacity 指定新优先队列的元素个数
     */
    private void resize(int capacity) {
        assert capacity > n;
        Key[] temp = (Key[]) new Object[capacity];
        for (int i = 1; i <= n; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    /**
     * 向优先队列中插入一个元素x
     *
     * @param x 向优先队列中插入的元素
     */
    public void insert(Key x) {
        // 如果优先队列已满，则扩展优先队列长度
        if (n == pq.length - 1) {
            resize(2 * pq.length);
        }
        // 插入元素x，并上浮索引n
        pq[++n] = x;
        swim(n);
        assert isMaxHeap();
    }

    /**
     * 删除并返回优先队列的最大元素
     *
     * @return 优先队列的最大元素
     */
    public Key delMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("优先队列为空，操作下溢。");
        }
        Key max = pq[1];
        exch(1, n--);
        sink(1);
        pq[n + 1] = null;
        if ((n > 0) && (n == (pq.length - 1) / 4)) {
            resize(pq.length / 2);
        }
        assert isMaxHeap();
        return max;
    }

    /**
     * 由下至上的堆有序化（上浮）
     *
     * @param k
     */
    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    /**
     * 由上至下的堆有序化（下沉）
     *
     * @param k
     */
    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(j, j + 1)) {
                j++;
            }
            if (!less(k, j)) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }

    /**
     * 堆实现的比较方法
     *
     * @param i pq数组中参与比较的元素的索引
     * @param j pq数组中参与比较的元素的索引
     * @return pq数组中参与比较的两个元素中的较小者
     */
    private boolean less(int i, int j) {
        if (comparator == null) {
            return ((Comparable<Key>) pq[i]).compareTo(pq[j]) < 0;
        } else {
            return comparator.compare(pq[i], pq[j]) < 0;
        }
    }

    /**
     * 堆实现的交换方法
     *
     * @param i pq数组中参与交换的元素的索引
     * @param j pq数组中参与交换的元素的索引
     */
    private void exch(int i, int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    /**
     * 判断pq[1..n]是否为最大堆
     *
     * @return {@code true} 是最大堆
     * {@code false} 不是最大堆
     */
    private boolean isMaxHeap() {
        for (int i = 1; i <= n; i++) {
            if (pq[i] == null) {
                return false;
            }
        }
        for (int i = n + 1; i < pq.length; i++) {
            if (pq[i] != null) {
                return false;
            }
        }
        if (pq[0] != null) {
            return false;
        }
        return isMaxHeapOrdered(1);
    }

    /**
     * 判断根节点为k的子树是否为最大堆（递归）
     *
     * @param k 子树的根节点
     * @return {@code true} 是最大堆
     * {@code false} 不是最大堆
     */
    private boolean isMaxHeapOrdered(int k) {
        if (k > n) {
            return true;
        }
        int left = 2 * k;
        int right = 2 * k + 1;
        if (left <= n && less(k, left)) {
            return false;
        }
        if (right <= n && less(k, right)) {
            return false;
        }
        return isMaxHeapOrdered(left) && isMaxHeapOrdered(right);
    }

    @Override
    public Iterator<Key> iterator() {
        return new HeapIterator();
    }


    private class HeapIterator implements Iterator<Key> {
        private MaxPQ<Key> copy;

        public HeapIterator() {
            if (comparator == null) {
                copy = new MaxPQ<>(size());
            } else {
                copy = new MaxPQ<>(size(), comparator);
            }
            for (int i = 1; i <= n; i++) {
                copy.insert(pq[i]);
            }
        }

        @Override
        public boolean hasNext() {
            return !copy.isEmpty();
        }

        @Override
        public Key next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return copy.delMax();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        MaxPQ<String> pq = new MaxPQ<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                pq.insert(item);
            } else if (!pq.isEmpty()) {
                StdOut.print(pq.delMax() + " ");
            }
            StdOut.println("(" + pq.size() + " left on pq)");
        }
    }
}
