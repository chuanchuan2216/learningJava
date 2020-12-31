package oods4e.ch06.apps;

import oods4e.ch06.largeInts.LargeInt;

import java.util.Scanner;

public class LargeIntCLI {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        LargeInt first, second;
        String intString;
        String more = null;

        do {
            System.out.println("请输入第一个整数：");
            intString = scan.nextLine();
            first = new LargeInt(intString);

            System.out.println("请输入第二个整数：");
            intString = scan.nextLine();
            second = new LargeInt(intString);

            System.out.println();
            System.out.print("第一个整数是：");
            System.out.println(first);
            System.out.print("第二个整数是：");
            System.out.println(second);
            System.out.print("两者的和是：");
            System.out.println(LargeInt.add(first, second));
            System.out.print("两者的差是：");
            System.out.println(LargeInt.subtract(first, second));

            System.out.println();
            System.out.print("继续计算？(Y=yes)");
            more = scan.nextLine();
            System.out.println();
        } while (more.equalsIgnoreCase("y"));
    }
}
