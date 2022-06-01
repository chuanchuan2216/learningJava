package algs4.fundamentals;

import support.Stdlib.StdIn;
import support.Stdlib.StdOut;

public class Accumulator {
    private int n = 0;
    private double sum = 0.0;
    private double mu = 0.0;

    public Accumulator() {
    }

    /**
     * 添加指定的样本数值到累加器里
     *
     * @param x 指定的样本数值
     */
    public void addDataValue(double x) {
        n++;
        double delta = x - mu;
        mu += delta / n;
        sum += (double) (n - 1) / n * delta * delta;
    }

    /**
     * 返回累加器当前的平均值
     *
     * @return 累加器当前的平均值
     */
    public double mean() {
        return mu;
    }

    /**
     * 返回累加器当前的样本方差
     *
     * @return
     */
    public double var() {
        if (n <= 1) {
            return Double.NaN;
        }
        return sum / (n - 1);
    }

    /**
     * 返回累加器当前的样本标准偏差
     *
     * @return
     */
    public double stddev() {
        return Math.sqrt(this.var());
    }

    /**
     * 返回累加器当前的样本个数
     *
     * @return
     */
    public int count() {
        return n;
    }

    @Override
    public String toString() {
        return "n = " + n + ",mean = " + mean() + ",stddev = " + stddev();
    }

    public static void main(String[] args) {
        Accumulator stats = new Accumulator();

        while (!StdIn.isEmpty()) {
            double x = StdIn.readDouble();
            stats.addDataValue(x);
        }

        StdOut.printf("n      = %d\n", stats.count());
        StdOut.printf("mean   = %.5f\n", stats.mean());
        StdOut.printf("stddev = %.5f\n", stats.stddev());
        StdOut.printf("var    = %.5f\n", stats.var());
        StdOut.println(stats);
    }
}
