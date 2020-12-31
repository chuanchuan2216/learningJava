package oods4e.ch07.trees;

import oods4e.ch04.queues.LinkedQueue;
import support.BSTNode;

import java.util.Comparator;
import java.util.Iterator;

public class BinarySearchTree<T> implements BSTInterface<T> {

    protected BSTNode<T> root;
    protected Comparator<T> comp;

    protected boolean found;

    public BinarySearchTree() {
        root = null;
        comp = new Comparator<T>() {
            @Override
            public int compare(T element1, T element2) {
                return ((Comparable) element1).compareTo(element2);
            }
        };
    }

    public BinarySearchTree(Comparator<T> comp) {
        root = null;
        this.comp = comp;
    }

    @Override
    public boolean add(T element) {
        root = recAdd(element, root);
        return true;
    }

    private BSTNode<T> recAdd(T element, BSTNode<T> node) {
        if (node == null)
            node = new BSTNode<T>(element);
        else if (comp.compare(element, node.getInfo()) <= 0)
            node.setLeft(recAdd(element, node.getLeft()));
        else
            node.setRight(recAdd(element, node.getRight()));
        return node;
    }

    @Override
    public T get(T target) {
        return recGet(target, root);
    }

    private T recGet(T target, BSTNode<T> node) {
        if (node == null)
            return null;
        else if (comp.compare(target, node.getInfo()) < 0)
            return recGet(target, node.getLeft());
        else if (comp.compare(target, node.getInfo()) > 0)
            return recGet(target, node.getRight());
        else
            return node.getInfo();
    }

    @Override
    public boolean contains(T target) {
        return recContains(target, root);
    }

    private boolean recContains(T target, BSTNode<T> node) {
        if (node == null)
            return false;
        else if (comp.compare(target, node.getInfo()) < 0)
            return recContains(target, node.getLeft());
        else if (comp.compare(target, node.getInfo()) > 0)
            return recContains(target, node.getRight());
        else
            return true;
    }

    @Override
    public boolean remove(T target) {
        root = recRemove(target, root);
        return false;
    }

    private BSTNode<T> recRemove(T target, BSTNode<T> node) {
        if (node == null)
            found = false;
        else if (comp.compare(target, node.getInfo()) < 0)
            node.setLeft(recRemove(target, node.getLeft()));
        else if (comp.compare(target, node.getInfo()) > 0)
            node.setRight(recRemove(target, node.getRight()));
        else {
            node = removeNode(node);
            found = true;
        }
        return node;
    }

    private BSTNode<T> removeNode(BSTNode<T> node) {
        T data;
        if (node.getLeft() == null)
            return node.getRight();
        else if (node.getRight() == null)
            return node.getLeft();
        else {
            data = getPredecessor(node.getLeft());
            node.setInfo(data);
            node.setLeft(recRemove(data, node.getLeft()));
            return node;
        }
    }

    private T getPredecessor(BSTNode<T> subtree) {
        BSTNode<T> temp = subtree;
        while (temp.getRight() != null)
            temp = temp.getRight();
        return temp.getInfo();
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return (root == null);
    }

    @Override
    public int size() {
        return recSize(root);
    }

    private int recSize(BSTNode<T> node) {
        if (node == null)
            return 0;
        else
            return 1 + recSize(node.getLeft()) + recSize(node.getRight());
    }

    @Override
    public T min() {
        if (isEmpty())
            return null;
        else {
            BSTNode<T> node = root;
            while (node.getLeft() != null)
                node = node.getLeft();
            return node.getInfo();
        }
    }

    @Override
    public T max() {
        if (isEmpty())
            return null;
        else {
            BSTNode<T> node = root;
            while (node.getRight() != null)
                node = node.getRight();
            return node.getInfo();
        }
    }

    @Override
    public Iterator<T> getInterator(BSTInterface.Traversal orderType) {
        final LinkedQueue<T> infoQueue = new LinkedQueue<T>();
        if (orderType == Traversal.Preorder)
            preOrder(root, infoQueue);
        else if (orderType == Traversal.Inorder)
            inOrder(root, infoQueue);
        else if (orderType == Traversal.Postorder)
            postOrder(root, infoQueue);
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return !infoQueue.isEmpty();
            }

            @Override
            public T next() {
                if (!hasNext())
                    throw new IndexOutOfBoundsException("BinarySearchTree迭代器无法访问下一个元素。");
                return infoQueue.dequeue();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("BinarySearchTree迭代器不支持remove元素。");
            }
        };
    }

    private void preOrder(BSTNode<T> node, LinkedQueue<T> q) {
        if (node != null) {
            q.enqueue(node.getInfo());
            preOrder(node.getLeft(), q);
            preOrder(node.getRight(), q);
        }
    }

    private void inOrder(BSTNode<T> node, LinkedQueue<T> q) {
        if (node != null) {
            inOrder(node.getLeft(), q);
            q.enqueue(node.getInfo());
            inOrder(node.getRight(), q);
        }
    }

    private void postOrder(BSTNode<T> node, LinkedQueue<T> q) {
        if (node != null) {
            postOrder(node.getLeft(), q);
            postOrder(node.getRight(), q);
            q.enqueue(node.getInfo());
        }
    }

    @Override
    public Iterator<T> iterator() {
        return getInterator(Traversal.Inorder);
    }
}
