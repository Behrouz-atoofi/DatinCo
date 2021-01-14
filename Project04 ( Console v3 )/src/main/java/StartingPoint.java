import exception.AccNotFoundException;
import exception.AmountException;
import exception.NotMatchAccException;
import exception.DivisibleException;
import service.Generator;
import service.Operator;

import java.util.List;


public class StartingPoint {


    public static void main(String[] args) throws InterruptedException, AccNotFoundException, NotMatchAccException, DivisibleException, AmountException {



        List<String> deposits = Generator.generateDeposits(100000);
        List<String> payments = Generator.generatePayments(500);
        Operator.payment(deposits, payments,500);






    }


}
