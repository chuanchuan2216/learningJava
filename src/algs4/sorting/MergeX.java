package algs4.sorting;


import support.Stdlib.StdIn;

import java.util.Comparator;

public class MergeX extends SortTemplate {
    private static final int CUTOFF = 7;    // 排序元素少于7个使用插入排序

    private MergeX() {
    }

    public static void sort(Comparable[] a) {
        Comparable[] aux = a.clone();
        sort(aux, a, 0, a.length - 1);
        assert isSorted(a);
    }

    public static void sort(Object[] a, Comparator comparator){
        Object[] aux = a.clone();
        sort(aux,a,0,a.length-1,comparator);
    }

    private static void sort(Comparable[] src, Comparable[] dst, int lo, int hi) {
        if (hi <= lo + CUTOFF) {
            insertionSort(dst, lo, hi);
            return;
        }

        int mid = lo + (hi - lo) / 2;

        sort(dst, src, lo, mid);
        sort(dst, src, mid + 1, hi);

        if (!less(src[mid + 1], src[mid])) {
            System.arraycopy(src, lo, dst, lo, hi - lo + 1);
            return;
        }

        merge(src, dst, lo, mid, hi);
    }

    private static void sort(Object[] src, Object[] dst, int lo, int hi, Comparator comparator) {
        if(hi<=lo+CUTOFF){
            insertionSort(dst,lo,hi,comparator);
            return;
        }
        int mid = lo+(hi-lo)/2;
        sort(dst,src,lo,mid,comparator);
        sort(dst,src,mid+1,hi,comparator);

        if(!less(src[mid],src[mid+1],comparator)){
            System.arraycopy(src,lo,dst,lo,hi-lo+1);
            return;
        }

        merge(src,dst,lo,mid,hi,comparator);
    }

    private static void insertionSort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }
    }

    private  static void insertionSort(Object[] a,int lo,int hi,Comparator comparator){
        for (int i = lo; i <=hi ; i++) {
            for (int j = i; j >lo&&less(a[j],a[j-1],comparator) ; j++) {
                exch(a,j,j-1);
            }
        }
    }

    private static void merge(Comparable[] src, Comparable[] dst, int lo, int mid, int hi) {
        assert isSorted(src, lo, mid);
        assert isSorted(src, mid + 1, hi);

        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                dst[k] = src[j++];
            } else if (j > hi) {
                dst[k] = src[i++];
            } else if (less(src[j], src[i])) {
                dst[k] = src[j++];
            } else {
                dst[k] = src[i++];
            }
        }
        assert isSorted(dst, lo, hi);
    }

    private static void merge(Object[] src,Object[] dst,int lo,int mid,int hi,Comparator comparator){
        assert isSorted(src,lo,mid,comparator);
        assert isSorted(src,mid+1,hi,comparator);

        int i=lo;
        int j=mid+1;
        for (int k = lo; k <= hi ; k++) {
            if(i>mid){
                dst[k]=src[j++];
            } else if (j>hi) {
                dst[k]=src[i++];
            } else if (less(src[j],src[i],comparator)) {
                dst[k]=src[j++];
            }else{
                dst[k]=src[i++];
            }
        }
        assert isSorted(dst,lo,hi,comparator);
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        MergeX.sort(a);
        show(a);
    }
}
