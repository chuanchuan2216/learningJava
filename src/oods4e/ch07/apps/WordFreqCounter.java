package oods4e.ch07.apps;

import oods4e.ch07.trees.BSTInterface;
import oods4e.ch07.trees.BinarySearchTree;
import support.WordFreq;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class WordFreqCounter {
    public static void main(String[] args) throws IOException {
        String word;
        WordFreq wordToTry;
        WordFreq wordInTree;

        BSTInterface<WordFreq> tree;
        tree = new BinarySearchTree<WordFreq>();

        int numWords = 0;
        int numValidWords = 0;
        int numValidFreqs = 0;
        int minSize;
        int minFreq;
        int treeSize;

        Scanner scan = new Scanner(System.in);

        String fn= "src/support/input/Shakespeare.txt";
        Scanner wordsIn = new Scanner(new FileReader(fn));
        wordsIn.useDelimiter("[^a-zA-Z']+");

        System.out.print("单词字母的最小个数为：");
        minSize = scan.nextInt();
        System.out.print("单词出现的最小次数为：");
        minFreq = scan.nextInt();

        while (wordsIn.hasNext()) {
            word = wordsIn.next();
            numWords++;
            if (word.length() >= minSize) {
                numValidWords++;
                word = word.toLowerCase();
                wordToTry = new WordFreq(word);
                wordInTree = tree.get(wordToTry);
                if (wordInTree != null)
                    wordInTree.inc();
                else {
                    wordToTry.inc();
                    tree.add(wordToTry);
                }
            }
        }

        System.out.println("在分析的文件中，");
        System.out.println("至少包含" + minSize + "个字母，");
        System.out.println("至少出现" + minFreq + "次的单词有：");
        System.out.println();
        System.out.println("次数 单词");
        System.out.println("---- ----------");
        for (WordFreq wordFromTree : tree) {
            if (wordFromTree.getFreq() >= minFreq) {
                numValidFreqs++;
                System.out.println(wordFromTree);
            }
        }
        System.out.println();
        System.out.println("在分析的文件中，");
        System.out.println("共计" + numWords + "个单词，其中，");
        System.out.println("至少包含" + minSize + "个字母的单词有" + numValidWords + "个，");
        System.out.println("至少出现" + minFreq + "次的单词有" + numValidFreqs + "个。");
    }
}
