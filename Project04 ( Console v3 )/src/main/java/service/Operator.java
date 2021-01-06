package service;

import dto.Deposit;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class Operator {

    static Logger log = Logger.getLogger(Generator.class.getName());

    public static void payment(List<String> deposits, List<String> payments, int thread) throws InterruptedException {


        int depositSize = deposits.size();

        if ((depositSize - 1) % thread != 0) {
            log.warn("The number of thread should be divisible for deposit size");
            log.info("application stopped...");
            System.exit(0);
        } else if (deposits.size() != payments.size()) {
            log.warn("The size of deposits should be equals to payments");
            log.info("application stopped...");
            System.exit(0);
        } else if (!checkDebtorAmount(deposits, payments)) {
            log.warn("Not enough money for processing this transaction");
            log.info("application stopped...");
            System.exit(0);
        }


        List<String> paymentList = new ArrayList<>();
        List<String> reportList = new ArrayList<>();
        int divided = depositSize / thread;
        CThread cThread = new CThread();

        if (thread > 1) {

            ExecutorService executor = Executors.newSingleThreadExecutor();

            for (int i = 0; i < thread; i++) {
                int finalI = i;
                executor.execute(() -> paymentList.addAll(cThread.processPayment(deposits, payments, divided * finalI + 1, divided * (finalI + 1))));
                executor.execute(() -> reportList.addAll(cThread.processReport(deposits, payments, divided * finalI + 1, divided * (finalI + 1))));
            }
            executor.awaitTermination(1, TimeUnit.SECONDS);
            executor.shutdown();

        } else {
            paymentList.addAll(cThread.processPayment(deposits, payments, 1, depositSize - 1));
            reportList.addAll(cThread.processReport(deposits, payments, 1, depositSize - 1));
        }

        // Update deposit Amount
        String[] debtorDeposit = deposits.get(0).split("\t");
        String[] debtorTrans = payments.get(0).split("\t");
        Deposit updatedDebtor = new Deposit();
        updatedDebtor.setDepositNumber(debtorDeposit[0]);
        updatedDebtor.setAmount(BigDecimal.valueOf(Integer.parseInt(debtorDeposit[1]) - Integer.parseInt(debtorTrans[2])));
        paymentList.add(0, updatedDebtor.toString());



        updateDepositFile(paymentList);
        buildReportFile(reportList);

    }

    public static void updateDepositFile(List<String> tempList) {

        // create tempFile
        Path tempFile = Paths.get("src/main/resources/", "temp.txt");
        try {
            Files.write(tempFile, tempList);
        } catch (IOException e) {
            e.getMessage();
            log.warn("tempFile couldn't write ");
        }

         // Update DepositFile
        Path depositFile = Paths.get("src/main/resources/", "deposits.txt");
        try {
            Files.delete(depositFile);
        } catch (IOException e) {
            e.getMessage();
            log.warn("depositFile couldn't delete ");
        }

        try {
            Files.move(tempFile, tempFile.resolveSibling(depositFile.getFileName()));
        } catch (IOException e) {
            e.getMessage();
            log.warn("tempFile couldn't replace");
        }

    }

    public static void buildReportFile(List<String> reportList) {

        Path reportFile = Paths.get("src/main/resources/", "report.txt");
        try {
            Files.write(reportFile, reportList);
        } catch (IOException e) {
            e.getMessage();
            log.warn("reportFile couldn't write ");
        }

    }

    public static boolean checkDebtorAmount(List<String> deposits, List<String> payments) {

        String[] depositLine;
        String[] paymentLine;

        for (int i = 0; i < deposits.size(); i++) {
            depositLine = deposits.get(i).split("\t");
            paymentLine = payments.get(i).split("\t");

            if ("1.10.100.1".equals(depositLine[0]) && "debtor".equals(paymentLine[0])) {
                BigDecimal debtorBalance = BigDecimal.valueOf(Long.parseLong(depositLine[1]));
                BigDecimal transValue = BigDecimal.valueOf(Long.parseLong(paymentLine[2]));
                int res = debtorBalance.compareTo(transValue);

                if (res == 1 || res == 0)
                    return true;
            }

        }
        return false;

    }

}
