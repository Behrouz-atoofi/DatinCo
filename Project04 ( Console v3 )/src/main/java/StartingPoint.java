import service.Operator;

import static service.Generator.*;


public class StartingPoint {


    public static void main(String[] args) {

        Operator.payment(generateDeposits(1000), generatePayments(1000));

    }


}
