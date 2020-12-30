package ch11.apps;

import algs4.StdOut;
import algs4.StdRandom;
import algs4.Stopwatch;
import ch11.sorts.*;

public class SortCompare {
    public static double time(String alg, Comparable[] a) {
        Stopwatch timer = new Stopwatch();
        switch (alg) {
            case "Selection":
                Selection.sort(a);
                break;
            case "Insertion":
                Insertion.sort(a);
                break;
            case "Shell":
                Shell.sort(a);
                break;
            case "Merge":
                Merge.sort(a);
                break;
            case "MergeBU":
                MergeBU.sort(a);
                break;
            case "Quick":
                Quick.sort(a);
                break;
            case "Quick3way":
                Quick3way.sort(a);
                break;
        }
        return timer.elapsedTime();
    }

    public static double timeRandomInput(String alg, int N, int T) {
        double totle = 0.0;
        Double[] a = new Double[N];
        for (int t = 0; t < T; t++) {
            for (int i = 0; i < N; i++) {
                a[i] = StdRandom.uniform();
            }
            totle += time(alg, a);
        }
        return totle;
    }

    public static void main(String[] args) {
        String alg1 = "Insertion";
        String alg2 = "Selection";
        String alg3 = "Shell";
        String alg4 = "Merge";
        String alg5 = "MergeBU";
        String alg6 = "Quick";
        String alg7 = "Quick3way";

        int N = 5000;
        int T = 100;

        double t1 = timeRandomInput(alg1, N, T);
        double t2 = timeRandomInput(alg2, N, T);
        double t3 = timeRandomInput(alg3, N, T);
        double t4 = timeRandomInput(alg4, N, T);
        double t5 = timeRandomInput(alg5, N, T);
        double t6 = timeRandomInput(alg6, N, T);
        double t7 = timeRandomInput(alg7, N, T);

        StdOut.printf("对%d个长度为%d的随机double数组进行排序：\n", T, N);
        StdOut.printf("%s-耗时：%.2f\n", alg1, t1);
        StdOut.printf("%s-耗时：%.2f\n", alg2, t2);
        StdOut.printf("%s-耗时：%.2f\n", alg3, t3);
        StdOut.printf("%s-耗时：%.2f\n", alg4, t4);
        StdOut.printf("%s-耗时：%.2f\n", alg5, t5);
        StdOut.printf("%s-耗时：%.2f\n", alg6, t6);
        StdOut.printf("%s-耗时：%.2f\n", alg7, t7);
    }
}
