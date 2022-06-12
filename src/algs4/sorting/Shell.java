package algs4.sorting;

import support.Stdlib.StdIn;

import java.util.Comparator;

public class Shell extends SortTemplate {
    private Shell() {
    }

    public static void sort(Comparable[] a) {
        int n = a.length;
        // 3x+1的增量序列：1, 4, 13, 40, 121, 364, 1093, ...
        int h = 1;
        while (h < n / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            // 使数组a变为h有序数组
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
            assert isHsorted(a, h);
            h = h / 3;
        }
    }

    public static void sort(Object[] a, Comparator comparator) {
        int n = a.length;
        // 3x+1的增量序列：1, 4, 13, 40, 121, 364, 1093, ...
        int h = 1;
        while (h < n / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            // 使数组a变为h有序数组
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h], comparator); j -= h) {
                    exch(a, j, j - h);
                }
            }
            assert isHsorted(a, comparator, h);
            h = h / 3;
        }
    }

    private static boolean isHsorted(Comparable[] a, int h) {
        for (int i = h; i < a.length; i++) {
            if (less(a[i], a[i - h])) {
                return false;
            }
        }
        return true;
    }

    private static boolean isHsorted(Object[] a, Comparator comparator, int h) {
        for (int i = h; i < a.length; i++) {
            if (less(a[i], a[i - h], comparator)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        Shell.sort(a);
        show(a);
    }
}
