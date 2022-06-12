package algs4.sorting;

import support.Stdlib.StdOut;
import support.Stdlib.StdRandom;
import support.Stdlib.Stopwatch;

import java.util.Arrays;

public class SortCompare {
    public static double time(String alg, Comparable[] a) {
        Stopwatch timer = new Stopwatch();
        switch (alg) {
            case "Insertion":
                Insertion.sort(a);
                break;

            case "InsertionX":
                InsertionX.sort(a);
                break;

            case "BinaryInsertion":
                BinaryInsertion.sort(a);
                break;

            case "Selection":
                Selection.sort(a);
                break;

            case "Bubble":
                Bubble.sort(a);
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

            case "MergeX":
                MergeX.sort(a);
                break;

            case "Quick":
                Quick.sort(a);
                break;

            case "Quick3way":
                Quick3way.sort(a);
                break;

            case "QuickX":
                QuickX.sort(a);
                break;

            case "QuickBentleyMcIlroy":
                QuickBentleyMcIlroy.sort(a);
                break;

            case "Heap":
                Heap.sort(a);
                break;

            case "System":
                Arrays.sort(a);
                break;

            default:
                throw new IllegalArgumentException("无效的算法名称" + alg);
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
        String[] algs = {"Insertion", "InsertionX", "BinaryInsertion",
                "Selection", "Bubble", "Shell",
                "Merge", "MergeBU", "MergeX",
                "Quick", "Quick3way", "QuickX", "QuickBentleyMcIlroy",
                "Heap", "System"};
        int n = algs.length;
        double[] timers = new double[n];

        int N = 1000;
        int T = 1000;

        for (int i = 0; i < n; i++) {
            timers[i] = timeRandomInput(algs[i], N, T);
        }

        StdOut.printf("对%d个长度为%d的随机double数组进行排序：\n", N, T);
        for (int i = 0; i < n; i++) {
            StdOut.printf("%s-耗时：%.2f\n", algs[i], timers[i]);
        }
    }
}
