package headfirst.designpatterns.command.remote;

public class Light {

    String localtion = "";

    public Light(String localtion) {
        this.localtion = localtion;
    }

    public void on() {
        System.out.println(localtion + " Light is on");
    }

    public void off() {
        System.out.println(localtion + " Light is off");
    }
}
