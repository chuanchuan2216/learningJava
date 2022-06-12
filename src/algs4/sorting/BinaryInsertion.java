package algs4.sorting;

import support.Stdlib.StdIn;

import java.util.Comparator;

public class BinaryInsertion extends SortTemplate {
    private BinaryInsertion() {
    }

    public static void sort(Comparable[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            Comparable v = a[i];
            int lo = 0, hi = i;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (less(v, a[mid])) {
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            }
            for (int j = i; j > lo; j--) {
                a[j] = a[j - 1];
            }
            a[lo] = v;
        }
        assert isSorted(a);
    }

    public static void sort(Object[] a, Comparator comparator) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            Object v = a[i];
            int lo = 0, hi = i;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (less(v, a[mid], comparator)) {
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            }
            for (int j = i; j > lo; j--) {
                a[j] = a[j - 1];
            }
            a[lo] = v;
        }
        assert isSorted(a, comparator);
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        BinaryInsertion.sort(a);
        show(a);
    }

}
