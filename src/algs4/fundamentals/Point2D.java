package algs4.fundamentals;

import support.Stdlib.StdDraw;
import support.Stdlib.StdRandom;

import java.util.Arrays;
import java.util.Comparator;

public final class Point2D implements Comparable<Point2D> {

    public static final Comparator<Point2D> X_ORDER = new XOrder();
    public static final Comparator<Point2D> Y_ORDER = new YOrder();
    public static final Comparator<Point2D> R_ORDER = new ROrder();

    private final double x;
    private final double y;

    /**
     * 创建一个点
     *
     * @param x 点的x坐标
     * @param y 点的y坐标
     */
    public Point2D(double x, double y) {
        if (Double.isInfinite(x) || Double.isInfinite(y)) {
            throw new IllegalArgumentException("坐标值必须是有限数");
        }
        if (Double.isNaN(x) || Double.isNaN(y)) {
            throw new IllegalArgumentException("坐标值不能是非数字");
        }
        if (x == 0.0) {
            this.x = 0.0;
        } else {
            this.x = x;
        }
        if (y == 0.0) {
            this.y = 0.0;
        } else {
            this.y = y;
        }
    }

    /**
     * 返回x坐标
     *
     * @return x坐标
     */
    public double x() {
        return x;
    }

    /**
     * 返回y坐标
     *
     * @return y坐标
     */
    public double y() {
        return y;
    }

    /**
     * 返回极径（极坐标）
     *
     * @return 极径（极坐标）
     */
    public double r() {
        return Math.sqrt(x * x + y * y);
    }

    /**
     * 返回r极角（极坐标）
     *
     * @return 极角（极坐标）
     */
    public double theta() {
        return Math.atan2(y, x);
    }

    /**
     * 返回当前点和另一个点的连线的倾斜角
     *
     * @param that 另一个点
     * @return 两点连线的倾斜角
     */
    private double angleTo(Point2D that) {
        double dx = that.x - this.x;
        double dy = that.y - this.y;
        return Math.atan2(dy, dx);
    }

    /**
     * 返回三个点的状态
     *
     * @param a 第一个点
     * @param b 第二个点
     * @param c 第三个点
     * @return -1，三点顺时针；0，三点一线；+1，三点逆时针
     */
    public static int ccw(Point2D a, Point2D b, Point2D c) {
        double area2 = area2(a, b, c);
        if (area2 < 0) {
            return -1;
        } else if (area2 > 0) {
            return +1;
        } else {
            return 0;
        }
    }

    /**
     * 返回三个点围成的三角形面积的平方
     *
     * @param a 第一个点
     * @param b 第二个点
     * @param c 第三个点
     * @return 三个点围成的三角形面积的平方
     */
    public static double area2(Point2D a, Point2D b, Point2D c) {
        return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
    }

    /**
     * 返回两点之间的欧几里得距离
     *
     * @param that 另一个点
     * @return 两点之间的欧几里得距离
     */
    public double distanceTo(Point2D that) {
        double dx = this.x - that.x;
        double dy = this.y - that.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * 返回两点之间的欧几里得距离的平方
     * @param that 另一个点
     * @return 两点之间的欧几里得距离的平方
     */
    public double distanceSquaredTo(Point2D that) {
        double dx = this.x - that.x;
        double dy = this.y - that.y;
        return dx * dx + dy * dy;
    }

    /**
     * 通过y坐标比较两个点
     * @param that 要比较的另一个点
     * @return
     */
    @Override
    public int compareTo(Point2D that) {
        if (this.y < that.y) {
            return -1;
        } else if (this.y > that.y) {
            return +1;
        } else if (this.x < that.x) {
            return -1;
        } else if (this.x > that.x) {
            return +1;
        } else {
            return 0;
        }
    }


    public Comparator<Point2D> polarOrder() {
        return new PolarOrder();
    }

    public Comparator<Point2D> atan2Order() {
        return new Atan2Order();
    }

    public Comparator<Point2D> distanceToOrder() {
        return new DistanceToOrder();
    }

    private static class XOrder implements Comparator<Point2D> {
        @Override
        public int compare(Point2D p, Point2D q) {
            if (p.x < q.x) {
                return -1;
            } else if (p.x > q.x) {
                return +1;
            }
            return 0;
        }
    }

    private static class YOrder implements Comparator<Point2D> {
        @Override
        public int compare(Point2D p, Point2D q) {
            if (p.y < q.y) {
                return -1;
            } else if (p.y > q.y) {
                return +1;
            }
            return 0;
        }
    }

    private static class ROrder implements Comparator<Point2D> {
        @Override
        public int compare(Point2D p, Point2D q) {
            double delta = (p.x * p.x + p.y * p.y) - (q.x * q.x + q.y * q.y);
            if (delta < 0) {
                return -1;
            } else if (delta > 0) {
                return +1;
            }
            return 0;
        }
    }

    private class Atan2Order implements Comparator<Point2D> {
        @Override
        public int compare(Point2D q1, Point2D q2) {
            double angle1 = angleTo(q1);
            double angle2 = angleTo(q2);
            if (angle1 < angle2) {
                return -1;
            } else if (angle1 > angle2) {
                return +1;
            }
            return 0;
        }
    }

    private class PolarOrder implements Comparator<Point2D> {
        @Override
        public int compare(Point2D q1, Point2D q2) {
            double dx1 = q1.x - x;
            double dy1 = q1.y - y;
            double dx2 = q2.x - x;
            double dy2 = q2.y - y;
            if (dy1 >= 0 && dy2 < 0) {
                return -1;
            } else if (dy2 >= 0 && dy1 < 0) {
                return +1;
            } else if (dy1 == 0 && dy2 == 0) {
                if (dx1 >= 0 && dx2 < 0) {
                    return -1;
                } else if (dx2 >= 0 && dx1 < 0) {
                    return +1;
                } else {
                    return 0;
                }
            } else {
                return -ccw(Point2D.this, q1, q2);
            }
        }
    }

    private class DistanceToOrder implements Comparator<Point2D> {
        @Override
        public int compare(Point2D p, Point2D q) {
            double dist1 = distanceSquaredTo(p);
            double dist2 = distanceSquaredTo(q);
            if (dist1 < dist2) {
                return -1;
            } else if (dist1 > dist2) {
                return +1;
            } else {
                return 0;
            }
        }
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
        Point2D that = (Point2D) other;
        return this.x == that.x && this.y == that.y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    @Override
    public int hashCode() {
        int hashX = ((Double) x).hashCode();
        int hashY = ((Double) y).hashCode();
        return 31 * hashX + hashY;
    }

    public void draw() {
        StdDraw.point(x, y);
    }

    public void drawTo(Point2D that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public static void main(String[] args) {
        int x0 = Integer.parseInt((args[0]));
        int y0 = Integer.parseInt((args[1]));
        int n = Integer.parseInt((args[2]));

        StdDraw.setCanvasSize(800, 800);
        StdDraw.setXscale(0, 100);
        StdDraw.setYscale(0, 100);
        StdDraw.setPenRadius(0.005);
        StdDraw.enableDoubleBuffering();

        Point2D[] points = new Point2D[n];
        for (int i = 0; i < n; i++) {
            int x = StdRandom.uniform(100);
            int y = StdRandom.uniform(100);
            points[i] = new Point2D(x, y);
            points[i].draw();
        }

        // draw p = (x0, x1) in red
        Point2D p = new Point2D(x0, y0);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setPenRadius(0.02);
        p.draw();


        // draw line segments from p to each point, one at a time, in polar order
        StdDraw.setPenRadius();
        StdDraw.setPenColor(StdDraw.BLUE);
        Arrays.sort(points, p.polarOrder());
        for (int i = 0; i < n; i++) {
            p.drawTo(points[i]);
            StdDraw.show();
            StdDraw.pause(100);
        }
    }
}
