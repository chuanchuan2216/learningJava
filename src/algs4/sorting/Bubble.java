package algs4.sorting;

import support.Stdlib.StdIn;

public class Bubble extends SortTemplate{
    private Bubble(){}

    public static void sort(Comparable[] a){
        int n=a.length;
        for (int i = 0; i < n; i++) {
            int exchanges=0;
            for (int j = n-1; j >i ; j--) {
                if(less(a[j],a[j-1])){
                    exch(a,j,j-1);
                    exchanges++;
                }
            }
            if (exchanges ==0){
                break;
            }
        }
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        Bubble.sort(a);
        show(a);
    }
}
