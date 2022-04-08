package headfirst.designpatterns.singleton.subclass;

public class Singleton {
    protected static Singleton uniqueInstance;

    protected Singleton(){}

    public static synchronized Singleton getUniqueInstance(){
        if (uniqueInstance == null) {
            System.out.println("Creating unique instance");
            uniqueInstance = new Singleton();
        }
        System.out.println("Returning unique instance");
        return uniqueInstance;
    }
}
