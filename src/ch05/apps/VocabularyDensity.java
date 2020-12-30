package ch05.apps;

import java.io.*;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.Scanner;

import ch05.collections.*;
import ch06.lists.SortedABList;
import ch07.trees.BinarySearchTree;

public class VocabularyDensity {

    public static void main(String[] args) throws IOException {

        final long startTime = System.currentTimeMillis();

        final int CAPACITY = 1000;
        String fname = "src/input/Shakespeare.txt";
        //String fname = "src/input/glossary.txt";
        String word;
        int numWords = 0;
        int uniqWords;
        double density;

        CollectionInterface<String> words;
        words = new ArrayCollection<String>(30000);
        //words = new SortedArrayCollection<String>(CAPACITY);
        //words = new LinkedCollection<String>();

        //Collection words = new ArrayList<String>();

        FileReader fin = new FileReader(fname);
        Scanner wordsIn = new Scanner(fin);
        wordsIn.useDelimiter("[^a-zA-Z']+");

        while (wordsIn.hasNext()) {
            word = wordsIn.next();
            word = word.toLowerCase();
            if (!words.contains(word)) {
                words.add(word);
            }
            numWords++;
        }

        density = (double) numWords / words.size();
        System.out.println("分析文件" + fname);
        System.out.println("\n\t总单词数：" + numWords);
        if (words.size() == CAPACITY) {
            System.out.println("\t单词个数（至少）：" + words.size());
        } else {
            System.out.println("\t单词个数：" + words.size());
            System.out.printf("\n\t词汇密度：%.2f", density);
        }

        final long endTime = System.currentTimeMillis();

        long total = endTime - startTime;
        System.out.println("\n\t耗时（毫秒）: " + total);

    }

}
