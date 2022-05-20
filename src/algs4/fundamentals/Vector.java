package algs4.fundamentals;

import support.Stdlib.StdOut;

public class Vector {

    private int d;          //矢量分量的个数
    private double[] data;  //矢量分量，double数组

    /**
     * 初始化一个矢量，分量个数为d
     *
     * @param d 矢量分量的个数
     */
    public Vector(int d) {
        this.d = d;
        data = new double[d];
    }

    /**
     * 初始化一个矢量，将可变数组参数转化成矢量
     *
     * @param a 可变参数数组
     */
    public Vector(double... a) {
        d = a.length;
        data = new double[d];
        for (int i = 0; i < d; i++) {
            data[i] = a[i];
        }
    }

    /**
     * 返回矢量分量的个数
     *
     * @return 矢量分量的个数
     */
    @Deprecated
    public int length() {
        return d;
    }

    /**
     * 返回矢量分量的个数
     *
     * @return 矢量分量的个数
     */
    public int dimension() {
        return d;
    }

    /**
     * 返回两个矢量的点积
     *
     * @param that 另一个矢量
     * @return 两个矢量的点积
     */
    public double dot(Vector that) {
        if (this.d != that.d) {
            throw new IllegalArgumentException("大小不匹配");
        }
        double sum = 0.0;
        for (int i = 0; i < d; i++) {
            sum += (this.data[i] * that.data[i]);
        }
        return sum;
    }

    /**
     * 返回矢量的欧几里得范数
     *
     * @return 矢量的欧几里得范数
     */
    public double magnitude() {
        return Math.sqrt(this.dot(this));
    }

    /**
     * 返回两个矢量的欧几里得距离
     *
     * @param that 另一个矢量
     * @return 两个矢量的欧几里得距离
     */
    public double distanceTo(Vector that) {
        if (this.d != that.d) {
            throw new IllegalArgumentException("大小不匹配");
        }
        return this.minus(that).magnitude();
    }

    /**
     * 返回两个矢量求和
     *
     * @param that 另一个矢量
     * @return 两个矢量求和
     */
    public Vector plus(Vector that) {
        if (this.d != that.d) {
            throw new IllegalArgumentException("大小不匹配");
        }
        Vector c = new Vector(d);
        for (int i = 0; i < d; i++) {
            c.data[i] = this.data[i] + that.data[i];
        }
        return c;
    }

    /**
     * 返回两个矢量求差
     *
     * @param that 另一个矢量
     * @return 两个矢量求差
     */
    public Vector minus(Vector that) {
        if (this.d != that.d) {
            throw new IllegalArgumentException("大小不匹配");
        }
        Vector c = new Vector(d);
        for (int i = 0; i < d; i++) {
            c.data[i] = this.data[i] - that.data[i];
        }
        return c;
    }

    /**
     * 返回矢量中第i个笛卡尔坐标
     *
     * @param i 坐标的索引
     * @return 矢量中第i个笛卡尔坐标
     */
    public double cartesian(int i) {
        return data[i];
    }

    /**
     * 返回矢量与特定标量的积
     *
     * @param alpha 特定标量
     * @return 矢量与特定标量的积
     */
    @Deprecated
    public Vector times(double alpha) {
        Vector c = new Vector(d);
        for (int i = 0; i < d; i++) {
            c.data[i] = alpha * data[i];
        }
        return c;
    }

    /**
     * 返回矢量与特定标量的积
     *
     * @param alpha 特定标量
     * @return 矢量与特定标量的积
     */
    public Vector scale(double alpha) {
        Vector c = new Vector(d);
        for (int i = 0; i < d; i++) {
            c.data[i] = alpha * data[i];
        }
        return c;
    }

    /**
     * 返回当前矢量中的单位矢量
     *
     * @return 当前矢量中的单位矢量
     */
    public Vector direction() {
        if (this.magnitude() == 0) {
            throw new ArithmeticException("零矢量没有方向");
        }
        return this.scale(1.0 / this.magnitude());
    }

    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        for (int i = 0; i < d; i++) {
            s.append(data[i] + " ");
        }
        return s.toString();
    }

    public static void main(String[] args) {
        double[] xdata = {1.0, 2.0, 3.0, 4.0};
        double[] ydata = {5.0, 2.0, 4.0, 1.0};
        Vector x = new Vector(xdata);
        Vector y = new Vector(ydata);

        StdOut.println("   x       = " + x);
        StdOut.println("   y       = " + y);

        Vector z = x.plus(y);
        StdOut.println("   z       = " + z);

        z = z.times(10.0);
        StdOut.println(" 10z       = " + z);

        StdOut.println("  |x|      = " + x.magnitude());
        StdOut.println(" <x, y>    = " + x.dot(y));
        StdOut.println("dist(x, y) = " + x.distanceTo(y));
        StdOut.println("dir(x)     = " + x.direction());
    }
}
