import dto.Deposit;
import dto.Payment;
import exception.AccNotFoundException;
import exception.AmountException;
import exception.NotMatchAccException;
import exception.DivisibleException;
import service.Generator;
import service.Operator;

import java.io.IOException;
import java.util.List;


public class StartingPoint {


    public static void main(String[] args) throws InterruptedException, AccNotFoundException, NotMatchAccException, DivisibleException, AmountException, IOException {



        List<Deposit> deposits = Generator.generateDeposits(8);
        List<Payment> payments = Generator.generatePayments(4);
        //Operator.checkDebtorAmount(deposits,payments);
        Operator.payment(deposits, payments,4);






    }


}
