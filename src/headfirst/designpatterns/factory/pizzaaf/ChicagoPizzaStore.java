package headfirst.designpatterns.factory.pizzaaf;

public class ChicagoPizzaStore extends PizzaStore {
    @Override
    Pizza createPizza(String type) {
        Pizza pizza = null;
        PizzaIngredientFactory ingredientFactory = new ChicagoPizzaIngredientFactory();


        switch (type) {
            case "cheese":
                pizza = new CheesePizza(ingredientFactory);
                pizza.setName("Chicago Style Cheese Pizza");
                break;
            case "pepperoni":
                pizza = new PepperoniPizza(ingredientFactory);
                pizza.setName("Chicago Style Pepperoni Pizza");
                break;
            case "clam":
                pizza = new ClamPizza(ingredientFactory);
                pizza.setName("Chicago Style Clam Pizza");
                break;
            case "veggie":
                pizza = new VeggiePizza(ingredientFactory);
                pizza.setName("Chicago Style Veggie Pizza");
                break;
        }
        return pizza;
    }
}
