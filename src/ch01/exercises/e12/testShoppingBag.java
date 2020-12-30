package ch01.exercises.e12;

public class testShoppingBag {
    public static void main(String[] args) {
        ShoppingBag myBag = new ShoppingBag(0.06);
        myBag.place(5,10.5);
        myBag.place(2,2.07);
        System.out.println(myBag);
    }
}
