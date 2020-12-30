package ch02.stacks;

public class ArrayBoundedStack<T> implements StackInterface<T> {

    protected final int DEFCAP = 100;
    protected T[] elements;
    protected int topIndex = -1;

    public ArrayBoundedStack() {
        elements = (T[]) new Object[DEFCAP];
    }

    public ArrayBoundedStack(int maxSize) {
        elements = (T[]) new Object[maxSize];
    }

    @Override
    public void push(T element) throws StackOverflowException {
        if (isFull())
            throw new StackOverflowException("栈满，不能压栈");
        else {
            topIndex++;
            elements[topIndex] = element;
        }
    }

    @Override
    public void pop() throws StackUnderflowException {
        if (isEmpty())
            throw new StackUnderflowException("栈空，不能弹栈");
        else {
            elements[topIndex] = null;
            topIndex--;
        }
    }

    @Override
    public T top() throws StackUnderflowException {
        T topOFStack = null;
        if (isEmpty())
            throw new StackUnderflowException("栈空，不能顶栈");
        else {
            topOFStack = elements[topIndex];
        }
        return topOFStack;
    }

    @Override
    public boolean isFull() {
        return (topIndex == (elements.length - 1));
    }

    @Override
    public boolean isEmpty() {
        return (topIndex == -1);
    }

}
