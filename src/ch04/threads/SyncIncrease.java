package ch04.threads;

public class SyncIncrease implements Runnable {

    private SyncCounter sc;
    private int amount;


    public SyncIncrease(SyncCounter sc, int amount) {
        this.sc = sc;
        this.amount = amount;
    }


    @Override
    public void run() {
        for (int i = 1; i <= amount; i++) {
            sc.increment();
        }
    }
}
