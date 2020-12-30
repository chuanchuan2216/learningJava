package support;

import java.util.Random;

public class CustomerGenerator {

    protected int minIAT;
    protected int maxIAT;

    protected int minST;
    protected int maxST;

    protected int currTime = 0;

    Random rand = new Random();

    public CustomerGenerator(int minIAT, int maxIAT, int minST, int maxST) {
        super();
        this.minIAT = minIAT;
        this.maxIAT = maxIAT;
        this.minST = minST;
        this.maxST = maxST;
    }

    public void reset() {
        currTime = 0;
    }

    public Customer nextCustomer() {
        int IAT;
        int ST;

        IAT = minIAT + rand.nextInt(maxIAT - minIAT + 1);
        ST = minST + rand.nextInt(maxST - minST + 1);

        currTime += IAT;

        Customer next = new Customer(currTime, ST);

        return next;
    }

}
