package service;

import dto.Deposit;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dto.*;

public class Generator {

    static Logger log = Logger.getLogger(Generator.class.getName());

    // This method generates values for Accounts and transactions
    // the value that will be generated for debtor account and  debtor transaction is between 500_000 and 10_000_000
    // and for creditor accounts is between 100 and 5000
    public static BigDecimal generateRandomValue(int n) {
        Random r = new Random();
        int low;
        int high;
        BigDecimal randomValue;
        if (n == 0) {
            low = 1000;
            high = 10_000;
            randomValue = BigDecimal.valueOf((Math.round((r.nextInt(high - low) + low) / 100)) * 100);
        } else {
            low = 5_000_000;
            high = 100_000_000;
            randomValue = BigDecimal.valueOf((Math.round((r.nextInt(high - low) + low) / 10000)) * 10000);
        }
        return randomValue;
    }

    public static List<String> generateDeposits(int n) {
        BasicConfigurator.configure();
        Path depositFile = Paths.get("src/main/resources/", "deposits.txt");

        Deposit debtorDeposit = new Deposit();
        debtorDeposit.setDepositNumber("1.10.100.1");
        debtorDeposit.setAmount(Generator.generateRandomValue(1));

        try {
            Files.write(depositFile,(debtorDeposit.toString()+"\n").getBytes()) ;
        } catch (IOException e) {
            log.warn("debtor Deposit couldn't write ");
        }



        for (int i = 1; i < n; i++) {

            Deposit creditorDeposit = new Deposit();
            creditorDeposit.setDepositNumber(buildDepositAccNumber(i));
            creditorDeposit.setAmount(generateRandomValue(0));

            try {
                Files.write(depositFile,(creditorDeposit.toString()+"\n").getBytes() , StandardOpenOption.APPEND) ;
            } catch (IOException e) {
                log.warn("creditorDeposit couldn't write");
            }

        }

        List<String> depositList = null ;
        try {
            depositList = Files.readAllLines(depositFile) ;
        } catch (IOException e) {
            log.warn(" depositFile couldn't read ");
        }


        return depositList;
    }

    public static List<String> generatePayments(int n) {

        List<String> paymentList = new ArrayList<>();

        BigDecimal totalTransactions = BigDecimal.valueOf(0);

        for (int i = 1; i < n; i++) {

            BigDecimal randomValue = generateRandomValue(0);
            Payment payment = new Payment();
            payment.setType(Payment.DepositType.creditor);
            payment.setDepositNumber(buildDepositAccNumber(i));
            payment.setAmount(randomValue);
            paymentList.add(payment.toString());
            totalTransactions = totalTransactions.add(randomValue);

        }
        log.info("Payments generated successfully");

        Payment debtorTransaction = new Payment();
        debtorTransaction.setType(Payment.DepositType.debtor);
        debtorTransaction.setDepositNumber("1.10.100.1");
        debtorTransaction.setAmount(totalTransactions);
        paymentList.add(0, debtorTransaction.toString());
        log.info("DebtorTransaction calculated based on total Transactions");

        Path transactionFile = Paths.get("src/main/resources", "transactions.txt");
        try {
            Files.write(transactionFile, paymentList);
        } catch (IOException e) {
            e.getMessage();
            log.warn("TransactionFile couldn't write");
        }
        return paymentList;
    }

    public static String buildDepositAccNumber(int i) {

        String accountNumber ;
        int tempNum = (int) Math.floor(i / 1000);
        int fnum = (i - tempNum * 1000);
        accountNumber = "1.20." + (100 + tempNum) + "." + (fnum);

        return accountNumber;

    }

}
