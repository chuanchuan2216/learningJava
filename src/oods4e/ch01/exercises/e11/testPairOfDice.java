package oods4e.ch01.exercises.e11;

public class testPairOfDice {
    public static void main(String[] args) {
        PairOfDice test = new PairOfDice();
        int numRoll = 0;
        int countWin = 0;
        do{
            numRoll++;
            test.roll();
            int temp = test.value();
            if(temp==7||temp==11){
                countWin++;
            }else if(temp==4||temp==5||temp==6||temp==8||temp==9||temp==10){
                do {
                    numRoll++;
                    test.roll();
                    if(test.value()==7){
                        break;
                    }
                    if(test.value()==temp){
                        countWin++;
                    }
                }while (temp!=test.value());
            }
        }while (numRoll<100000);

        System.out.println((float) countWin/(float) numRoll);
    }
}
