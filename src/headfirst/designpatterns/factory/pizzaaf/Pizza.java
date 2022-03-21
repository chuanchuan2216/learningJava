package headfirst.designpatterns.factory.pizzaaf;

import java.util.ArrayList;

public abstract class Pizza {
    String name;
    Dough dough;
    Sauce sauce;
    Veggies veggies[];
    Cheese cheese;
    Pepperoni pepperoni;
    Clams clam;

    ArrayList toppings = new ArrayList();

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

//    void prepare(){
//        System.out.println("Preparing "+name);
//        System.out.println("Tossing dough...");
//        System.out.println("Adding sauce...");
//        System.out.println("Adding toppings:");
//        for (int i = 0; i < toppings.size(); i++) {
//            System.out.println(" "+toppings.get(i));
//        }
//    }
    abstract void prepear();

    void bake(){
        System.out.println("Bake for 25 minutes at 350");
    }

    void cut(){
        System.out.println("Cutting the pizza int diagonal slices");
    }

    void box(){
        System.out.println("Place pizza in official PizzaStore box");
    }

}
