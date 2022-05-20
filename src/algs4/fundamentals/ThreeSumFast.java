package algs4.fundamentals;

import support.Stdlib.In;
import support.Stdlib.StdOut;

import java.util.Arrays;

public class ThreeSumFast {
    //避免实例化
    private ThreeSumFast() {
    }

    /**
     * 判断数组里是否有重复的整数
     * @param a 包含整型数据的数组
     * @return 有重复的数据返回true，否则返回false
     */
    private static boolean containDuplicates(int[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i] == a[i - 1]) {
                return true;
            }
        }
        return false;
    }

    /**
     * 打印出所有和为0的数字组合
     *
     * @param a 包含整型数据的数组
     */
    public static void printAll(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        if (containDuplicates(a)) {
            throw new IllegalArgumentException("发现重复的数据");
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int k = Arrays.binarySearch(a, -(a[i] + a[j]));
                if (k > j) {
                    StdOut.println(a[i] + " " + a[j] + " " + a[k]);
                }
            }
        }
    }

    /**
     * 返回所有和为0的数字组合的个数
     *
     * @param a 包含整型数据的数组
     * @return 所有和为0的数字组合的个数
     */
    public static int count(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        if (containDuplicates(a)) {
            throw new IllegalArgumentException("发现重复的数据");
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int k = Arrays.binarySearch(a, -(a[i] + a[j]));
                if (k > j) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int[] a = in.readAllInts();

        Stopwatch timer = new Stopwatch();
        int count = count(a);
        StdOut.println("elapsed time = " + timer.elapsedTime());
        StdOut.println(count);
    }
}
