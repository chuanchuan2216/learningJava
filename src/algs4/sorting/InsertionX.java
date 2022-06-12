package algs4.sorting;

import support.Stdlib.StdIn;

import java.util.Comparator;

public class InsertionX extends SortTemplate {
    private InsertionX() {
    }

    public static void sort(Comparable[] a) {
        int n = a.length;

        int exchanges = 0;
        for (int i = n - 1; i > 0; i--) {
            if (less(a[i], a[i - 1])) {
                exch(a, i, i - 1);
                exchanges++;
            }
        }
        if (exchanges == 0) {
            return;
        }

        for (int i = 2; i < n; i++) {
            Comparable v = a[i];
            int j = i;
            while (less(v, a[j - 1])) {
                a[j] = a[j - 1];
                j--;
            }
            a[j] = v;
        }
        assert isSorted(a);
    }

    public static void sort(Object[] a, Comparator comparator) {
        int n = a.length;

        int exchanges = 0;
        for (int i = n - 1; i > 0; i--) {
            if (less(a[i], a[i - 1], comparator)) {
                exch(a, i, i - 1);
                exchanges++;
            }
        }
        if (exchanges == 0) {
            return;
        }
        for (int i = 2; i < n; i++) {
            Object v = a[i];
            int j = i;
            while (less(v, a[j - 1], comparator)) {
                a[j] = a[j - 1];
                j--;
            }
            a[j] = v;
        }
        assert isSorted(a, comparator);
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        InsertionX.sort(a);
        show(a);
    }
}
