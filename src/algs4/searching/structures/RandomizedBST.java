package algs4.searching.structures;

import algs4.fundamentals.structures.Stack;
import support.Stdlib.StdOut;
import support.Stdlib.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedBST<Key extends Comparable<Key>, Value> implements Iterable<Key> {
    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int size;

        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
            this.size = 1;
        }
    }

    /**
     * 判断键key是否存在于符号表中
     *
     * @param key 键key
     * @return 键key存在于符号表中返回true，否则返回false
     */
    public boolean contains(Key key) {
        return get(key) != null;
    }

    /**
     * 获取键key对应的值
     *
     * @param key 键key
     * @return 键key对应的值
     */
    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("调用get()时没有给定键key");
        }
        return get(root, key);
    }

    /**
     * 返回以结点x为根结点的子树中键key对应的值，未命中则返回null
     *
     * @param x   结点x
     * @param key 键key
     * @return 以结点x为根节点的子树中键key对应的值，未命中则返回null
     */
    private Value get(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x.val;
        } else if (cmp < 0) {
            return get(x.left, key);
        } else {
            return get(x.right, key);
        }
    }

    /**
     * 将键值对存入符号表中，若值为null则将键key删除
     *
     * @param key 键key
     * @param val 值val
     */
    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    /**
     * 如果键key存在于以结点x为根结点的子树中，用值val更新它的值，否则以键key和值val创建一个新结点。
     *
     * @param x   结点x
     * @param key 键key
     * @param val 值val
     * @return 更新后的结点x或者创建的新结点
     */
    private Node put(Node x, Key key, Value val) {
        if (x == null) {
            return new Node(key, val);
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            x.val = val;
            return x;
        }

        if (StdRandom.bernoulli(1.0 / (size(x) + 1.0))) {
            return putRoot(x, key, val);
        }
        if (cmp < 0) {
            x.left = put(x.left, key, val);
        } else if (cmp > 0) {
            x.right = put(x.right, key, val);
        } else {
            x.val = val;
        }
        return x;
    }

    private Node putRoot(Node x, Key key, Value val) {
        if (x == null) {
            return new Node(key, val);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = putRoot(x.left, key, val);
            x = rotR(x);
        } else if (cmp > 0) {
            x.right = putRoot(x.right, key, val);
            x = rotL(x);
        } else {
            x.val = val;
        }
        return x;
    }

    private Node joinLR(Node a, Node b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        if (StdRandom.bernoulli((double) size(a) / (size(a) + size(b)))) {
            a.right = joinLR(a.right, b);
            fix(a);
            return a;
        } else {
            b.left = joinLR(a, b.left);
            fix(b);
            return b;
        }
    }


    public Value remove(Key key) {
        Value val = get(key);
        root = remove(root, key);
        return val;
    }

    private Node remove(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            x = joinLR(x.left, x.right);
        } else if (cmp < 0) {
            x.left = remove(x.left, key);
        } else {
            x.right = remove(x.right, key);
        }
        fix(x);
        return x;
    }

    /**
     * 返回此符号表中的排序rank的键
     *
     * @param rank 排序rank
     * @return 此符号表中的排序rank的键
     */
    public Key select(int rank) {
        Node x = select(root, rank);
        return x.key;
    }

    /**
     * 返回以结点x为根结点的子树中排序rank的键
     *
     * @param x    结点x
     * @param rank 排序rank
     * @return 以结点x为根结点的子树中排序rank的键
     */
    private Node select(Node x, int rank) {
        if (x == null) {
            return null;
        }
        int t = size(x.left);
        if (t > rank) {
            return select(x.left, rank);
        } else if (t < rank) {
            return select(x.right, rank - t - 1);
        } else {
            return x;
        }
    }

    /**
     * 返回符号表中最小的键key
     *
     * @return 表中最小的键key
     */
    public Key min() {
        Key key = null;
        for (Node x = root; x != null; x = x.left) {
            key = x.key;
        }
        return key;
    }

    /**
     * 返回符号表中最大的键key
     *
     * @return 表中最大的键key
     */
    public Key max() {
        Key key = null;
        for (Node x = root; x != null; x = x.right) {
            key = x.key;
        }
        return key;
    }

    /**
     * 返回“大于等于”键key的键（向上取整）
     *
     * @param key 键key
     * @return “大于等于”键key的键
     */
    public Key ceiling(Key key) {
        Node best = ceiling(root, key, null);
        if (best == null) {
            return null;
        }
        return best.key;
    }

    /**
     * 返回以结点x为根结点的右子树中“大于等于”键key的键
     *
     * @param x   结点x
     * @param key 键key
     * @return 以结点x为根结点的右子树中“大于等于”键key的键
     */
    private Node ceiling(Node x, Key key, Node best) {
        if (x == null) {
            return best;
        } else if (eq(key, x.key)) {
            return x;
        } else if (less(key, x.key)) {
            return ceiling(x.left, key, x);
        } else {
            return ceiling(x.right, key, best);
        }
    }

    public Key ceiling2(Key key) {
        Node best = null;
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                best = x;
                x = x.left;
            } else if (cmp > 0) {
                x = x.right;
            } else {
                return x.key;
            }
        }
        if (best == null) {
            return null;
        }
        return best.key;
    }

    public Iterator<Key> iterator() {
        return new BSTIterator(root);
    }

    private class BSTIterator implements Iterator<Key> {
        private Stack<Node> stack = new Stack<>();

        public BSTIterator(Node x) {
            while (x != null) {
                stack.push(x);
                x = x.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Key next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node x = stack.pop();
            Key key = x.key;
            x = x.right;
            while (x != null) {
                stack.push(x);
                x = x.left;
            }
            return key;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * 返回表中键值对的数量
     *
     * @return 表中键值对的数量
     */
    public int size() {
        return size(root);
    }

    /**
     * 返回以结点x为根结点的子树中键值对的数量
     *
     * @param x 结点x
     * @return 以结点x为根节点的子树中键值对的数量
     */
    private int size(Node x) {
        if (x == null) {
            return 0;
        } else {
            return x.size;
        }
    }

    public int height() {
        return height(root);
    }

    private int height(Node x) {
        if (x == null) {
            return -1;
        }
        return 1 + Math.max(height(x.left), height(x.right));
    }

    private void fix(Node x) {
        if (x == null) {
            return;
        }
        x.size = size(x.left) + size(x.right) + 1;
    }

    private Node rotR(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;

        fix(h);
        fix(x);
        return x;
    }

    private Node rotL(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        fix(h);
        fix(x);
        return x;
    }

    public boolean check() {
        return checkCount() && isBST();
    }

    private boolean checkCount() {
        return checkCount(root);
    }

    private boolean checkCount(Node x) {
        if (x == null) {
            return true;
        }
        return checkCount(x.left) && checkCount(x.right) && (x.size == size(x.left) + size(x.right) + 1);
    }

    private boolean isBST() {
        return isBST(root, min(), max());
    }

    private boolean isBST(Node x, Key min, Key max) {
        if (x == null) {
            return true;
        }
        if (less(x.key, min) || less(max, x.key)) {
            return false;
        }
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }

    private boolean less(Key k1, Key k2) {
        return k1.compareTo(k2) < 0;
    }

    private boolean eq(Key k1, Key k2) {
        return k1.compareTo(k2) == 0;
    }

    public static void main(String[] args) {
        RandomizedBST<String, String> st = new RandomizedBST<String, String>();

        // insert some key-value pairs
        st.put("www.cs.princeton.edu", "128.112.136.11");
        st.put("www.cs.princeton.edu", "128.112.136.35");    // overwrite old value
        st.put("www.princeton.edu", "128.112.130.211");
        st.put("www.math.princeton.edu", "128.112.18.11");
        st.put("www.yale.edu", "130.132.51.8");
        st.put("www.amazon.com", "207.171.163.90");
        st.put("www.simpsons.com", "209.123.16.34");
        st.put("www.stanford.edu", "171.67.16.120");
        st.put("www.google.com", "64.233.161.99");
        st.put("www.ibm.com", "129.42.16.99");
        st.put("www.apple.com", "17.254.0.91");
        st.put("www.slashdot.com", "66.35.250.150");
        st.put("www.whitehouse.gov", "204.153.49.136");
        st.put("www.espn.com", "199.181.132.250");
        st.put("www.snopes.com", "66.165.133.65");
        st.put("www.movies.com", "199.181.132.250");
        st.put("www.cnn.com", "64.236.16.20");
        st.put("www.iitb.ac.in", "202.68.145.210");


        StdOut.println(st.get("www.cs.princeton.edu"));
        StdOut.println(st.get("www.harvardsucks.com"));
        StdOut.println(st.get("www.simpsons.com"));
        StdOut.println();

        StdOut.println("integrity check: " + st.check());
        StdOut.println();

        StdOut.println("ceiling(www.simpsonr.com) = " + st.ceiling("www.simpsonr.com"));
        StdOut.println("ceiling(www.simpsons.com) = " + st.ceiling("www.simpsons.com"));
        StdOut.println("ceiling(www.simpsont.com) = " + st.ceiling("www.simpsont.com"));

        StdOut.println("ceiling(www.simpsonr.com) = " + st.ceiling2("www.simpsonr.com"));
        StdOut.println("ceiling(www.simpsons.com) = " + st.ceiling2("www.simpsons.com"));
        StdOut.println("ceiling(www.simpsont.com) = " + st.ceiling2("www.simpsont.com"));
        StdOut.println();

        for (int i = 0; i < st.size(); i++) {
            StdOut.println(i + "th: key  " + st.select(i));
        }
        StdOut.println();

        StdOut.println("min key: " + st.min());
        StdOut.println("max key: " + st.max());
        StdOut.println("size:    " + st.size());
        StdOut.println("height:  " + st.height());
        StdOut.println();
    }
}
