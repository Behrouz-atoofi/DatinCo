package service;

import dto.Deposit;
import dto.Report;
import exception.AmountException;
import org.apache.log4j.Logger;
import sun.rmi.runtime.Log;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Operator {

    static Logger log = Logger.getLogger(Generator.class.getName());

    public static void payment(List<String> deposits, List<String> payments) {

        List<String> tempList = new ArrayList<>();
        List<String> reportList = new ArrayList<>();
        String[] debtorDeposit = deposits.get(0).split("\t");
        String[] debtorTrans = payments.get(0).split("\t");


        Runnable paymentThread = () -> {


            try {

                if (!checkDebtorAmount(deposits, payments))
                    throw new AmountException("Not Enough Money for processing this transaction");

                else {

                    for (int i = 1; i < deposits.size(); i++) {
                        String[] spitedTLine = payments.get(i).split("\t");
                        String[] spitedALine = deposits.get(i).split("\t");
                        BigDecimal updatedAmount = BigDecimal.valueOf(Integer.parseInt(spitedALine[1]) + Integer.parseInt(spitedTLine[2]));

                        Deposit updatedRep = new Deposit();
                        updatedRep.setDepositNumber(spitedALine[0]);
                        updatedRep.setAmount(updatedAmount);
                        tempList.add(updatedRep.toString());

                        Report report = new Report();
                        report.setSrcDepositNumber(debtorDeposit[0]);
                        report.setDstDepositNumber(spitedTLine[1]);
                        report.setAmount(Integer.parseInt(spitedTLine[2]));
                        reportList.add(report.toString());
                    }
                }
            } catch (AmountException e) {
                log.warn("not enough money");
                System.out.println(e.getMessage());
            }

        };

        Runnable updateDepositThread = () -> {
            // Update deposit Amount
            Deposit updatedDebtor = new Deposit();
            updatedDebtor.setDepositNumber(debtorDeposit[0]);
            updatedDebtor.setAmount(BigDecimal.valueOf(Integer.parseInt(debtorDeposit[1]) - Integer.parseInt(debtorTrans[2])));
            tempList.add(0, updatedDebtor.toString());

            updateDepositFile(tempList);
            buildReportFile(reportList);
        };

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(paymentThread);
        executor.submit(updateDepositThread);
        executor.shutdown();
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
        String[] paymentLine ;

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
