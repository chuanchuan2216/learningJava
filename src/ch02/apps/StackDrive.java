package ch02.apps;

import ch02.stacks.*;

public class StackDrive {

    public static void main(String[] args) {

        StackInterface<String> test = new ArrayBoundedStack<String>();

        test.push("123");
        test.push("abc");
        test.push("一二三");
        test.pop();

        String s = test.top();
        if (s.equals("abc")) {
            System.out.print("OK");
        } else {
            System.out.print("not OK");
        }
    }

}
