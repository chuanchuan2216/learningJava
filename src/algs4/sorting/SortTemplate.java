package algs4.sorting;

import support.Stdlib.StdOut;

public class SortTemplate {
    protected static boolean less(Comparable v,Comparable w){
        return v.compareTo(w)<0;
    }

    protected static void exch(Comparable[] a,int i,int j){
        Comparable t =a[i];
        a[i]=a[j];
        a[j]=t;
    }

    protected static void show(Comparable[] a){
        for (int i = 0;i< a.length;i++){
            StdOut.print(a[i]+" ");
        }
        StdOut.println();
    }

    protected static boolean isSorted(Comparable[] a){
        for (int i =0;i<a.length;i++){
            if(less(a[i],a[i-1])){
                return false;
            }
        }
        return true;
    }
}
