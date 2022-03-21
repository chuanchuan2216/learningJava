package headfirst.designpatterns.factory.pizzaaf;

public class ChicagoPizzaStore extends PizzaStore {
    @Override
    Pizza createPizza(String type) {
        Pizza pizza = null;
        PizzaIngredientFactory pizzaIngredientFactory = new ChicagoPizzaIngredientFactory();


        switch (type) {
            case "cheese":
                pizza = new CheesePizza(type);
                break;
            case "pepperoni":
                pizza = new ChicagoStylePepperoniPizza();
                break;
            case "clam":
                pizza = new ChicagoStyleClamPizza();
                break;
            case "veggie":
                pizza = new ChicagoStyleVeggiePizza();
                break;
        }
        return pizza;
    }
}
