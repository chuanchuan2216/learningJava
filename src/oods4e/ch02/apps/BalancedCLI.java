package oods4e.ch02.apps;

import java.util.Scanner;

import oods4e.ch02.balanced.*;

public class BalancedCLI {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        Balanced bal = new Balanced("([{", ")]}");

        int result;

        String expression = null;
        final String STOP = "X";

        while (!STOP.equals(expression)) {
            System.out.print("Expression (" + STOP + " to stop):");
            expression = scan.nextLine();
            if (!STOP.equals(expression)) {
                result = bal.test(expression);
                if (result == 1)
                    System.out.println("不平衡");
                else if (result == 2)
                    System.out.println("提前结束");
                else
                    System.out.println("平衡");
            }
        }
    }

}
