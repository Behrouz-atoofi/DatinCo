package service;

import exception.AccNotFoundException;
import exception.AmountException;
import exception.DivisibleException;
import exception.NotMatchAccException;
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

    public static void payment(List<String> deposits, List<String> payments, int thread) throws InterruptedException, AccNotFoundException, NotMatchAccException, DivisibleException, AmountException {


        int paymentSize = payments.size();

        if ((paymentSize) % thread != 0) {
            log.warn("The number of thread should be divisible for deposit size");
            log.info("application stopped...");
            throw new DivisibleException("The number of thread should be divisible for deposit size");
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
        int divided = paymentSize / thread;

        if (thread > 1) {

            ExecutorService executor = Executors.newSingleThreadExecutor();

            for (int i = 0; i < thread; i++) {

                CThread cThread = new CThread(deposits, payments, divided * i, divided * (i + 1));

                executor.execute(() -> reportList.addAll(cThread.getReportList()));
                executor.execute(() -> paymentList.addAll(cThread.getPaymentList()));

            }
            executor.awaitTermination(1, TimeUnit.SECONDS);
            executor.shutdown();

        } else {
            CThread cThread = new CThread(deposits, payments, 0, paymentSize);
            paymentList.addAll(cThread.getPaymentList());
            reportList.addAll(cThread.getReportList());
        }


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

    public static boolean checkDebtorAmount(List<String> deposits, List<String> payments) throws AccNotFoundException, AmountException {

        String[] depositLine;
        String[] paymentLine;

        BigDecimal debtorAmount = BigDecimal.valueOf(0);
        BigDecimal totalPayment = BigDecimal.valueOf(0);
        String debtorAccount = null;

        for (int i = 0; i < payments.size(); i++) {


            paymentLine = payments.get(i).split("\t");

            if (paymentLine[0].equals("creditor")) {
                totalPayment = totalPayment.add(BigDecimal.valueOf(Long.parseLong(paymentLine[2])));

            } else if (paymentLine[0].equals("debtor")) {
                debtorAmount = debtorAmount.add(BigDecimal.valueOf(Long.parseLong(paymentLine[2])));
                debtorAccount = paymentLine[1];
            }

        }

        if (debtorAccount != null) {

            for (int i = 0; i < deposits.size(); i++) {
                depositLine = deposits.get(i).split("\t");
                if (depositLine[0].equals(debtorAccount)) {
                    BigDecimal debtorBalance = BigDecimal.valueOf(Long.parseLong(depositLine[1]));
                    return debtorBalance.compareTo(debtorAmount) == 1 ||
                            debtorBalance.compareTo(debtorAmount) == 0;

                } else {
                    throw new AmountException("Amount of debtor balance not enough for processing this transaction");
                }

            }
        } else {
            throw new AccNotFoundException("debtor Account not found in payment List ");
        }


        return false;

    }

}
