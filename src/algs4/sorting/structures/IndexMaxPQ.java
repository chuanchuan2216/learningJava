package algs4.sorting.structures;

import support.Stdlib.StdOut;
import support.Stdlib.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IndexMaxPQ<Key extends Comparable<Key>> implements Iterable<Integer> {
    private int maxN;   //优先队列的最大长度
    private int n;      //当前优先队列的长度
    private int[] pq;   //二叉树使用的索引
    private int[] qp;   //翻转二叉树使用的索引
    private Key[] keys; //优先队列

    /**
     * 创建一个最大容量为maxN的优先队列，索引范围0到maxN
     *
     * @param maxN 优先队列的最大容量
     * @throws IllegalArgumentException 如果{@code maxN <0}
     */
    public IndexMaxPQ(int maxN) {
        if (maxN < 0) {
            throw new IllegalArgumentException();
        }
        this.maxN = maxN;
        n = 0;
        keys = (Key[]) new Comparable[maxN + 1];
        pq = new int[maxN + 1];
        qp = new int[maxN + 1];
        for (int i = 0; i <= maxN; i++) {
            qp[i] = -1;
        }
    }

    /**
     * 返回优先队列是否为空
     *
     * @return {@code true} 优先队列为空
     * {@code false} 优先队列不为空
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * 是否存在索引为i的元素
     *
     * @param i 需要判断是否存在的元素的索引
     * @return {@code true} 索引为i的元素存在
     * {@code false} 索引为i的元素不存在
     */
    public boolean contains(int i) {
        validateIndex(i);
        return qp[i] != -1;
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
     * 插入一个元素，将它和索引i相关联
     *
     * @param i   索引i，与插入的元素key相关联
     * @param key 插入的元素key，与索引i相关联
     */
    public void insert(int i, Key key) {
        validateIndex(i);
        if (contains(i)) {
            throw new IllegalArgumentException("索引值已存在于优先队列中");
        }
        n++;
        qp[i] = n;
        pq[n] = i;
        keys[i] = key;
        swim(n);
    }

    /**
     * 返回最大元素的索引
     *
     * @return 最大元素的索引
     */
    public int maxIndex() {
        if (n == 0) {
            throw new NoSuchElementException("优先队列为空，操作下溢。");
        }
        return pq[1];
    }

    /**
     * 返回最大元素
     *
     * @return
     */
    public Key maxKey() {
        if (n == 0) {
            throw new NoSuchElementException("优先队列为空，操作下溢。");
        }
        return keys[pq[1]];
    }

    /**
     * 删除最大元素并返回它的索引
     *
     * @return 最大元素的索引
     */
    public int delMax() {
        if (n == 0) {
            throw new NoSuchElementException("优先队列为空，操作下溢。");
        }
        int max = pq[1];
        exch(1, n--);
        sink(1);

        assert pq[n + 1] == max;
        qp[max] = -1;
        keys[max] = null;
        pq[n + 1] = -1;
        return max;
    }

    /**
     * 返回与索引相关联的元素
     *
     * @param i 与元素关联的索引
     * @return 与索引关联的元素
     */
    public Key keyOf(int i) {
        validateIndex(i);
        if (!contains(i)) {
            throw new NoSuchElementException("索引值不存在于优先队列中");
        } else {
            return keys[i];
        }
    }

    /**
     * 修改与索引关联的元素
     *
     * @param i   需要修改的元素的索引
     * @param key 需要修改的元素的数据
     */
    public void changeKey(int i, Key key) {
        validateIndex(i);
        if (!contains(i)) {
            throw new NoSuchElementException("索引值不存在于优先队列中");
        }
        keys[i] = key;
        swim(qp[i]);
        sink(qp[i]);
    }

    /**
     * 修改与索引关联的元素
     *
     * @param i   需要修改的元素的索引
     * @param key 需要修改的元素的数据
     */
    @Deprecated
    public void change(int i, Key key) {
        validateIndex(i);
        changeKey(i, key);
    }

    /**
     *
     * @param i
     * @param key
     */
    public void increaseKey(int i, Key key) {
        validateIndex(i);
        if (!contains(i)) {
            throw new NoSuchElementException("索引值不存在于优先队列中");
        }
        if (keys[i].compareTo(key) == 0) {
            throw new IllegalArgumentException("使用了与优先级队列中的键相等的键作为参数调用increaseKey()。");
        }
        if (keys[i].compareTo(key) > 0) {
            throw new IllegalArgumentException("使用了比优先级队列中的键较大的键作为参数调用increaseKey()。");
        }
        keys[i] = key;
        swim(qp[i]);
    }

    /**
     *
     * @param i
     * @param key
     */
    public void decreaseKey(int i, Key key) {
        validateIndex(i);
        if (!contains(i)) {
            throw new NoSuchElementException("索引值不存在于优先队列中");
        }
        if (keys[i].compareTo(key) == 0) {
            throw new IllegalArgumentException("使用了与优先级队列中的键相等的键作为参数调用decreaseKey()。");
        }
        if (keys[i].compareTo(key) < 0) {
            throw new IllegalArgumentException("使用了比优先级队列中的键较大的键作为参数调用decreaseKey()。");
        }
        keys[i] = key;
        sink(qp[i]);
    }

    /**
     * 删除与索引i相关联的元素
     *
     * @param i 需要删除的索引
     */
    public void delete(int i) {
        validateIndex(i);
        if (!contains(i)) {
            throw new NoSuchElementException("索引值不存在于优先队列中");
        }
        int index = qp[i];
        exch(index, n--);
        swim(index);
        sink(index);
        keys[i] = null;
        qp[i] = -1;
    }

    /**
     * 验证索引i是否有效
     *
     * @param i 需要验证的索引
     */
    private void validateIndex(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("无效的索引：" + i);
        }
        if (i >= maxN) {
            throw new IllegalArgumentException("越界的索引：" + i);
        }
    }

    /**
     * 判断索引i相关联的元素是否比索引j相关联的元素小
     *
     * @param i 索引值i
     * @param j 索引值j
     * @return {@code ture} i<j
     * {@code false} i>j
     */
    private boolean less(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
    }

    /**
     * 交换索引i、j的位置
     *
     * @param i 索引值i
     * @param j 索引值j
     */
    private void exch(int i, int j) {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    /**
     * 由下至上的堆有序化（上浮）
     *
     * @param k 上浮元素的索引值
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
     * @param k 下沉元素的索引值
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

    public Iterator<Integer> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Integer> {
        private IndexMaxPQ<Key> copy;

        public HeapIterator() {
            copy = new IndexMaxPQ<>(pq.length - 1);
            for (int i = 1; i <= n; i++) {
                copy.insert(pq[i], keys[pq[i]]);
            }
        }

        @Override
        public boolean hasNext() {
            return !copy.isEmpty();
        }

        @Override
        public Integer next() {
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
        String[] strings = {"it", "was", "the", "best", "of", "times", "it", "was", "the", "worst"};

        IndexMaxPQ<String> pq = new IndexMaxPQ<String>(strings.length);
        for (int i = 0; i < strings.length; i++) {
            pq.insert(i, strings[i]);
        }

        for (int i : pq) {
            StdOut.println(i + " " + strings[i]);
        }

        StdOut.println();

        for (int i = 0; i < strings.length; i++) {
            if (StdRandom.uniform() < 0.5)
                pq.increaseKey(i, strings[i] + strings[i]);
            else
                pq.decreaseKey(i, strings[i].substring(0, 1));
        }

        while (!pq.isEmpty()) {
            String key = pq.maxKey();
            int i = pq.delMax();
            StdOut.println(i + " " + key);
        }
        StdOut.println();

        for (int i = 0; i < strings.length; i++) {
            pq.insert(i, strings[i]);
        }

        int[] perm = new int[strings.length];
        for (int i = 0; i < strings.length; i++)
            perm[i] = i;
        StdRandom.shuffle(perm);
        for (int i = 0; i < perm.length; i++) {
            String key = pq.keyOf(perm[i]);
            pq.delete(perm[i]);
            StdOut.println(perm[i] + " " + key);
        }
    }
}
