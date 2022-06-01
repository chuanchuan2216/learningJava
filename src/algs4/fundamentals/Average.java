package algs4.fundamentals;

import support.Stdlib.StdIn;
import support.Stdlib.StdOut;

public class Average {
    private Average() {
    }

    public static void main(String[] args) {
        int count = 0;
        double sum = 0.0;

        while (!StdIn.isEmpty()) {
            double value = StdIn.readDouble();
            sum += value;
            count++;
        }

        double average = sum / count;

        StdOut.println("平均数：" + average);
    }
}
