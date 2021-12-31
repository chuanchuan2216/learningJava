package headfirst.designpatterns.decorator.starbuzz;

public class Whip extends Condiment{
    Beverage beverage;
    @Override
    public double cost() {
        return beverage.cost()+0.10;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription()+",Whip";
    }
}
