package algs4.searching;

import algs4.searching.structures.SET;
import support.Stdlib.In;
import support.Stdlib.StdIn;
import support.Stdlib.StdOut;

public class BlockFilter {
    private BlockFilter() {
    }

    public static void main(String[] args) {
        SET<String> set = new SET<>();

        In in = new In(args[0]);
        while (!in.isEmpty()) {
            String word = in.readString();
            set.add(word);
        }

        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            if (!set.contains(word)) {
                StdOut.println(word);
            }
        }
    }
}
