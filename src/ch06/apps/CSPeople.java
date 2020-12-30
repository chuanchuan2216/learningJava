package ch06.apps;

import ch06.lists.ListInterface;
import ch06.lists.SortedABList;
import support.FamousPerson;

import javax.swing.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class CSPeople {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        int choice;
        System.out.println("1：按照名字排序（name）");
        System.out.println("2：按照生日排序（birth）");
        choice = scan.nextInt();

        SortedABList<FamousPerson> people;

        if (choice == 1)
            people = new SortedABList<FamousPerson>();
        else
            people = new SortedABList<FamousPerson>(FamousPerson.yearOfBirthComparator());

        FileReader fin = new FileReader("src/input/FamousCS.txt");
        Scanner info = new Scanner(fin);
        info.useDelimiter("[,\\n]");
        FamousPerson person;
        String fname, lname, fact;
        int year;

        while (info.hasNext()) {
            fname = info.next();
            lname = info.next();
            year = info.nextInt();
            fact = info.next();
            person = new FamousPerson(fname, lname, year, fact);
            people.add(person);
        }

        System.out.println();
        //for (FamousPerson fp : people)
        //    System.out.println(fp);
        //SortABList未实现Iterable，无法for-each遍历
        for (int i = 0; i < people.size(); i++)
            System.out.println(people.get(i).toString());
    }
}
