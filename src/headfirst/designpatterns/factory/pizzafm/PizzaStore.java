package headfirst.designpatterns.factory.pizzafm;

public abstract class PizzaStore {

    public Pizza orderPizaa(String type){
        Pizza pizza;
        pizza = createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }

    abstract Pizza createPizza(String type);

}
