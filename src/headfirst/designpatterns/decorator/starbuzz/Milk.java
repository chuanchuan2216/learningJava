package headfirst.designpatterns.decorator.starbuzz;

public class Milk extends Condiment{
    Beverage beverage;

    public Milk(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double cost() {
        return beverage.cost()+0.10;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription()+",Milk";
    }
}
