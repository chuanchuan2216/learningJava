package algs4.fundamentals;

import support.Stdlib.StdOut;

import java.util.Comparator;

public class Date implements Comparable<Date> {
    private static final int[] DAYS = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    private final int year;
    private final int month;
    private final int day;

    public Date(int year, int month, int day) {
        if (!isValid(year, month, day)) {
            throw new IllegalArgumentException("无效的日期");
        }
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public Date(String date) {
        String[] fields = date.split(".");
        if (fields.length != 3) {
            throw new IllegalArgumentException("无效的日期");
        }
        year = Integer.parseInt(fields[0]);
        month = Integer.parseInt(fields[1]);
        day = Integer.parseInt(fields[2]);
        if (!isValid(year, month, day)) {
            throw new IllegalArgumentException("无效的日期");
        }
    }

    public int year() {
        return year;
    }

    public int month() {
        return month;
    }

    public int day() {
        return day;
    }

    private static boolean isValid(int y, int m, int d) {
        if (m < 1 || m > 12) {
            return false;
        }
        if (d < 1 || d > DAYS[m]) {
            return false;
        }
        if (m == 2 && d == 29 && !isLeapYear(y)) {
            return false;
        }
        return true;
    }

    private static boolean isLeapYear(int y) {
        if (y % 400 == 0) {
            return true;
        }
        if ((y % 100 == 0)) {
            return false;
        }
        return y % 4 == 0;
    }

    public Date next() {
        if (isValid(year, month, day + 1)) {
            return new Date(year, month, day + 1);
        } else if (isValid(year, month + 1, 1)) {
            return new Date(year, month + 1, 1);
        } else {
            return new Date(year + 1, 1, 1);
        }
    }

    public boolean isAfter(Date that) {
        return compareTo(that) > 0;
    }

    public boolean isBefore(Date that) {
        return compareTo(that) < 0;
    }

    @Override
    public int compareTo(Date that) {
        if (this.year < that.year) {
            return -1;
        }
        if (this.year > that.year) {
            return +1;
        }
        if (this.month < that.month) {
            return -1;
        }
        if (this.month > that.month) {
            return +1;
        }
        if (this.day < that.day) {
            return -1;
        }
        if (this.day > that.day) {
            return +1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return year + "." + month + "." + day;
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
        Date that = (Date) other;
        return this.year == that.year && this.month == that.month && this.day == that.day;
    }

    @Override
    public int hashCode() {
        return 31 * 12 * year + 31 * month + day;
    }

    public static void main(String[] args) {
        Date today = new Date(2020, 2, 25);
        StdOut.println(today);
        for (int i = 0; i < 10; i++) {
            today = today.next();
            StdOut.println(today);
        }

        StdOut.println(today.isAfter(today.next()));
        StdOut.println(today.isAfter(today));
        StdOut.println(today.next().isAfter(today));


        Date birthday = new Date(1982, 12, 27);
        StdOut.println(birthday);
        for (int i = 0; i < 10; i++) {
            birthday = birthday.next();
            StdOut.println(birthday);
        }
    }
}
