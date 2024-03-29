package algs4.searching.structures;

import support.Stdlib.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeSet;

public class SET<Key extends Comparable<Key>> implements Iterable<Key> {
    private TreeSet<Key> set;

    public SET() {
        set = new TreeSet<>();
    }

    public SET(SET<Key> x) {
        set = new TreeSet<>(x.set);
        ;
    }

    public void add(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("调用add()时没有给定键key");
        }
        set.add(key);
    }

    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("调用contains()时没有给定键key");
        }
        return set.contains(key);
    }

    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("调用delete()时没有给定键key");
        }
        set.remove(key);
    }

    public void remove(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("调用remove()时没有给定键key");
        }
        set.remove(key);
    }

    public int size() {
        return set.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Iterator<Key> iterator() {
        return set.iterator();
    }

    public Key max() {
        if (isEmpty()) {
            throw new NoSuchElementException("不能在表为空调用max()时");
        }
        return set.first();
    }

    public Key min() {
        if (isEmpty()) {
            throw new NoSuchElementException("不能在表为空调用min()时");
        }
        return set.last();
    }

    public Key ceiling(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("调用ceiling()时没有给定键key");
        }
        Key k = set.ceiling(key);
        if (k == null) {
            throw new NoSuchElementException("没有“大于等于”键key：" + key + "的键");
        }
        return k;
    }

    public Key floor(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("调用floor()时没有给定键key");
        }
        Key k = set.floor(key);
        if (k == null) {
            throw new NoSuchElementException("没有“小于等于”键key：" + key + "的键");
        }
        return k;
    }

    public SET<Key> union(SET<Key> that) {
        if (that == null) {
            throw new IllegalArgumentException("调用union()时没有给定集合that");
        }
        SET<Key> c = new SET<>();
        for (Key x : this) {
            c.add(x);
        }
        for (Key x : that) {
            c.add(x);
        }
        return c;
    }

    public SET<Key> intersects(SET<Key> that) {
        if (that == null) {
            throw new IllegalArgumentException("调用intersects()时没有给定集合that");
        }
        SET<Key> c = new SET<>();
        if (this.size() < that.size()) {
            for (Key x : this) {
                if (that.contains(x)) {
                    c.add(x);
                }
            }
        } else {
            for (Key x : that) {
                if (this.contains(x)) {
                    c.add(x);
                }
            }
        }
        return c;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }
        SET that = (SET) other;
        return this.set.equals(that.set);
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("不支持hashCode()，因为集合是可变的");
    }

    @Override
    public String toString() {
        String s = set.toString();
        return "{ " + s.substring(1, s.length() - 1) + " }";
    }

    public static void main(String[] args) {
        SET<String> set = new SET<String>();
        StdOut.println("set = " + set);

        set.add("www.cs.princeton.edu");
        set.add("www.cs.princeton.edu");
        set.add("www.princeton.edu");
        set.add("www.math.princeton.edu");
        set.add("www.yale.edu");
        set.add("www.amazon.com");
        set.add("www.simpsons.com");
        set.add("www.stanford.edu");
        set.add("www.google.com");
        set.add("www.ibm.com");
        set.add("www.apple.com");
        set.add("www.slashdot.com");
        set.add("www.whitehouse.gov");
        set.add("www.espn.com");
        set.add("www.snopes.com");
        set.add("www.movies.com");
        set.add("www.cnn.com");
        set.add("www.iitb.ac.in");


        StdOut.println(set.contains("www.cs.princeton.edu"));
        StdOut.println(!set.contains("www.harvardsucks.com"));
        StdOut.println(set.contains("www.simpsons.com"));
        StdOut.println();

        StdOut.println("ceiling(www.simpsonr.com) = " + set.ceiling("www.simpsonr.com"));
        StdOut.println("ceiling(www.simpsons.com) = " + set.ceiling("www.simpsons.com"));
        StdOut.println("ceiling(www.simpsont.com) = " + set.ceiling("www.simpsont.com"));
        StdOut.println("floor(www.simpsonr.com)   = " + set.floor("www.simpsonr.com"));
        StdOut.println("floor(www.simpsons.com)   = " + set.floor("www.simpsons.com"));
        StdOut.println("floor(www.simpsont.com)   = " + set.floor("www.simpsont.com"));
        StdOut.println();

        StdOut.println("set = " + set);
        StdOut.println();

        for (String s : set) {
            StdOut.println(s);
        }

        StdOut.println();
        SET<String> set2 = new SET<String>(set);
        StdOut.println(set.equals(set2));
    }
}
