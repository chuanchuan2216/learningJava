package algs4.fundamentals;

import support.Stdlib.In;
import support.Stdlib.StdOut;
import support.Stdlib.Stopwatch;

public class ThreeSum {
    //避免实例化
    private ThreeSum() {
    }

    /**
     * 打印出所有和为0的数字组合
     *
     * @param a 包含整型数据的数组
     */
    public static void printAll(int[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (a[i] + a[j] + a[j] == 0) {
                        StdOut.println(a[i] + " " + a[j] + " " + a[k]);
                    }
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
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (a[i] + a[j] + a[k] == 0) {
                        count++;
                    }
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
        StdOut.println("消耗时间：" + timer.elapsedTime());
        StdOut.println(count);
    }
}
