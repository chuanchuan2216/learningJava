package algs4.fundamentals;

import support.Stdlib.StdDraw;
import support.Stdlib.StdOut;
import support.Stdlib.StdRandom;

public class Interval2D {
    private final Interval1D x;
    private final Interval1D y;

    /**
     * 初始化一个二维间隔
     *
     * @param x x轴上的间隔
     * @param y y轴上的间隔
     */
    public Interval2D(Interval1D x, Interval1D y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 判断两个平面上的二维间隔是否有交叉
     *
     * @param that 另一个二维间隔
     * @return 有交叉返回true，否则返回false
     */
    public boolean intersects(Interval2D that) {
        if (!this.x.intersects(that.x)) {
            return false;
        }
        if (!this.y.intersects(that.y)) {
            return false;
        }
        return true;
    }

    /**
     * 判断二维间隔是否包含另一个二维间隔
     *
     * @param p 另一个二维间隔
     * @return 当前的二维间隔包含另一个返回true，否则返回false
     */
    public boolean contains(Point2D p) {
        return x.contains(p.x()) && y.contains(p.y());
    }

    /**
     * 返回二维间隔的面积
     *
     * @return 二维间隔的面积
     */
    public double area() {
        return x.length() * y.length();
    }

    @Override
    public String toString() {
        return x + " x " + y;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }
        Interval2D that = (Interval2D) other;
        return this.x.equals(that.x) && this.y.equals(that.y);
    }

    @Override
    public int hashCode() {
        int hash1 = x.hashCode();
        int hash2 = y.hashCode();
        return 31 * hash1 + hash2;
    }

    /**
     * 用StdDraw绘制二维间隔
     */
    public void draw() {
        double xc = (x.min() + y.max()) / 2.0;
        double yc = (y.min() + x.max()) / 2.0;
        StdDraw.rectangle(xc, yc, x.length() / 2.0, y.length() / 2.0);
    }

    public static void main(String[] args) {
        double xmin = Double.parseDouble(args[0]);
        double xmax = Double.parseDouble(args[1]);
        double ymin = Double.parseDouble(args[2]);
        double ymax = Double.parseDouble(args[3]);
        int trials = Integer.parseInt(args[4]);

        Interval1D xInterval = new Interval1D(xmin, xmax);
        Interval1D yInterval = new Interval1D(ymin, ymax);
        Interval2D box = new Interval2D(xInterval, yInterval);
        box.draw();

        Counter counter = new Counter("hits");
        for (int t = 0; t < trials; t++) {
            double x = StdRandom.uniform(0.0, 1.0);
            double y = StdRandom.uniform(0.0, 1.0);
            Point2D point = new Point2D(x, y);

            if (box.contains(point)) counter.increment();
            else point.draw();
        }

        StdOut.println(counter);
        StdOut.printf("box area = %.2f\n", box.area());
    }
}
