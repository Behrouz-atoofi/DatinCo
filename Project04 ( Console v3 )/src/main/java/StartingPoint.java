import service.Operator;

import static service.Generator.*;


public class StartingPoint {


    public static void main(String[] args) {

        Operator.payment(generateDeposits(99), generatePayments(99));

    }


}
