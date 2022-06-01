package algs4.fundamentals;

import support.Stdlib.In;
import support.Stdlib.StdIn;
import support.Stdlib.StdOut;

import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

public class BinarySearch {
    /**
     * rank方法的作用
     * @param key 参数说明
     * @param a 参数说明
     * @return 返回值的说明
     */
    public static int rank(int key, int[] a) {
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

    public static void main(String[] args) {
        int[] whitelist = In.readInts(args[0]);

        Arrays.sort(whitelist);

        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            if (rank(key, whitelist) == -1) {
                StdOut.println(key);
            }
        }
    }
}
