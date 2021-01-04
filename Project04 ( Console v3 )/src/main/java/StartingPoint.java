import service.Generator;
import service.Operator;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static service.Generator.*;


public class StartingPoint {


    public static void main(String[] args) {


        List<String> deposits = Generator.generateDeposits(3000) ;
        List<String> payments = Generator.generatePayments(3000) ;
       Operator.payment(deposits,payments);




    }


}
