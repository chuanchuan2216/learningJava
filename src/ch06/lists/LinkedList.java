package ch06.lists;

import support.LLNode;

import java.util.Iterator;

public class LinkedList<T> implements ListInterface<T> {

    protected LLNode<T> front;
    protected LLNode<T> rear;
    protected int numElements = 0;

    protected boolean found;
    protected int targetIndex;
    protected LLNode<T> location;
    protected LLNode<T> previous;

    public LinkedList() {
        numElements = 0;
        front = null;
        rear = null;
    }

    protected void find(T target) {
        location = front;
        found = false;
        targetIndex = -1;

        while (location != null) {
            targetIndex++;
            if (location.getInfo().equals(target)) {
                found = true;
                return;
            } else {
                previous = location;
                location = location.getLink();
            }
        }
    }

    @Override
    public boolean add(T element) {
        LLNode<T> newNode = new LLNode<T>(element);
        if (rear == null)
            front = newNode;
        else
            rear.setLink(newNode);
        rear = newNode;
        numElements++;
        return true;
    }

    @Override
    public T get(T target) {
        find(target);
        if (found)
            return location.getInfo();
        else
            return null;
    }

    @Override
    public boolean contains(T target) {
        find(target);
        return found;
    }

    @Override
    public boolean remove(T target) {
        find(target);
        if (found) {
            if (front == location)
                front = front.getLink();
            else
                previous.setLink(location.getLink());
            if (front == null)
                rear = null;
            numElements--;
        }
        return found;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return (numElements == 0);
    }

    @Override
    public int size() {
        return numElements;
    }

    @Override
    public void add(int index, T element) {
        if ((index < 0) || (index >= size())) {
            throw new IndexOutOfBoundsException("索引值：" + index + "超出了LinkedList列表索引范围，无法add()。");
        }
        LLNode<T> newNode = new LLNode<T>(element);
        if (index == 0) {
            if (front == null) {
                front = newNode;
                rear = newNode;
            } else {
                newNode.setLink(front);
                front = newNode;
            }
        } else if (index == size()) {
            rear.setLink(newNode);
            rear = newNode;
        } else {
            LLNode<T> node = front;
            for (int i = 0; i < (index - 1); i++)
                node = node.getLink();
            newNode.setLink(node.getLink());
            node.setLink(newNode);
        }
        numElements++;
    }

    @Override
    public T set(int index, T newElement) {
        if ((index < 0) || (index >= size())) {
            throw new IndexOutOfBoundsException("索引值：" + index + "超出了LinkedList列表索引范围，无法set()。");
        }
        LLNode<T> node = front;
        for (int i = 0; i < index; i++)
            node = node.getLink();
        T hold = node.getInfo();
        node.setInfo(newElement);
        return hold;
    }

    @Override
    public T get(int index) {
        if ((index < 0) || (index >= size())) {
            throw new IndexOutOfBoundsException("索引值：" + index + "超出了LinkedList列表索引范围，无法get()。");
        }
        LLNode<T> node = front;
        for (int i = 0; i < index; i++)
            node = node.getLink();
        return node.getInfo();
    }

    @Override
    public int indexOf(T target) {
        find(target);
        if (found)
            return targetIndex;
        else
            return -1;
    }

    @Override
    public T remove(int index) {
        if ((index < 0) || (index >= size())) {
            throw new IndexOutOfBoundsException("索引值：" + index + "超出了LinkedList列表索引范围，无法remove()。");
        }
        T hold;
        if (index == 0) {
            hold = front.getInfo();
            front = front.getLink();
            if (numElements == 1)
                rear = null;
        } else {
            LLNode<T> node = front;
            for (int i = 0; i < (index - 1); i++)
                node = node.getLink();
            hold = node.getLink().getInfo();
            if (node.getLink() == rear)
                rear = node;
            node.setLink(node.getLink().getLink());
        }
        numElements--;
        return hold;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private LLNode<T> prevPos = null;
            private LLNode<T> currPos = null;
            private LLNode<T> nextPos = null;

            @Override
            public boolean hasNext() {
                return (nextPos != null);
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new IndexOutOfBoundsException("LinkedList列表迭代器无法访问下一个元素。");
                }
                T hold = nextPos.getInfo();
                if (currPos != null)
                    prevPos = currPos;
                currPos = nextPos;
                nextPos = nextPos.getLink();
                return hold;
            }

            public void remove() {
                if (currPos == null)
                    return;
                else {
                    if (prevPos == null) {
                        front = nextPos;
                        currPos = null;
                        if (front == null)
                            rear = null;
                    } else {
                        prevPos.setLink(nextPos);
                        currPos = null;
                    }
                    numElements--;
                }
            }
        };
    }
}
