package oods4e.ch01.exercises.e13;

public class Polynomial {
    protected int[] coef;//系数
    protected int[] expn;//指数
    protected int index;

    public Polynomial(int numExpn) {
        coef = new int[numExpn];
        expn = new int[numExpn];
        index = 0;
    }

    public void setCoefficient(int inExpn, int inCoef) {
        coef[index] = inCoef;
        expn[index] = inExpn;
        index++;
    }

    public double evaluate(double x) {
        double result = 0.0;
        for (int i = 0; i < index; i++) {
            result += coef[i] * Math.pow(x, (double) expn[i]);
        }
        return result;
    }
}
