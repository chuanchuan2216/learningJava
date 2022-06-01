package algs4.fundamentals;

import support.Stdlib.StdIn;
import support.Stdlib.StdOut;

public class Knuth {
    private Knuth() {
    }

    public static void shuffle(Object[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int r = (int) (Math.random() * (i + 1));
            Object swap = a[r];
            a[r] = a[i];
            a[i] = swap;
        }
    }

    public static void shuffleAlternate(Object[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int r = i + (int) (Math.random() * (n - 1));
            Object swap = a[r];
            a[r] = a[i];
            a[i] = swap;
        }
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();

        Knuth.shuffle(a);

        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }
}
