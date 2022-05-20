package algs4.fundamentals.structures;

import support.Stdlib.StdIn;
import support.Stdlib.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayStack<Item> implements Iterable<Item> {
    private static final int INIT_CAPACITY = 8;

    private Item[] a;
    private int n;

    /**
     * 初始化一个空栈
     */
    public ResizingArrayStack() {
        a = (Item[]) new Object[INIT_CAPACITY];
        n = 0;
    }

    /**
     * 判断栈是否为空
     *
     * @return 栈空返回true, 否则返回false
     */
    public boolean isEmpty() {
        return n == 0;
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
     * 将栈内元素移动到新数组
     *
     * @param capacity 新数组的元素个数
     */
    private void resize(int capacity) {
        assert capacity >= n;
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = a[i];
        }
        a = copy;
    }

    /**
     * 压栈,添加元素到栈顶
     *
     * @param item 需要添加的元素
     */
    public void push(Item item) {
        if (n == a.length) {
            resize(2 * a.length);
        }
        a[n++] = item;
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
        Item item = a[n - 1];
        a[n - 1] = null;
        n--;
        if (n > 0 && n == a.length / 4) {
            resize(a.length / 2);
        }
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
        return a[n - 1];
    }

    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        private int i;

        public ReverseArrayIterator() {
            i = n - 1;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return i >= 0;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return a[i--];
        }
    }

    public static void main(String[] args) {
        ResizingArrayStack<String> stack = new ResizingArrayStack<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) stack.push(item);
            else if (!stack.isEmpty()) StdOut.print(stack.pop() + " ");
        }
        StdOut.println("(" + stack.size() + " left on stack)");
    }
}
