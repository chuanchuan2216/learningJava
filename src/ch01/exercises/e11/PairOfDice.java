package ch01.exercises.e11;

import java.util.Random;

public class PairOfDice {
    protected int pointOfDice1;
    protected int pointOfDice2;

    public PairOfDice() {
        this.pointOfDice1 = 0;
        this.pointOfDice2 = 0;
    }

    public void roll() {
        Random rand = new Random();
        this.pointOfDice1 = rand.nextInt(6) + 1;
        this.pointOfDice2 = rand.nextInt(6) + 1;
    }

    public int value() {
        return pointOfDice1 + pointOfDice2;
    }

    @Override
    public String toString() {
        return ("骰子1：" + pointOfDice1 + " - 骰子2：" + pointOfDice2);
    }
}
