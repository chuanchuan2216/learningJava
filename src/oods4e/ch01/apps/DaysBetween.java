package oods4e.ch01.apps;

import oods4e.ch01.dates.Date;

import java.util.Scanner;

public class DaysBetween {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int year, month, day;

        System.out.println("请注意输入的日期格式为：年 月 日");
        System.out.println("例如：2020年12月18日，则输入：2020 12 18");
        System.out.println();
        System.out.println("请不要输入" + Date.MINYEAR + "之前的日期。");
        System.out.println();

        System.out.println("请输入第一个日期：");
        year = scan.nextInt();
        month = scan.nextInt();
        day = scan.nextInt();
        Date firstDay = new Date(year, month, day);

        System.out.println("请输入第二个日期：");
        year = scan.nextInt();
        month = scan.nextInt();
        day = scan.nextInt();
        Date secondDay = new Date(year, month, day);

        if ((firstDay.getYear() <= Date.MINYEAR) || (secondDay.getYear() <= Date.MINYEAR)) {
            System.out.println("请不要输入" + Date.MINYEAR + "之前的日期。");
        } else {
            System.out.println("两个日期之间（" + firstDay + "-" + secondDay + "）间隔的天数为：");
            System.out.println(Math.abs(firstDay.lilian() - secondDay.lilian()));
            System.out.println("两个日期之间（" + firstDay + "-" + secondDay + "）间隔的闰年数为：");
            System.out.println(Math.abs(firstDay.numLeapYear() - secondDay.numLeapYear()));
        }
    }
}
