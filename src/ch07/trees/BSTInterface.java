package ch07.trees;

import ch05.collections.CollectionInterface;

import java.util.Iterator;

public interface BSTInterface<T> extends CollectionInterface<T>,Iterable<T> {
    public enum Traversal {Inorder,Preorder,Postorder};

    T min();

    T max();

    public Iterator getInterator(Traversal orderType);
}
