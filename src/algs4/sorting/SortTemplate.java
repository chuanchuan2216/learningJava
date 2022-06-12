package algs4.sorting;

import support.Stdlib.StdOut;

import java.util.Comparator;

public abstract class SortTemplate {
    /**
     * 判断v是否小于w
     *
     * @param v 实现了Comparable接口的v
     * @param w 实现了Comparable接口的w
     * @return v<w返回true ， 否则返回false
     */
    protected static boolean less(Comparable v, Comparable w) {
        if (v == w) {
            return false;
        }
        return v.compareTo(w) < 0;
    }

    /**
     * 判断v是否小于w
     *
     * @param v          需要进行比较的v
     * @param w          需要进行比较的w
     * @param comparator 需要使用的比较器
     * @return v<w返回true ， 否则返回false
     */
    protected static boolean less(Object v, Object w, Comparator comparator) {
        if (v == w) {
            return false;
        }
        return comparator.compare(v, w) < 0;
    }

    /**
     * 判断v是否等于w
     *
     * @param v 需要进行比较的v
     * @param w 需要进行比较的w
     * @return v=w返回true ， 否则返回false
     */
    protected static boolean eq(Comparable v, Comparable w) {
        if (v == w) {
            return true;
        }
        return v.compareTo(w) == 0;
    }

    /**
     * 判断v是否等于w
     *
     * @param v          需要进行比较的v
     * @param w          需要进行比较的w
     * @param comparator 需要使用的比较器
     * @return v=w返回true ， 否则返回false
     */
    protected static boolean eq(Object v, Object w, Comparator comparator) {
        if (v == w) {
            return true;
        }
        return comparator.compare(v, w) == 0;
    }

    /**
     * 交换数组a中索引为i和j的元素的位置
     *
     * @param a 数组a
     * @param i 索引i
     * @param j 索引j
     */
    protected static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    /**
     * 交换数组a中索引为i和j的元素的位置
     *
     * @param a 数组a
     * @param i 索引i
     * @param j 索引j
     */
    protected static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    /**
     * 判断数组a中的元素是否已经有序（默认升序）
     *
     * @param a 数组a
     * @return 有序返回true，否则返回false
     */
    protected static boolean isSorted(Comparable[] a) {
        return SortTemplate.isSorted(a, 0, a.length);
    }

    /**
     * 判断数组a中从索引lo到索引hi的元素是否已经有序（默认升序）
     *
     * @param a  数组a
     * @param lo 索引较小值lo
     * @param hi 索引较大值hi
     * @return 有序返回true，否则返回false
     */
    protected static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo; i < hi; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 断数组a中的元素是否已经有序（默认升序）
     *
     * @param a          数组a
     * @param comparator 比较器
     * @return 有序返回true，否则返回false
     */
    protected static boolean isSorted(Object[] a, Comparator comparator) {
        return isSorted(a, 0, a.length, comparator);
    }

    /**
     * 判断数组a中从索引lo到索引hi的元素是否已经有序（默认升序）
     *
     * @param a          数组a
     * @param lo         索引较小值lo
     * @param hi         索引较大值hi
     * @param comparator 比较器
     * @return 有序返回true，否则返回false
     */
    protected static boolean isSorted(Object[] a, int lo, int hi, Comparator comparator) {
        for (int i = lo; i < hi; i++) {
            if (less(a[i], a[i - 1], comparator)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 输出数组a中的元素
     *
     * @param a 数组a
     */
    protected static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
    }

    protected static void show(Object[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i] + " ");
        }
    }
}
