package algs4.searching;

import algs4.searching.structures.SET;
import support.Stdlib.StdIn;
import support.Stdlib.StdOut;

public class Dedup {
    private Dedup(){}

    public static void main(String[] args) {
        SET<String> set = new SET<>();

        while(!StdIn.isEmpty()){
            String key = StdIn.readString();
            if(!set.contains(key)){
                set.add(key);
                StdOut.println(key);
            }
        }
    }
}
