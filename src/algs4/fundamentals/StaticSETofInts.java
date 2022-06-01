package algs4.fundamentals;

import java.util.Arrays;

public class StaticSETofInts {
    private int[] a;

    /**
     * 根据整型数组实例化整型集合
     * @param keys 整型数组
     */
    public StaticSETofInts(int[] keys) {

        a = new int[keys.length];
        for (int i = 0; i < keys.length; i++) {
            a[i] = keys[i];
        }

        Arrays.sort(a);

        for (int i = 1; i < a.length; i++) {
            if (a[i] == a[i - 1]) {
                throw new IllegalArgumentException("数组中有重复的数据");
            }
        }
    }

    /**
     * 在集合中查找数据
     * @param key 要查找的数据
     * @return 存在返回true，否则返回false
     */
    public boolean contains(int key) {
        return rank(key) != -1;
    }

    /**
     * 返回数据对应的索引，没有该数据则返回-1
     * @param key 要查找的数据
     * @return 数据对应的索引，没有该数据则返回-1
     */
    private int rank(int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) {
                hi = mid - 1;
            } else if (key > a[mid]) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
