package algs4.fundamentals;

import support.Stdlib.StdOut;
import support.Stdlib.StdRandom;

public class Counter implements Comparable<Counter> {
    private final String name;  //计数器名称
    private int count = 0;      //当前计数

    /**
     * 创建一个名称为id的计数器
     *
     * @param id 计数器名称
     */
    public Counter(String id) {
        this.name = id;
    }

    /**
     * 将计数器的值加1
     */
    public void increment() {
        count++;
    }

    /**
     * 返回该对象创建之后计数器被加1的次数
     *
     * @return 该对象创建之后计数器被加1的次数
     */
    public int tally() {
        return count;
    }

    @Override
    public String toString() {
        return count + " " + name;
    }

    @Override
    public int compareTo(Counter that) {
        if (that.count < this.count) {
            return -1;
        } else if (that.count > this.count) {
            return +1;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trails = Integer.parseInt(args[1]);

        Counter[] hits = new Counter[n];
        for (int i = 0; i < n; i++) {
            hits[i] = new Counter("counter" + i);
        }

        for (int t = 0; t < trails; t++) {
            hits[StdRandom.uniform(n)].increment();
        }

        for (int i = 0; i < n; i++) {
            StdOut.println(hits[i]);
        }
    }
}
