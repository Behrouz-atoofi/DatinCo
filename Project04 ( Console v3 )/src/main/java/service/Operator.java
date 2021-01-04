package service;

import dto.Deposit;
import dto.Report;
import exception.AmountException;
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

    public static void payment(List<String> deposits, List<String> payments) {

        List<String> tempList1 = new ArrayList<>();
        List<String> tempList2 = new ArrayList<>();
        List<String> tempList3 = new ArrayList<>();

        List<String> reportList1 = new ArrayList<>();
        List<String> reportList2 = new ArrayList<>();
        List<String> reportList3 = new ArrayList<>();

        String[] debtorDeposit = deposits.get(0).split("\t");
        String[] debtorTrans = payments.get(0).split("\t");

        int size = deposits.size();
        int divided = (int) Math.floor(size / 3);
        System.out.println(divided);


        Runnable paymentThread1 = () -> {

            for (int i = 1; i <= divided; i++) {
                String[] transactionSLine = payments.get(i).split("\t");
                String[] accountSLine = deposits.get(i).split("\t");
                BigDecimal updatedAmount = BigDecimal.valueOf(Integer.parseInt(accountSLine[1]) + Integer.parseInt(transactionSLine[2]));

                Deposit updatedDep = new Deposit();
                updatedDep.setDepositNumber(accountSLine[0]);
                updatedDep.setAmount(updatedAmount);
                tempList1.add(updatedDep.toString());

                Report report = new Report();
                report.setSrcDepositNumber(debtorDeposit[0]);
                report.setDstDepositNumber(transactionSLine[1]);
                report.setAmount(Integer.parseInt(transactionSLine[2]));
                reportList1.add(report.toString());

            }

        };
        Runnable paymentThread2 = () -> {

            for (int i = divided + 1; i <= divided * 2; i++) {
                String[] transactionSLine = payments.get(i).split("\t");
                String[] accountSLine = deposits.get(i).split("\t");
                BigDecimal updatedAmount = BigDecimal.valueOf(Integer.parseInt(accountSLine[1]) + Integer.parseInt(transactionSLine[2]));

                Deposit updatedDep = new Deposit();
                updatedDep.setDepositNumber(accountSLine[0]);
                updatedDep.setAmount(updatedAmount);
                tempList2.add(updatedDep.toString());

                Report report = new Report();
                report.setSrcDepositNumber(debtorDeposit[0]);
                report.setDstDepositNumber(transactionSLine[1]);
                report.setAmount(Integer.parseInt(transactionSLine[2]));
                reportList2.add(report.toString());


            }
        };
        Runnable paymentThread3 = () -> {

            for (int i = divided * 2 + 1; i < size; i++) {
                String[] transactionSLine = payments.get(i).split("\t");
                String[] accountSLine = deposits.get(i).split("\t");
                BigDecimal updatedAmount = BigDecimal.valueOf(Integer.parseInt(accountSLine[1]) + Integer.parseInt(transactionSLine[2]));

                Deposit updatedDep = new Deposit();
                updatedDep.setDepositNumber(accountSLine[0]);
                updatedDep.setAmount(updatedAmount);
                tempList3.add(updatedDep.toString());

                Report report = new Report();
                report.setSrcDepositNumber(debtorDeposit[0]);
                report.setDstDepositNumber(transactionSLine[1]);
                report.setAmount(Integer.parseInt(transactionSLine[2]));
                reportList3.add(report.toString());

            }
        };

        try {

            if (!checkDebtorAmount(deposits, payments))
                throw new AmountException("Not Enough Money for processing this transaction");

            else {
                ExecutorService executor = Executors.newFixedThreadPool(3);

                executor.execute(paymentThread1);
                executor.execute(paymentThread2);
                executor.execute(paymentThread3);

                executor.awaitTermination(2, TimeUnit.SECONDS);
                executor.shutdown();

            }

        } catch (AmountException | InterruptedException e) {
            log.warn("not enough money");
            System.out.println(e.getMessage());
        }


        List<String> tempList = new ArrayList<>();
        tempList.addAll(tempList1);
        tempList.addAll(tempList2);
        tempList.addAll(tempList3);

        List<String> reportList = new ArrayList<>();
        reportList.addAll(reportList1);
        reportList.addAll(reportList2);
        reportList.addAll(reportList3);

        // Update deposit Amount
        Deposit updatedDebtor = new Deposit();
        updatedDebtor.setDepositNumber(debtorDeposit[0]);
        updatedDebtor.setAmount(BigDecimal.valueOf(Integer.parseInt(debtorDeposit[1]) - Integer.parseInt(debtorTrans[2])));
        tempList.add(0, updatedDebtor.toString());


        updateDepositFile(tempList);
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
