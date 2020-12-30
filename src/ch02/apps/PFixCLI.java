package ch02.apps;

import java.util.Scanner;

import ch02.postfix.*;

public class PFixCLI {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String expression = null;
        final String STOP = "X";
        int result;

        while (!STOP.equals(expression)) {
            System.out.print("\n请输入后缀表达式：（输入" + STOP + "结束）：");
            expression = scan.nextLine();

            if (!STOP.equals(expression)) {
                try {
                    result = PostFixEvaluator.evaluate(expression);
                    System.out.println("计算结果：" + result);
                } catch (PostFixException e) {
                    System.out.println("错误信息：" + e.getMessage());
                }
            }
        }
    }

}
