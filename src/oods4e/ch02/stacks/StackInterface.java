package oods4e.ch02.stacks;

public interface StackInterface<T> {

    void push(T element) throws StackOverflowException;

    void pop() throws StackUnderflowException;

    T top() throws StackUnderflowException;

    boolean isFull();

    boolean isEmpty();
}
