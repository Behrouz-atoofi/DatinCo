import service.Generator;
import service.Operator;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class StartingPoint {


    public static void main(String[] args) throws InterruptedException {


        List<String> deposits = Generator.generateDeposits(1000);
        List<String> payments = Generator.generatePayments(1000);
        Operator.payment(deposits, payments,25);


    }


}
