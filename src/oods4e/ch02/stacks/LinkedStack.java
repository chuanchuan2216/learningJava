package oods4e.ch02.stacks;

import support.LLNode;

public class LinkedStack<T> implements StackInterface<T> {

    protected LLNode<T> top;

    public LinkedStack() {
        top = null;
    }

    @Override
    public void push(T element) throws StackOverflowException {
        LLNode<T> newNode = new LLNode<T>(element);
        newNode.setLink(top);
        top = newNode;
    }

    @Override
    public void pop() throws StackUnderflowException {
        if (isEmpty()) {
            throw new StackUnderflowException("栈空，不能弹栈");
        } else {
            top = top.getLink();
        }
    }

    @Override
    public T top() throws StackUnderflowException {
        if (isEmpty()) {
            throw new StackUnderflowException("栈空，不能顶栈");
        } else {
            return top.getInfo();
        }
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return (top == null);
    }

}
