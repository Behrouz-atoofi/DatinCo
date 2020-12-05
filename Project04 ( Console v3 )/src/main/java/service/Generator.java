package service;

import dto.Deposit;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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


        List<String> depositList = new ArrayList<>();
        Deposit debtorDeposit = new Deposit();
        debtorDeposit.setDeposit("1.10.100.1");
        debtorDeposit.setAmount(Generator.generateRandomValue(1));
        depositList.add(debtorDeposit.toString());
        log.info("Amount generated for debtorDeposit");


        for (int i = 1; i <= n; i++) {

            Deposit creditorAccount = new Deposit();
            creditorAccount.setDeposit(DepositBuilder(i));
            creditorAccount.setAmount(generateRandomValue(0));
            depositList.add(creditorAccount.toString());


        }
        log.info("Amounts generated for all creditorDeposits");

        Path depositFile = Paths.get("src/main/resources/", "depositVO.txt");

        try {
            Files.write(depositFile, depositList);
        } catch (IOException e) {
            e.getMessage();
            log.warn("depositFile couldn't write");
        }


        return depositList;
    }

    public static List<String> generatePayments(int n) {

        List<String> paymentList = new ArrayList<>();

        BigDecimal totalTransactions = BigDecimal.valueOf(0);

        for (int i = 1; i <= n; i++) {

            BigDecimal randomValue = generateRandomValue(0);
            Payment payment = new Payment();
            payment.setType(Payment.DepositType.creditor);
            payment.setDeposit(DepositBuilder(i));
            payment.setAmount(randomValue);
            paymentList.add(payment.toString());
            totalTransactions = totalTransactions.add(randomValue);

        }
        log.info("Payments generated successfully");

        Payment debtorTransaction = new Payment();
        debtorTransaction.setType(Payment.DepositType.debtor);
        debtorTransaction.setDeposit("1.10.100.1");
        debtorTransaction.setAmount(totalTransactions);
        paymentList.add(0, debtorTransaction.toString());
        log.info("DebtorTransaction calculated based on total Transactions");

        Path transactionsVO = Paths.get("src/main/resources", "transactionsVO.txt");
        try {
            Files.write(transactionsVO, paymentList);
        } catch (IOException e) {
            e.getMessage();
            log.warn("TransactionFile couldn't write");
        }
        return paymentList;
    }

    public static String DepositBuilder(int i) {

        String accountNumber = null;
        int tempNum = (int) Math.floor(i / 1000);
        int fnum = (i - tempNum * 1000);
        accountNumber = "1.20." + (100 + tempNum) + "." + (fnum);

        return accountNumber;

    }

}
