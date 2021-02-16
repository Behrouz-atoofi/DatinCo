import exception.AccNotFoundException;
import exception.AmountException;
import exception.DivisibleException;
import exception.NotMatchAccException;
import service.Generator;
import service.Operator;

import java.io.IOException;


public class StartingPoint {


    public static void main(String[] args) throws InterruptedException, AccNotFoundException, NotMatchAccException, DivisibleException, AmountException, IOException {


        Generator.generateDeposits(20);
        Generator.generatePayments(20);
        Operator.payment(10);
    }


}
