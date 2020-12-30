package ch01.exercises.e12;

public class ShoppingBag {
    protected int numGoods[];
    protected double goodPrice[];
    protected double tax;

    protected int index;
    protected final static int DEACAP = 100;

    public ShoppingBag(double tax) {
        this.numGoods = new int[DEACAP];
        this.goodPrice = new double[DEACAP];
        this.tax = tax;
        this.index = 0;
    }

    public void place(int inNumGoods,double inGoodPrice){
        numGoods[index] = inNumGoods;
        goodPrice[index] = inGoodPrice;
        index++;
    }

    public double totalCost(){
        double result = 0.0;
        for (int i = 0;i<index;i++){
            result += numGoods[i]*goodPrice[i];
        }
        return result;
    }

    @Override
    public String toString() {
        return "不含税价格为："+this.totalCost()+"\n含税价格为"+this.totalCost()*(1+this.tax);
    }
}
