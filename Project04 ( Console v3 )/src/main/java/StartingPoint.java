import service.Generator;
import service.Operator;

import java.util.List;

import static service.Generator.*;


public class StartingPoint {


    public static void main(String[] args) {


        List<String> deposits = Generator.generateDeposits(2000) ;
        List<String> payments = Generator.generatePayments(2000) ;
        Operator.payment(deposits,payments);

    }


}
