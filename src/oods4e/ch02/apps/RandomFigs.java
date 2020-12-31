package oods4e.ch02.apps;

import oods4e.ch02.figures.Circle;
import oods4e.ch02.figures.FigureInterface;
import oods4e.ch02.figures.Rectangle;

import java.text.DecimalFormat;
import java.util.Random;

public class RandomFigs {
    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("#.##");
        Random rand = new Random();
        final int COUNT = 5;
        double totalArea = 0;

        FigureInterface[] figures = new FigureInterface[COUNT];

        for (int i = 0; i < COUNT; i++) {
            switch (rand.nextInt(2)) {
                case 0:
                    figures[i] = new Circle(1.0);
                    System.out.println("Circle area is 3.14");
                    break;
                case 1:
                    figures[i] = new Rectangle(1.0, 2.0);
                    System.out.println("Rectangle area is 2");
                    break;
            }
            totalArea += figures[i].area();
        }

        System.out.println("Total area is " + totalArea);

    }
}
