package headfirst.designpatterns.factory.pizzaaf;

public class PizzaTestDrive {
    public static void main(String[] args) {
        PizzaStore nyStore = new NYPizzaStore();
        PizzaStore chicagoStore = new ChicagoPizzaStore();

        Pizza pizza = nyStore.orderPizaa("cheese");
        System.out.println("Ethan ordered a "+pizza.getName());

        pizza = chicagoStore.orderPizaa("cheese");
        System.out.println("Joel ordered a "+pizza.getName());
    }
}
