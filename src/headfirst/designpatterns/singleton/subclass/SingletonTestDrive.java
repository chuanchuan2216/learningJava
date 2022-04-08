package headfirst.designpatterns.singleton.subclass;

public class SingletonTestDrive {
    public static void main(String[] args) {
        Singleton foo = CoolerSingleton.getUniqueInstance();
        Singleton bar = HotterSingleton.getUniqueInstance();
        System.out.println(foo);
        System.out.println(bar);
    }
}
