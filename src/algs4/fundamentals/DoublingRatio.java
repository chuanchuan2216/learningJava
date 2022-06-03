package algs4.fundamentals;

import support.Stdlib.StdOut;
import support.Stdlib.StdRandom;
import support.Stdlib.Stopwatch;

public class DoublingRatio {
    private static final int MAXIMUN_INTERGER=1000000;

    private DoublingRatio(){}

    public static double timeTrial(int n){
        int[] a =new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = StdRandom.uniform(-MAXIMUN_INTERGER,MAXIMUN_INTERGER);
        }
        Stopwatch timer = new Stopwatch();
        ThreeSum.count(a);
        //ThreeSumFast.count(a);
        return timer.elapsedTime();
    }

    public static void main(String[] args) {
        double prev = timeTrial(125);
        for(int n = 250;true;n+=n){
            double time = timeTrial(n);
            StdOut.printf("%7d %7.1f %5.1f\n",n,time,time/prev);
            prev = time;
        }
    }
}
