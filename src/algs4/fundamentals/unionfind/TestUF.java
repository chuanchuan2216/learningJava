package algs4.fundamentals.unionfind;

import support.Stdlib.StdIn;
import support.Stdlib.StdOut;

public class TestUF {
    private  TestUF(){}

    public static void main(String[] args) {
        int n = StdIn.readInt();
        UnionByRankUF unionByRankUf = new UnionByRankUF(n);
        while (!StdIn.isEmpty()){
            int p = StdIn.readInt();
            int q =StdIn.readInt();
            if(unionByRankUf.find(p)== unionByRankUf.find(q)){
                continue;
            }
            unionByRankUf.union(p,q);
            StdOut.println(p+" "+q);
        }
        StdOut.println("连通分量个数："+unionByRankUf.count());
    }
}
