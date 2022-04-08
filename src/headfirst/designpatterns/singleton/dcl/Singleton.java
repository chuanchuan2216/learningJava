package headfirst.designpatterns.singleton.dcl;

public class Singleton {
    // volatile
    // 确保当uniqueInstance被初始化为单件实例时
    // 多个线程正确处理
    private volatile static Singleton uniqueInstance;

    private Singleton(){}

    public static Singleton getUniqueInstance() {
        if(uniqueInstance == null){
            synchronized (Singleton.class){
                if (uniqueInstance == null){
                    System.out.println("Creating unique instance");
                    uniqueInstance = new Singleton();
                }
            }
        }
        System.out.println("Returning unique instance");
        return uniqueInstance;
    }
}
