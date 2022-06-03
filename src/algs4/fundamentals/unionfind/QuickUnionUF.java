package algs4.fundamentals.unionfind;

public class QuickUnionUF implements UFInterface{
    private int[] parent;
    private int count;

    public QuickUnionUF(int n) {
        parent = new int[n];
        count = n;
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    @Override
    public int find(int p) {
        validate(p);
        while (p!=parent[p]){
            p = parent[p];
        }
        return p;
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public boolean connected(int p, int q) {
        return find(p)==find(q);
    }

    @Override
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if(rootP==rootQ){
            return;
        }
        parent[rootP] = rootQ;
        count--;
    }

    private void validate(int p){
        int n = parent.length;
        if(p<0||p>=n){
            throw new IllegalArgumentException("索引值："+p+"不在[0 - "+(n-1)+"]之间");
        }
    }
}
