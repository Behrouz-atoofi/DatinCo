package service;

import dto.Deposit;
import dto.Payment;
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

    public static void generateDeposits(int n) throws IOException {

        BasicConfigurator.configure();
        List<Deposit> depositList = new ArrayList<>();

        Deposit debtorDeposit = new Deposit();
        debtorDeposit.setDepositNumber("1.10.100.1");
        debtorDeposit.setAmount(Generator.generateRandomValue(1));
        depositList.add(debtorDeposit);

        for (int i = 1; i < n; i++) {

            Deposit creditorDeposit = new Deposit();
            creditorDeposit.setDepositNumber(buildDepositAccNumber(i));
            creditorDeposit.setAmount(generateRandomValue(0));

            depositList.add(creditorDeposit);

        }
        log.info("DepositsList generated successfully");

        Path depositFile = Paths.get("src/main/resources/", "deposits.txt");

        if (Files.exists(depositFile))
            Files.delete(depositFile);
        Files.createFile(depositFile);


        for (int i = 0; i < depositList.size(); i++) {

            try {
                Files.write(depositFile, (depositList.get(i).toString() + "\n").getBytes(), StandardOpenOption.APPEND);

            } catch (Exception e) {
                log.warn("DepositFile Could't write ...");
            }

        }

    }

    public static void generatePayments(int n) throws IOException {

        List<Payment> paymentList = new ArrayList<>();

        BigDecimal totalPayments = BigDecimal.valueOf(0);

        for (int i = 1; i < n; i++) {

            BigDecimal randomValue = generateRandomValue(0);
            Payment payment = new Payment();
            payment.setType(Payment.DepositType.creditor);
            payment.setDepositNumber(buildDepositAccNumber(i));
            payment.setAmount(randomValue);
            paymentList.add(payment);
            totalPayments = totalPayments.add(randomValue);

        }
        log.info("Payments generated successfully");

        Payment debtorTransaction = new Payment();
        debtorTransaction.setType(Payment.DepositType.debtor);
        debtorTransaction.setDepositNumber("1.10.100.1");
        debtorTransaction.setAmount(totalPayments);
        paymentList.add(0, debtorTransaction);
        log.info("DebtorTransaction calculated based on total Transactions");

        Path paymentFile = Paths.get("src/main/resources", "payments.txt");
        if (Files.exists(paymentFile))
            Files.delete(paymentFile);
        Files.createFile(paymentFile);

        for (int i = 0; i < paymentList.size(); i++) {

            try {
                Files.write(paymentFile, (paymentList.get(i).toString() + "\n").getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.getMessage();
                log.warn("PaymentFile couldn't write");
            }
        }
    }

    public static String buildDepositAccNumber(int i) {

        String accountNumber;
        int tempNum = (int) Math.floor(i / 1000);
        int fNum = (i - tempNum * 1000);
        accountNumber = "1.20." + (100 + tempNum) + "." + (fNum);

        return accountNumber;

    }

}
