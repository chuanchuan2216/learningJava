package algs4.fundamentals;

import support.Stdlib.StdDraw;

public final class RectHV {
    private final double xmin, ymin;
    private final double xmax, ymax;

    /**
     * 创建一个矩形
     *
     * @param xmin 矩形左下角x坐标
     * @param ymin 矩形左下角y坐标
     * @param xmax 矩形右上角x坐标
     * @param ymax 矩形右上角y坐标
     */
    public RectHV(double xmin, double ymin, double xmax, double ymax) {
        this.xmin = xmin;
        this.ymin = ymin;
        this.xmax = xmax;
        this.ymax = ymax;

        if (Double.isNaN(xmin) || (Double.isNaN(xmax))) {
            throw new IllegalArgumentException("x坐标isNaN：" + toString());
        }
        if (Double.isNaN(ymin) || (Double.isNaN(ymax))) {
            throw new IllegalArgumentException("y坐标isNaN：" + toString());
        }
        if (xmax < xmin) {
            throw new IllegalArgumentException("xmax < xmin：" + toString());
        }
        if (ymax < ymin) {
            throw new IllegalArgumentException("ymax < ymin：" + toString());
        }
    }

    public double xmin() {
        return xmin;
    }

    public double xmax() {
        return xmax;
    }

    public double ymin() {
        return ymin;
    }

    public double ymax() {
        return ymax;
    }

    /**
     * 返回矩形的宽度
     *
     * @return 矩形的宽度
     */
    public double width() {
        return xmax - xmin;
    }

    /**
     * 返回矩形的高度
     *
     * @return 矩形的高度
     */
    public double height() {
        return ymax - ymin;
    }

    /**
     * 判断两个矩形是否相交
     *
     * @param that 另一个矩形
     * @return 相交返回true，否则返回false
     */
    public boolean intersects(RectHV that) {
        return (this.xmax >= that.xmin) &&
                (this.ymax >= that.ymin) &&
                (that.xmax >= this.xmin) &&
                (that.ymax >= this.ymin);
    }

    /**
     * 判断Point2D的点是否被包含在矩形范围内
     *
     * @param p Point2D的点
     * @return 包含返回true，否则返回false
     */
    public boolean contains(Point2D p) {
        return (p.x() >= xmin) &&
                (p.x() <= xmax) &&
                (p.y() >= ymin) &&
                (p.y() <= ymax);
    }

    /**
     * 返回Point2D的点到矩形的欧几里得距离
     *
     * @param p Point2D的点
     * @return Point2D的点到矩形的欧几里得距离
     */
    public double distanceTo(Point2D p) {
        return Math.sqrt(this.distanceSquaredTo(p));
    }

    /**
     * 返回Point2D的点到矩形的欧几里得距离的平方
     *
     * @param p
     * @return
     */
    public double distanceSquaredTo(Point2D p) {
        double dx = 0.0, dy = 0.0;
        if (p.x() < xmin) {
            dx = p.x() - xmin;
        } else if (p.x() > xmax) {
            dx = p.x() - xmax;
        }
        if (p.y() < ymin) {
            dy = p.y() - ymin;
        } else if (p.y() > ymax) {
            dy = p.y() - ymax;
        }
        return dx * dx + dy * dy;
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
        RectHV that = (RectHV) other;
        if (this.xmin != that.xmin) {
            return false;
        }
        if (this.xmax != that.xmax) {
            return false;
        }
        if (this.ymin != that.ymin) {
            return false;
        }
        if (this.ymax != that.ymax) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash1 = ((Double) xmin).hashCode();
        int hash2 = ((Double) ymin).hashCode();
        int hash3 = ((Double) xmax).hashCode();
        int hash4 = ((Double) ymax).hashCode();
        return 31 * (31 * (31 * hash1 + hash2) + hash3) + hash4;
    }

    @Override
    public String toString() {
        return "[" + xmin + "," + xmax + "] x [" + ymin + "," + ymax + "]";
    }

    public void draw() {
        StdDraw.line(xmin, ymin, xmax, ymax);
        StdDraw.line(xmax, ymin, xmax, ymax);
        StdDraw.line(xmax, ymax, xmin, ymax);
        StdDraw.line(xmin, ymax, xmin, ymin);
    }
}
