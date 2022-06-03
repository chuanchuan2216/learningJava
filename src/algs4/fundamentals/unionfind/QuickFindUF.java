package algs4.fundamentals.unionfind;

public class QuickFindUF implements UFInterface{
    private int[] id;
    private int count;

    public QuickFindUF(int n) {
        count = n;
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i]=i;
        }
    }

    @Override
    public int count(){
        return count;
    }

    @Override
    public int find(int p){
        validate(p);
        return id[p];
    }

    @Override
    @Deprecated
    public boolean connected(int p,int q){
        validate(p);
        validate(q);
        return id[p]==id[q];
    }

    @Override
    public void union(int p,int q){
        validate(p);
        validate(q);
        int pID = id[p];
        int qID = id[q];

        if (pID==qID){
            return;
        }

        for (int i = 0; i < id.length;  i++) {
            if(id[i]==pID){
                id[i]=qID;
            }
        }
        count--;
    }

    private void validate(int p){
        int n = id.length;
        if(p<0||p>=n){
            throw new IllegalArgumentException("索引值："+p+"不在[0 - "+(n-1)+"]之间");
        }
    }
}
