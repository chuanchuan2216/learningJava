package headfirst.designpatterns.singleton.dcl;

public class SingletonClient {
    public static void main(String[] args) {
        Singleton singleton = Singleton.getUniqueInstance();
        Singleton singleton1 = Singleton.getUniqueInstance();
    }
}
