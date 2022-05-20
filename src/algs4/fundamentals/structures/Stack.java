package algs4.fundamentals.structures;

import support.Stdlib.StdIn;
import support.Stdlib.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<Item> implements Iterable<Item> {
    private Node<Item> first; //栈顶
    private int n;

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    /**
     * 创建一个空栈
     */
    public Stack() {
        first = null;
        n = 0;
    }

    /**
     * 判断栈是否为空
     *
     * @return 栈空返回true, 否则返回false
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * 返回栈内元素的个数
     *
     * @return 栈内元素的个数
     */
    public int size() {
        return n;
    }

    /**
     * 压栈,添加元素到栈顶
     *
     * @param item 需要添加的元素
     */
    public void push(Item item) {
        Node<Item> oldfirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldfirst;
        n++;
    }

    /**
     * 顶栈,删除并返回栈顶元素
     *
     * @return 栈顶元素
     */
    public Item pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("空栈,操作下溢");
        }
        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }

    /**
     * 弹栈,返回栈顶元素
     *
     * @return 栈顶元素
     */
    public Item peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("空栈,操作下溢");
        }
        return first.item;
    }

    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        for (Item item :
                this) {
            s.append(item);
            s.append(" ");
        }
        return s.toString();
    }

    public Iterator<Item> iterator() {
        return new LinkedIterator(first);
    }

    private class LinkedIterator implements Iterator<Item> {
        private Node<Item> current;

        public LinkedIterator(Node<Item> first) {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        Stack<String> stack = new Stack<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                stack.push(item);
            else if (!stack.isEmpty())
                StdOut.print(stack.pop() + " ");
        }
        StdOut.println("(" + stack.size() + " left on stack)");
    }
}
