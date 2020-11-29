
import java.io.IOException;
import service.Operator;

import static service.Generator.*;

public class StartingPoint {


    public static void main(String[] args) throws IOException {



        Operator.payment(generateAccounts(20),generateTransactions(20)) ;



    }





}
