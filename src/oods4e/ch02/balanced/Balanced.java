package oods4e.ch02.balanced;

import oods4e.ch02.stacks.*;

public class Balanced {

    protected String openSet;
    protected String closeSet;

    public Balanced(String openSet, String closeSet) {
        this.openSet = openSet;
        this.closeSet = closeSet;
    }

    public int test(String expression) {
        char currChar;
        int currCharIndex;
        int lastCharIndex;
        int openIndex;
        int closeIndex;
        boolean stillBalanced = true;
        StackInterface<Integer> stack;
        stack = new ArrayBoundedStack<Integer>(expression.length());
        currCharIndex = 0;
        lastCharIndex = expression.length() - 1;
        while (stillBalanced && (currCharIndex <= lastCharIndex)) {
            currChar = expression.charAt(currCharIndex);
            openIndex = openSet.indexOf(currChar);
            if (openIndex != -1) {
                stack.push(openIndex);
            } else {
                closeIndex = closeSet.indexOf(currChar);
                if (closeIndex != -1) {
                    try {
                        openIndex = stack.top();
                        stack.pop();
                        if (openIndex != closeIndex) {
                            stillBalanced = false;
                        }
                    } catch (StackUnderflowException e) {
                        stillBalanced = false;
                    }
                }
            }
            currCharIndex++;
        }
        if (!stillBalanced)
            return 1;
        else if (!stack.isEmpty())
            return 2;
        else
            return 0;
    }
}
