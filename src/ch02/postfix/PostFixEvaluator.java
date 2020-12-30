package ch02.postfix;

import java.util.Scanner;

import ch02.stacks.*;

public class PostFixEvaluator {

    public static int evaluate(String expression) {
        Scanner tokenizer = new Scanner(expression);
        StackInterface<Integer> stack;
        stack = new ArrayBoundedStack<Integer>(50);

        int value;
        String operator;
        int operand1, operand2;
        int result = 0;

        while (tokenizer.hasNext()) {
            if (tokenizer.hasNextInt()) {
                value = tokenizer.nextInt();
                if (stack.isFull()) {
                    throw new PostFixException("栈已满，无法对操作数压栈");
                }
                stack.push(value);
            } else {
                operator = tokenizer.next();
                if (!(operator.equals("+") || operator.equals("-") ||
                        operator.equals("*") || operator.equals("/"))) {
                    throw new PostFixException("无效的运算符：" + operator);
                }

                if (stack.isEmpty()) {
                    throw new PostFixException("栈空，无操作数可用");
                }
                operand2 = stack.top();
                stack.pop();

                if (stack.isEmpty()) {
                    throw new PostFixException("栈空，无操作数可用");
                }
                operand1 = stack.top();
                stack.pop();

                switch (operator) {
                    case "+":
                        result = operand1 + operand2;
                        break;
                    case "-":
                        result = operand1 - operand2;
                        break;
                    case "*":
                        result = operand1 * operand2;
                        break;
                    case "/":
                        result = operand1 / operand2;
                        break;
                }
                stack.push(result);
            }
        }

        if (stack.isEmpty()) {
            throw new PostFixException("栈空，无操作数可用");
        }
        result = stack.top();
        stack.pop();
        if (!stack.isEmpty()) {
            throw new PostFixException("栈未空，计算结果有误");
        }
        return result;
    }
}
