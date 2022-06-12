package algs4.sorting;

import support.Stdlib.StdIn;

public class MergeBU extends SortTemplate {
    private MergeBU(){}

    public static void sort(Comparable[] a) {
        int n = a.length;
        Comparable[] aux = new Comparable[n];
        for (int len = 1; len < n; len += len) {
            for (int lo = 0; lo < n - len; lo += len + len) {
                merge(a,aux ,lo, lo + len - 1, Math.min(lo + len + len - 1, n - 1));
            }
        }
    }

    private static void merge(Comparable[] a,Comparable[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        MergeBU.sort(a);
        show(a);
    }
}
