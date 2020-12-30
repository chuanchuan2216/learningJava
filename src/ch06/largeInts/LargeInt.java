package ch06.largeInts;

import java.util.Iterator;

public class LargeInt {
    protected LargeIntList numbers;

    protected static final boolean PLUS = true;
    protected static final boolean MINUS = false;

    protected boolean sign;

    public LargeInt() {
        numbers = new LargeIntList();
        sign = PLUS;
    }

    public LargeInt(String intString) {
        numbers = new LargeIntList();
        sign = PLUS;

        int firstDigitPosition;
        int lastDigitPosition;

        char digitChar;
        int digitInt;
        byte digitByte;

        firstDigitPosition = 0;
        if (intString.charAt(0) == '+')
            firstDigitPosition = 1;
        else if (intString.charAt(0) == '-') {
            firstDigitPosition = 1;
            sign = MINUS;
        }

        lastDigitPosition = intString.length() - 1;

        for (int count = firstDigitPosition; count <= lastDigitPosition; count++) {
            digitChar = intString.charAt(count);
            digitInt = Character.digit(digitChar, 10);
            digitByte = (byte) digitInt;
            numbers.addEnd(digitByte);
        }
    }

    @Override
    public String toString() {
        Byte element;

        String largeIntString;
        if (sign == PLUS)
            largeIntString = "+";
        else
            largeIntString = "-";

        int count = numbers.size();
        Iterator<Byte> forword = numbers.forword();
        while (forword.hasNext()) {
            element = forword.next();
            largeIntString += element;
            if ((((count - 1) % 3) == 0) && (count != 1))
                largeIntString += ",";
            count--;
        }
        return largeIntString;
    }

    public void setNegative() {
        sign = MINUS;
    }

    protected static boolean greaterList(LargeIntList first, LargeIntList second) {
        boolean greater = false;
        if (first.size() > second.size())
            greater = true;
        else if (first.size() < second.size())
            greater = false;
        else {
            byte digitFirst;
            byte digitSecond;
            Iterator<Byte> firstForward = first.forword();
            Iterator<Byte> secondForward = second.forword();

            int length = first.size();
            boolean keepChecking = true;
            int count = 1;

            while ((count <= length) && (keepChecking)) {
                digitFirst = firstForward.next();
                digitSecond = secondForward.next();
                if (digitFirst > digitSecond) {
                    greater = true;
                    keepChecking = false;
                } else if (digitFirst < digitSecond) {
                    greater = false;
                    keepChecking = false;
                }
                count++;
            }
        }
        return greater;
    }

    protected static LargeIntList addLists(LargeIntList larger, LargeIntList smaller) {
        byte digit1, digit2, temp, carry = 0;

        int largerLength = larger.size();
        int smallerLength = smaller.size();
        int lengthDiff;

        LargeIntList result = new LargeIntList();

        Iterator<Byte> largerReverse = larger.reverse();
        Iterator<Byte> smallerReverse = smaller.reverse();

        for (int count = 1; count <= smallerLength; count++) {
            digit1 = largerReverse.next();
            digit2 = smallerReverse.next();
            temp = (byte) (digit1 + digit2 + carry);
            carry = (byte) (temp / 10);
            result.addFront((byte) (temp % 10));
        }

        lengthDiff = (largerLength - smallerLength);
        for (int count = 1; count <= lengthDiff; count++) {
            digit1 = largerReverse.next();
            temp = (byte) (digit1 + carry);
            carry = (byte) (temp / 10);
            result.addFront((byte) (temp % 10));
        }

        if (carry != 0)
            result.addFront((byte) carry);

        return result;
    }

    protected static LargeIntList subtractLists(LargeIntList larger, LargeIntList smaller) {
        byte digit1, digit2, temp;
        boolean borrow = false;

        int largerLength = larger.size();
        int smallerLength = smaller.size();
        int lengthDiff;

        LargeIntList result = new LargeIntList();

        Iterator<Byte> largerReverse = larger.reverse();
        Iterator<Byte> smallerReverse = smaller.reverse();

        for (int count = 1; count <= smallerLength; count++) {
            digit1 = largerReverse.next();
            if (borrow) {
                if (digit1 != 0) {
                    digit1 = (byte) (digit1 - 1);
                    borrow = false;
                } else {
                    digit1 = 9;
                    borrow = true;
                }
            }
            digit2 = smallerReverse.next();

            if (digit2 <= digit1)
                result.addFront((byte) (digit1 - digit2));
            else {
                result.addFront((byte) (digit1 + 10 - digit2));
                borrow = true;
            }
        }

        lengthDiff = largerLength - smallerLength;
        for (int count = 1; count <= lengthDiff; count++) {
            digit1 = largerReverse.next();
            if (borrow) {
                if (digit1 != 0) {
                    digit1 = (byte) (digit1 - 1);
                    borrow = false;
                } else {
                    digit1 = 9;
                    borrow = true;
                }
            }
            result.addFront(digit1);
        }
        return result;
    }

    public static LargeInt add(LargeInt first, LargeInt second) {
        LargeInt sum = new LargeInt();
        if (first.sign == second.sign) {
            if (greaterList(first.numbers, second.numbers))
                sum.numbers = addLists(first.numbers, second.numbers);
            else
                sum.numbers = addLists(second.numbers, first.numbers);
        } else {
            if (greaterList(first.numbers, second.numbers)) {
                sum.numbers = subtractLists(first.numbers, second.numbers);
                sum.sign = first.sign;
            } else {
                sum.numbers = subtractLists(second.numbers, first.numbers);
                sum.sign = second.sign;
            }
        }
        return sum;
    }

    public static LargeInt subtract(LargeInt first, LargeInt second) {
        LargeInt diff = new LargeInt();

        LargeInt negSecond = new LargeInt();
        negSecond.sign = !second.sign;
        Iterator<Byte> secondForward = second.numbers.forword();
        int length = second.numbers.size();
        for (int count = 1; count <= length; count++)
            negSecond.numbers.addEnd(secondForward.next());
        diff = add(first, negSecond);
        return diff;
    }
}
