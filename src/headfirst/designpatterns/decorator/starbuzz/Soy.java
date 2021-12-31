package headfirst.designpatterns.decorator.starbuzz;

public class Soy extends Condiment{
    Beverage beverage;

    @Override
    public double cost() {
        return beverage.cost()+0.15;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription()+",Soy";
    }
}
