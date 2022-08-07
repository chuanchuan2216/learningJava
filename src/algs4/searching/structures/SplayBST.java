package algs4.searching.structures;

import support.Stdlib.StdOut;

public class SplayBST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value val;

        private Node left, right;

        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
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
        root = splay(root, key);

        int cmp = key.compareTo(root.key);
        if (cmp == 0) {
            return root.val;
        } else {
            return null;
        }
    }

    /**
     * 将键值对存入符号表中，若值为null则将键key删除
     *
     * @param key 键key
     * @param val 值val
     */
    public void put(Key key, Value val) {
        if (root == null) {
            root = new Node(key, val);
            return;
        }

        root = splay(root, key);

        int cmp = key.compareTo(root.key);
        if (cmp < 0) {
            Node n = new Node(key, val);
            n.left = root.left;
            n.right = root;
            root.left = null;
            root = n;
        } else if (cmp > 0) {
            Node n = new Node(key, val);
            n.right = root.right;
            n.left = root;
            root.right = null;
            root = n;
        } else {
            root.val = val;
        }
    }

    /**
     * 从符号表中删除键key及其对应的值
     *
     * @param key 键key
     */
    public void remove(Key key) {
        if (root == null) {
            return;
        }

        root = splay(root, key);

        int cmp = key.compareTo(root.key);
        if (cmp == 0) {
            if (root.left == null) {
                root = root.right;
            } else {
                Node x = root.right;
                root = root.left;
                splay(root, key);
                root.right = x;
            }
        }
    }


    private Node splay(Node h, Key key) {
        if (h == null) {
            return null;
        }

        int cmp1 = key.compareTo(h.key);
        if (cmp1 < 0) {
            if (h.left == null) {
                return h;
            }

            int cmp2 = key.compareTo(h.left.key);
            if (cmp2 < 0) {
                h.left.left = splay(h.left.left, key);
                h = rotateRight(h);
            } else if (cmp2 > 0) {
                h.left.right = splay(h.left.right, key);
                if (h.left.right != null) {
                    h.left = rotateLeft(h.left);
                }
            }

            if (h.left == null) {
                return h;
            } else {
                return rotateRight(h);
            }
        } else if (cmp1 > 0) {
            if (h.right == null) {
                return h;
            }

            int cmp2 = key.compareTo(h.right.key);
            if (cmp2 < 0) {
                h.right.left = splay(h.right.left, key);
                if (h.right.left != null) {
                    h.right = rotateRight(h.right);
                }
            } else if (cmp2 > 0) {
                h.right.right = splay(h.right.right, key);
                h = rotateLeft(h);
            }

            if (h.right == null) {
                return h;
            } else {
                return rotateLeft(h);
            }
        } else {
            return h;
        }
    }

    /**
     * 返回BST的高度
     *
     * @return BST的高度
     */
    public int height() {
        return height(root);
    }

    /**
     * 返回以结点x为根结点的BST的高度
     *
     * @param x 结点x
     * @return
     */
    private int height(Node x) {
        if (x == null) {
            return -1;
        }
        return 1 + Math.max(height(x.left), height(x.right));
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
            return 1 + size(x.left) + size(x.right);
        }
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
        return x;
    }

    public static void main(String[] args) {
        SplayBST<Integer, Integer> st1 = new SplayBST<Integer, Integer>();
        st1.put(5, 5);
        st1.put(9, 9);
        st1.put(13, 13);
        st1.put(11, 11);
        st1.put(1, 1);


        SplayBST<String, String> st = new SplayBST<String, String>();
        st.put("www.cs.princeton.edu", "128.112.136.11");
        st.put("www.cs.princeton.edu", "128.112.136.12");
        st.put("www.cs.princeton.edu", "128.112.136.13");
        st.put("www.princeton.edu", "128.112.128.15");
        st.put("www.yale.edu", "130.132.143.21");
        st.put("www.simpsons.com", "209.052.165.60");
        StdOut.println("The size 0 is: " + st.size());
        st.remove("www.yale.edu");
        StdOut.println("The size 1 is: " + st.size());
        st.remove("www.princeton.edu");
        StdOut.println("The size 2 is: " + st.size());
        st.remove("non-member");
        StdOut.println("The size 3 is: " + st.size());
        StdOut.println(st.get("www.cs.princeton.edu"));
        StdOut.println("The size 4 is: " + st.size());
        StdOut.println(st.get("www.yale.com"));
        StdOut.println("The size 5 is: " + st.size());
        StdOut.println(st.get("www.simpsons.com"));
        StdOut.println("The size 6 is: " + st.size());
        StdOut.println();
    }
}
