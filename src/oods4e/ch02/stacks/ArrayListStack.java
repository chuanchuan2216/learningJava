package oods4e.ch02.stacks;

import java.util.ArrayList;

public class ArrayListStack<T> implements StackInterface<T> {

    protected ArrayList<T> elements;

    public ArrayListStack() {
        elements = new ArrayList<T>();
    }

    @Override
    public void push(T element) throws StackOverflowException {
        elements.add(element);
    }

    @Override
    public void pop() throws StackUnderflowException {
        if (isEmpty())
            throw new StackUnderflowException("栈空，不能弹栈");
        else {
            elements.remove(elements.size() - 1);
        }
    }

    @Override
    public T top() throws StackUnderflowException {
        T topOfStack = null;
        if (isEmpty())
            throw new StackUnderflowException("栈空，不能顶栈");
        else {
            topOfStack = elements.get(elements.size() - 1);
        }
        return topOfStack;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return (elements.size() == 0);
    }

}
