package algs4.fundamentals.unionfind;

import support.Stdlib.StdIn;
import support.Stdlib.StdOut;

public class UnionByRankUF implements UFInterface {
    private int[] parent;
    private byte[] rank;
    private int count;

    public UnionByRankUF(int n){
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        count = n;
        parent = new int[n];
        rank = new byte[n];
        for (int i = 0; i < n; i++) {
            parent[i]= i;
            rank[i]=0;
        }
    }

    @Override
    public int find(int p){
        validate(p);
        while (p!=parent[p]){
            parent[p]=parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    @Override
    public int count(){
        return count;
    }

    @Override
    @Deprecated
    public boolean connected(int p, int q){
        return find(p)==find(q);
    }

    @Override
    public void union(int p, int q){
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP==rootQ){
            return;
        }
        if (rank[rootP]<rank[rootQ]){
            parent[rootP] =rootQ;
        } else if (rank[rootP] > rank[rootQ]) {
            parent[rootQ] = rootP;
        }else{
            parent[rootQ] =rootP;
            rank[rootP]++;
        }
        count--;
    }

    private void validate(int p){
        int n = parent.length;
        if(p<0||p>=n){
            throw new IllegalArgumentException("索引值："+p+"不在[0 - "+(n-1)+"]之间");
        }
    }
}
