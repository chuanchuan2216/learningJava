package headfirst.designpatterns.singleton.stat;

public class Singleton {
    private static Singleton uniqueInstance = new Singleton();

    private Singleton() {
    }

    public static Singleton getUniqueInstance() {
        return uniqueInstance;
    }

    public String getDescription() {
        return "I'm a statically initialized Singleton";
    }
}
