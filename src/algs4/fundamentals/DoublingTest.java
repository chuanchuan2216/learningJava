package algs4.fundamentals;

import support.Stdlib.StdOut;
import support.Stdlib.StdRandom;
import support.Stdlib.Stopwatch;

public class DoublingTest {
    private static final int MAXIMUM_INTERGER = 1000000;

    private DoublingTest(){}

    public static double timeTrial(int n){
        int[] a =new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = StdRandom.uniform(-MAXIMUM_INTERGER,MAXIMUM_INTERGER);
        }
        Stopwatch timer = new Stopwatch();
        ThreeSum.count(a);
        return timer.elapsedTime();
    }

    public static void main(String[] args) {
        for (int n = 250; true ; n+=n) {
            double time = timeTrial(n);
            StdOut.printf("%7d %7.1f\n",n,time);
        }
    }
}
