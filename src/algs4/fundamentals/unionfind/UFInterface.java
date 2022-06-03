package algs4.fundamentals.unionfind;

public interface UFInterface {
    /**
     * 返回p（0到N-1之间）所在的分量的标识符
     * @param p 分量
     * @return p（0到N-1之间）所在的分量的标识符
     */
    int find(int p);

    /**
     * 返回连通分量的数量
     * @return 连通分量的数量
     */
    int count();

    /**
     * 判断p和q是否存在于同一个连通分量中
     * @param p 分量p
     * @param q 分量q
     * @return 存在于同一个连通分量中返回true，否则返回false
     */
    @Deprecated
    boolean connected(int p, int q);

    /**
     * 在p和q之间添加一个连接
     * @param p 分量p
     * @param q 分量q
     */
    void union(int p, int q);

}
