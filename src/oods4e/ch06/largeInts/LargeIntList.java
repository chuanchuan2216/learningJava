package oods4e.ch06.largeInts;

import support.DLLNode;

import java.util.Iterator;

public class LargeIntList {
    protected DLLNode<Byte> listFirst;
    protected DLLNode<Byte> listLast;
    protected int numElements;

    public LargeIntList() {
        numElements = 0;
        listFirst = null;
        listLast = null;
    }

    public int size() {
        return numElements;
    }

    public void addFront(byte element) {
        DLLNode<Byte> newNode = new DLLNode<Byte>(element);
        newNode.setForword(listFirst);
        newNode.setBack(null);
        if (listFirst == null) {
            listFirst = newNode;
            listLast = newNode;
        } else {
            listFirst.setBack(newNode);
            listFirst = newNode;
        }
        numElements++;
    }

    public void addEnd(byte element) {
        DLLNode<Byte> newNode = new DLLNode<Byte>(element);
        newNode.setForword(listLast);
        newNode.setForword(null);
        newNode.setBack(listLast);
        if (listFirst == null) {
            listFirst = newNode;
            listLast = newNode;
        } else {
            listLast.setForword(newNode);
            listLast = newNode;
        }
        numElements++;
    }

    public Iterator<Byte> forword() {
        return new Iterator<Byte>() {
            private DLLNode<Byte> next = listFirst;

            @Override
            public boolean hasNext() {
                return (next != null);
            }

            @Override
            public Byte next() {
                if (!hasNext())
                    throw new IndexOutOfBoundsException("LargeIntList列表forword迭代器无法访问下一个元素。");
                Byte hold = next.getInfo();
                next = next.getForword();
                return hold;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("LargeIntList列表forword迭代器不支持remove元素。");
            }
        };
    }

    public Iterator<Byte> reverse() {
        return new Iterator<Byte>() {
            private DLLNode<Byte> next = listLast;

            @Override
            public boolean hasNext() {
                return (next != null);
            }

            @Override
            public Byte next() {
                if (!hasNext())
                    throw new IndexOutOfBoundsException("LargeIntList列表reverse迭代器无法访问下一个元素。");
                Byte hold = next.getInfo();
                next = next.getBack();
                return hold;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("LargeIntList列表reverse迭代器不支持remove元素。");
            }
        };
    }
}
