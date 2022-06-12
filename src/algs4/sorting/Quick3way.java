package algs4.sorting;

import support.Stdlib.StdIn;
import support.Stdlib.StdRandom;

public class Quick3way extends SortTemplate {
    private Quick3way(){}

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int lt = lo;
        int gt = hi;

        Comparable v = a[lo];
        int i = lo + 1;

        while (i <= gt) {
            int comp = a[i].compareTo(v);
            if (comp < 0) {
                exch(a, lt++, i++);
            } else if (comp > 0) {
                exch(a, i, gt--);
            } else {
                i++;
            }
        }
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);

        assert isSorted(a,lo,hi);
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        Quick3way.sort(a);
        show(a);
    }
}
