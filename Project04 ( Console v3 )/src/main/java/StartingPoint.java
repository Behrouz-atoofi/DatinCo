import service.Generator;
import service.Operator;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static service.Generator.*;


public class StartingPoint {


    public static void main(String[] args) {


        List<String> deposits = Generator.generateDeposits(301) ;
        List<String> payments = Generator.generatePayments(301) ;
       Operator.payment(deposits,payments);




    }


}
