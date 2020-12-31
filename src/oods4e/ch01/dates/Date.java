package oods4e.ch01.dates;

public class Date {
    protected int year, month, day;
    public static final int MINYEAR = 1583;

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int lilian() {
        final int subDays = 578100;

        int numDays = 0;

        numDays = year * 365;

        if (month <= 2) {
            numDays = numDays + (month - 1) * 31;
        } else {
            numDays = numDays + (month - 1) * 31 - ((month - 1) * 4 + 27) / 10;
        }

        numDays = numDays + day;

        numDays = numDays + (year / 4) - (year / 100) + (year / 400);

        if (month < 3) {
            if ((year % 4) == 0) {
                numDays = numDays - 1;
            }
            if ((year % 100) == 0) {
                numDays = numDays + 1;
            }
            if ((year % 400) == 0) {
                numDays = numDays - 1;
            }
        }

        numDays = numDays - subDays;

        return numDays;
    }

    public int numLeapYear() {
        return (year / 4) - (year / 100) + (year / 400);
    }

    public int compareTo(Date anotherDate) {
        return this.lilian() - anotherDate.lilian();
    }

    @Override
    public String toString() {
        return (year + "年" + month + "月" + day + "日");
    }
}
