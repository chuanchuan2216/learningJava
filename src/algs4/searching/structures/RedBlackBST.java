package algs4.searching.structures;

import algs4.fundamentals.structures.Queue;
import support.Stdlib.StdIn;
import support.Stdlib.StdOut;

import java.util.NoSuchElementException;

public class RedBlackBST<Key extends Comparable<Key>, Value> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private boolean color;
        private int size;

        public Node(Key key, Value val, boolean color, int size) {
            this.key = key;
            this.val = val;
            this.color = color;
            this.size = size;
        }
    }

    /**
     * 创建一个空的有序符号表（基于红黑二叉查找树）
     */
    public RedBlackBST() {
    }

    /**
     * 返回结点x与父节点之间的链接是否为红链接
     *
     * @param x 结点x
     * @return 结点x与父节点之间的链接为红链接返回true，否则或者结点x为null返回false
     */
    private boolean isRed(Node x) {
        if (x == null) {
            return false;
        }
        return x.color == RED;
    }

    /**
     * 判断符号表表是否为空
     *
     * @return 符号表为空返回true，否则返回false
     */
    public boolean isEmpty() {
        return size() == 0;
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
        }
        return x.size;
    }

    /**
     * 判断键key是否存在于符号表中
     *
     * @param key 键key
     * @return 键key存在于符号表中返回true，否则返回false
     */
    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("调用contains()时没有给定键key");
        }
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
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                x = x.right;
            } else {
                return x.val;
            }
        }
        return null;
    }

    /**
     * 将键值对存入符号表中，若值为null则将键key删除
     *
     * @param key 键key
     * @param val 值val
     */
    public void put(Key key, Value val) {
        if (key == null) {
            throw new IllegalArgumentException("调用put()时没有给定键key");
        }
        if (val == null) {
            delete(key);
            return;
        }
        root = put(root, key, val);
        root.color = BLACK;
        assert check();
    }

    /**
     * 如果键key存在于以结点h为根结点的子树中，用值val更新它的值，否则以键key和值val创建一个新结点。
     *
     * @param h   结点h
     * @param key 键key
     * @param val 值val
     * @return 更新后的结点x或者创建的新结点
     */
    private Node put(Node h, Key key, Value val) {
        if (h == null) {
            return new Node(key, val, RED, 1);
        }

        int cmp = key.compareTo(h.key);
        if (cmp < 0) {
            h.left = put(h.left, key, val);
        } else if (cmp > 0) {
            h.right = put(h.right, key, val);
        } else {
            h.val = val;
        }

        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }

        h.size = size(h.left) + size(h.right) + 1;
        return h;
    }

    /**
     * 从符号表中删除最小的键及其关联的值
     */
    public void deleteMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("符号表为空，deleteMin()操作下溢");
        }
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }
        root = deleteMin(root);
        if (!isEmpty()) {
            root.color = BLACK;
        }
    }


    private Node deleteMin(Node h) {
        if (h.left == null) {
            return null;
        }
        if (!isRed(h.left) && !isRed(h.right)) {
            h = moveRedLeft(h);
        }
        h.left = deleteMin(h.left);
        return balance(h);
    }

    /**
     * 从符号表中删除最大的键及其关联的值
     */
    public void deleteMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("符号表为空，deleteMax()操作下溢");
        }
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }
        root = deleteMax(root);
        if (!isEmpty()) {
            root.color = BLACK;
        }
    }

    private Node deleteMax(Node h) {
        if (h.right == null) {
            return null;
        }
        if (!isRed(root.left) && !isRed(root.right)) {
            h = moveRedRight(h);
        }
        h.right = deleteMax(h.right);
        return balance(h);
    }

    /**
     * 从符号表中删除键key及其对应的值
     *
     * @param key 键key
     */
    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("调用delete()时没有给定键key");
        }
        if (!contains(key)) {
            return;
        }

        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }
        root = delete(root, key);
        if (!isEmpty()) {
            root.color = BLACK;
        }
    }

    private Node delete(Node h, Key key) {
        if (key.compareTo(h.key) < 0) {
            if (!isRed(h.left) && !isRed(h.left.left)) {
                h = moveRedLeft(h);
            }
            h.left = delete(h.left, key);
        } else {
            if (isRed(h.left)) {
                h = rotateRight(h);
            }
            if (key.compareTo(h.key) == 0 && (h.right == null)) {
                return null;
            }
            if (!isRed(h.right) && !isRed(h.right.left)) {
                h = moveRedRight(h);
            }
            if (key.compareTo(h.key) == 0) {
                Node x = min(h.right);
                h.key = x.key;
                h.val = x.val;
                h.right = deleteMin(h.right);
            } else {
                h.right = delete(h.right, key);
            }
        }
        return balance(h);
    }

    /**
     * 返回将结点h的左链接进行右旋转之后的结点h
     *
     * @param h 结点h
     * @return 将结点h的左链接进行右旋转之后的结点h
     */
    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;

        x.color = x.right.color;
        x.right.color = RED;

        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;

        return x;
    }

    /**
     * 返回将结点h的右链接进行左旋转之后的结点h
     *
     * @param h 结点h
     * @return 将结点h的右链接进行左旋转之后的结点h
     */
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;

        x.color = x.left.color;
        x.left.color = RED;

        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;

        return x;
    }

    /**
     * 将结点h的两个子结点链接的颜色进行转换
     *
     * @param h 结点h
     */
    private void flipColors(Node h) {
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    /**
     * 假设结点h是红色并且h.left和 h.left.left都是黑色的，则将h.left或其子结点设为红色。
     *
     * @param h 结点h
     * @return 转换之后的结点h
     */
    private Node moveRedLeft(Node h) {
        flipColors(h);
        if (isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    /**
     * 假设结点h是红色并且h.right和h.right.left都是黑色的，则将h.right或其子结点设为红色。
     *
     * @param h 结点h
     * @return 转换之后的结点h
     */
    private Node moveRedRight(Node h) {
        flipColors(h);
        if (isRed(h.left.left)) {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }

    private Node balance(Node h) {
        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }

        h.size = size(h.left) + size(h.right) + 1;
        return h;
    }

    /**
     * 返回符号表中最小的键key
     *
     * @return 表中最小的键key
     */
    public Key min() {
        if (isEmpty()) {
            throw new NoSuchElementException("符号表为空，min()无法找到最小键");
        }
        return min(root).key;
    }

    /**
     * 返回以结点x为根结点的左子树中含有最小键的结点
     *
     * @param x 结点x
     * @return 以结点x为根结点的左子树中含有最小键的结点
     */
    private Node min(Node x) {
        if (x.left == null) {
            return x;
        } else {
            return min(x.left);
        }
    }

    /**
     * 返回符号表中最大的键key
     *
     * @return 表中最大的键key
     */
    public Key max() {
        if (isEmpty()) {
            throw new NoSuchElementException("符号表为空，max()无法找到最大键");
        }
        return max(root).key;
    }

    /**
     * 返回以结点x为根结点的右子树中含有最大键的结点
     *
     * @param x 结点x
     * @return 以结点x为根结点的右子树中含有最大键的结点
     */
    private Node max(Node x) {
        if (x.right == null) {
            return x;
        } else {
            return max(x.right);
        }
    }

    /**
     * 返回“小于等于”键key的键（向下取整）
     *
     * @param key 键key
     * @return “小于等于”键key的键
     */
    public Key floor(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("调用floor()时没有给定键key");
        }
        if (isEmpty()) {
            throw new NoSuchElementException("符号表为空，floor()无法找到大于给定键key的键");
        }
        Node x = floor(root, key);
        if (x == null) {
            throw new NoSuchElementException("floor()给定键key太小");
        } else {
            return x.key;
        }
    }

    /**
     * 返回以结点x为根结点的左子树中“小于等于”键key的键
     *
     * @param x   结点x
     * @param key 键key
     * @return 以结点x为根结点的左子树中“小于等于”键key的键
     */
    private Node floor(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        }
        if (cmp < 0) {
            floor(x.left, key);
        }
        Node t = floor(x.right, key);
        if (t != null) {
            return t;
        } else {
            return x;
        }
    }

    public Key floor2(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("调用floor2()时没有给定键key");
        }
        Key x = floor2(root, key, null);
        if (x == null) {
            throw new NoSuchElementException("floor2()给定键key太小");
        } else {
            return x;
        }
    }

    private Key floor2(Node x, Key key, Key best) {
        if (x == null) {
            return best;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return floor2(x.left, key, best);
        } else if (cmp > 0) {
            return floor2(x.right, key, x.key);
        } else {
            return x.key;
        }
    }

    /**
     * 返回“大于等于”键key的键（向上取整）
     *
     * @param key 键key
     * @return “大于等于”键key的键
     */
    public Key ceiling(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("调用ceiling()时没有给定键key");
        }
        if (isEmpty()) {
            throw new NoSuchElementException("符号表为空，ceiling()无法找到小于给定键key的键");
        }
        Node x = ceiling(root, key);
        if (x == null) {
            throw new NoSuchElementException("ceiling()给定键key太大");
        } else {
            return x.key;
        }
    }

    /**
     * 返回以结点x为根结点的右子树中“大于等于”键key的键
     *
     * @param x   结点x
     * @param key 键key
     * @return 以结点x为根结点的右子树中“大于等于”键key的键
     */
    private Node ceiling(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        }
        if (cmp < 0) {
            Node t = ceiling(x.left, key);
            if (t != null) {
                return t;
            } else {
                return x;
            }
        }
        return ceiling(x.right, key);
    }

    /**
     * 返回此符号表中的排序rank的键
     *
     * @param rank 排序rank
     * @return 此符号表中的排序rank的键
     */
    public Key select(int rank) {
        if (rank < 0 || rank >= size()) {
            throw new IllegalArgumentException("调用select()时传递了错误的参数：" + rank);
        }
        return select(root, rank);
    }

    /**
     * 返回以结点x为根结点的子树中排序rank的键
     *
     * @param x    结点x
     * @param rank 排序rank
     * @return 以结点x为根结点的子树中排序rank的键
     */
    private Key select(Node x, int rank) {
        if (x == null) {
            return null;
        }
        int leftSize = size(x.left);
        if (leftSize > rank) {
            return select(x.left, rank);
        } else if (leftSize < rank) {
            return select(x.right, rank - leftSize - 1);
        } else {
            return x.key;
        }
    }

    /**
     * 返回符号表中所有比键key小的键的个数
     *
     * @param key 键key
     * @return 符号表中所有比键key小的键的个数
     */
    public int rank(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("调用rank()时没有给定键key");
        }
        return rank(root, key);
    }

    /**
     * 返回以结点x为根结点的子树中所有比键key小的键的个数
     *
     * @param x   结点x
     * @param key 键key
     * @return 以结点x为根结点的子树中所有比键key小的键的个数
     */
    private int rank(Node x, Key key) {
        if (x == null) {
            return 0;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return rank(x.left, key);
        } else if (cmp > 0) {
            return 1 + size(x.left) + rank(x.right, key);
        } else {
            return size(x.left);
        }
    }

    /**
     * 返回符号表中的所有键
     *
     * @return 表中的所有键
     */
    public Iterable<Key> keys() {
        if (isEmpty()) {
            return new Queue<>();
        }
        return keys(min(), max());
    }

    /**
     * 返回符号表中键lo到键hi范围内的所有键
     *
     * @param lo 键lo
     * @param hi 键hi
     * @return 符号表中键lo到键hi范围内的所有键
     */
    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) {
            throw new IllegalArgumentException("调用keys()时没有给定第一个参数：起点键key");
        }
        if (hi == null) {
            throw new IllegalArgumentException("调用keys()时没有给定第二个参数：终点键key");
        }
        Queue<Key> queue = new Queue<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    /**
     * 将符号表中以结点x为根结点的子树中键lo到键hi范围内的所有键保存到队列queue中
     *
     * @param x     结点x
     * @param queue 队列queue
     * @param lo    键lo
     * @param hi    键hi
     */
    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) {
            return;
        }
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) {
            keys(x.left, queue, lo, hi);
        }
        if (cmplo <= 0 && cmphi >= 0) {
            queue.enqueue(x.key);
        }
        if (cmphi > 0) {
            keys(x.right, queue, lo, hi);
        }
    }

    /**
     * 返回符号表中指定范围内的键的个数
     *
     * @param lo 范围的起点lo
     * @param hi 范围的终点hi
     * @return 符号表中指定范围内的键的个数
     */
    public int size(Key lo, Key hi) {
        if (lo == null) {
            throw new IllegalArgumentException("调用size()时没有给定第一个参数：起点键key");
        }
        if (hi == null) {
            throw new IllegalArgumentException("调用size()时没有给定第二个参数：终点键key");
        }
        if (lo.compareTo(hi) > 0) {
            return 0;
        }
        if (contains(hi)) {
            return rank(hi) - rank(lo) + 1;
        } else {
            return rank(hi) - rank(lo);
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

    /**
     * 验证符号表
     *
     * @return 验证通过返回true，否则返回false
     */
    public boolean check() {
        if (!isBST()) {
            StdOut.println("非对称顺序");
        }
        if (!isSizeConsistent()) {
            StdOut.println("子树计数不一致");
        }
        if (!isRankConsistent()) {
            StdOut.println("排序不一致");
        }
        if (!is23()) {
            StdOut.println("非2-3树");
        }
        if (!isBalanced()) {
            StdOut.println("未平衡");
        }
        return isBST() && isSizeConsistent() && isRankConsistent() && is23() && isBalanced();
    }

    private boolean isBST() {
        return isBST(root, null, null);
    }

    private boolean isBST(Node x, Key min, Key max) {
        if (x == null) {
            return true;
        }
        if (min != null && x.key.compareTo(min) <= 0) {
            return false;
        }
        if (max != null && x.key.compareTo(max) >= 0) {
            return false;
        }
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }

    private boolean isSizeConsistent() {
        return isSizeConsistent(root);
    }

    private boolean isSizeConsistent(Node x) {
        if (x == null) {
            return true;
        }
        if (x.size != size(x.left) + size(x.right) + 1) {
            return false;
        }
        return isSizeConsistent(x.left) && isSizeConsistent(x.right);
    }

    private boolean isRankConsistent() {
        for (int i = 0; i < size(); i++) {
            if (i != rank(select(i))) {
                return false;
            }
        }
        for (Key key : keys()) {
            if (key.compareTo(select(rank(key))) != 0) {
                return false;
            }
        }
        return true;
    }

    private boolean is23() {
        return is23(root);
    }

    private boolean is23(Node x) {
        if (x == null) {
            return true;
        }
        if (isRed(x.right)) {
            return true;
        }
        if (x != null && isRed(x) && isRed(x.left)) {
            return false;
        }
        return is23(x.left) && is23(x.right);
    }

    private boolean isBalanced() {
        int black = 0;
        Node x = root;
        while (x != null) {
            if (!isRed(x)) {
                black++;
            }
            x = x.left;
        }
        return isBalanced(root, black);
    }

    private boolean isBalanced(Node x, int black) {
        if (x == null) {
            return black == 0;
        }
        if (!isRed(x)) {
            black--;
        }
        return isBalanced(x.left, black) && isBalanced(x.right, black);
    }

    public static void main(String[] args) {
        RedBlackBST<String, Integer> st = new RedBlackBST<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        StdOut.println();
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
        StdOut.println();
    }
}
