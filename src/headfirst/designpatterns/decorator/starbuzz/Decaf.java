package headfirst.designpatterns.decorator.starbuzz;

public class Decaf extends Beverage{
    public Decaf() {
        description = "Decat";
    }

    @Override
    public double cost() {
        return 1.05;
    }
}
