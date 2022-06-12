package algs4.sorting;

import support.Stdlib.StdIn;
import support.Stdlib.StdOut;
import support.Stdlib.StdRandom;

public class Quick extends SortTemplate {
    private Quick() {
    }

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);   // 消除对输入的依赖
        sort(a, 0, a.length - 1);
        assert isSorted(a);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (less(a[++i], v)) {
                if (i == hi) {
                    break;
                }
            }
            while (less(v, a[--j])) {
                if (j == lo) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    public static Comparable select(Comparable[] a,int k){
        if(k<0||k>=a.length){
            throw new IllegalArgumentException("索引值："+k+"不在[0 - "+a.length+"]之间");
        }
        StdRandom.shuffle(a);
        int lo =0;
        int hi = a.length-1;
        while (hi>lo){
            int i = partition(a,lo,hi);
            if(i>k){
                hi = i-1;
            } else if (i < k) {
                lo = i+1;
            }else {
                return a[i];
            }
        }
        return a[lo];
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        Quick.sort(a);
        show(a);
        assert isSorted(a);

        StdRandom.shuffle(a);

        StdOut.println();
        for (int i = 0; i < a.length; i++) {
            String ith = (String) Quick.select(a,i);
            StdOut.println(ith);
        }
    }
}
