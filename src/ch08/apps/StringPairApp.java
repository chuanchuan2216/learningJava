package ch08.apps;

import ch08.maps.ArrayListMap;
import ch08.maps.MapInterface;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class StringPairApp {
    public static void main(String[] args) throws IOException {
        //MapInterface<String,String> pairs;
        MapInterface<String, String> pairs;
        pairs = new ArrayListMap<String, String>();

        //String fname = "src/input/glossary.txt";
        String fname = "src/input/periodic.txt";
        FileReader fin = new FileReader(fname);
        Scanner info = new Scanner(fin);
        //info.useDelimiter("[#\\n\\r]");
        info.useDelimiter("[#\\n\\r]+");//原书错误，不加+无法匹配

        String keyInfo = info.next();
        String valueInfo = info.next();
        info.nextLine();

        String key, value;
        while (info.hasNext()) {
            key = info.next();
            value = info.next();
            pairs.put(key, value);
        }

        Scanner scan = new Scanner(System.in);
        final String STOP = "XX";
        key = null;
        while (!STOP.equals(key)) {
            System.out.println("\n请输入要查看的" + keyInfo + "（输入" + STOP + "停止）：");
            //key = scan.next();
            key = scan.nextLine();//原书错误，不然无法获取到输入的空格
            if (!STOP.equals(key))
                if (pairs.contains(key))
                    System.out.println(valueInfo + "\t" + pairs.get(key));
                else
                    System.out.println("\t未找到有效信息。");
        }
    }
}
