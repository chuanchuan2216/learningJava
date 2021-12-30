package headfirst.designpatterns.ducks;

public class Squack implements QuackBehavior{

    @Override
    public void quack() {
        System.out.println("Squeak");
    }
}
