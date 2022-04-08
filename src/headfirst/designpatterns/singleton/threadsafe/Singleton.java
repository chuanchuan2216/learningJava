package headfirst.designpatterns.singleton.threadsafe;

public class Singleton {
    private static Singleton uniqueInstance;

    private Singleton() {
    }

    // synchronized 同步导致性能开销大
    // 只有第一次执行此方法时才需要同步，后续皆为多余
    public static synchronized Singleton getUniqueInstance() {
        if (uniqueInstance == null) {
            System.out.println("Creating unique instance");
            uniqueInstance = new Singleton();
        }
        System.out.println("Returning unique instance");
        return uniqueInstance;
    }
}
