package algs4.searching;

import algs4.searching.structures.BST;
import algs4.searching.structures.RedBlackBST;
import support.Stdlib.StdOut;

public class TestBST {
    public static void main(String[] args) {
        String test = "S E A R C H E X A M P L E";
        String[] keys = test.split(" ");
        int n = keys.length;
        BST<String ,Integer> st = new BST<>();
        //RedBlackBST<String ,Integer> st = new RedBlackBST<>();
        for (int i = 0; i < n; i++) {
            st.put(keys[i],i );
        }

        StdOut.println("测试 size()、min()、max()");
        StdOut.println("--------------------------------");
        StdOut.println("size = "+st.size());
        StdOut.println("min = "+st.min());
        StdOut.println("max = "+st.max());
        StdOut.println();

        StdOut.println("测试 keys()");
        StdOut.println("--------------------------------");
        for (String s : st.keys()){
            StdOut.println(s + " " + st.get(s));
        }
        StdOut.println();

        StdOut.println("测试 select()");
        StdOut.println("--------------------------------");
        for (int i = 0; i < st.size(); i++){
            StdOut.println(i + " " + st.select(i));
        }
        StdOut.println();

        StdOut.println("测试 key()、rank()、floor()、floor2()、ceil()");
        StdOut.println("key rank floor floor2 ceil");
        StdOut.println("-------------------------");
        for (char i = 'A'; i <= 'X'; i++) {
            String s = i + "";
            StdOut.printf("%2s %4d %4s %4s %4s\n", s, st.rank(s), st.floor(s), st.floor2(s), st.ceiling(s));
        }
        StdOut.println();

        StdOut.println("测试范围查找");
        StdOut.println("-------------------------");
        String[] from = { "A", "Z", "X", "0", "B", "C" };
        String[] to   = { "Z", "A", "X", "Z", "G", "L" };
        for (int i = 0; i < from.length; i++) {
            StdOut.printf("%s-%s (%2d) : ", from[i], to[i], st.size(from[i], to[i]));
            for (String s : st.keys(from[i], to[i])){
                StdOut.print(s + " ");
            }
            StdOut.println();
        }
        StdOut.println();

        for (int i = 0; i < st.size() / 2; i++) {
            st.deleteMin();
        }
        StdOut.println("删除最小的" + st.size() / 2 + "个键之后：");
        StdOut.println("--------------------------------");
        for (String s : st.keys()){
            StdOut.println(s + " " + st.get(s));
        }
        StdOut.println();

        while (!st.isEmpty()) {
            st.delete(st.select(st.size() / 2));
        }
        StdOut.println("删除其余的键之后：");
        StdOut.println("--------------------------------");
        for (String s : st.keys()){
            StdOut.println(s + " " + st.get(s));
        }
        StdOut.println();

        for (int i = 0; i < n; i++){
            st.put(keys[i], i);
        }
        StdOut.println("重新添加键值对之后：");
        StdOut.println("--------------------------------");
        for (String s : st.keys()){
            StdOut.println(s + " " + st.get(s));
        }
        StdOut.println();
    }
}
