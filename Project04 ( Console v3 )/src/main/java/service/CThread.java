package service;

import dto.Deposit;
import dto.Report;
import exception.NotMatchAccException;
import org.apache.log4j.Logger;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CThread {


    List<String> reportList = new ArrayList<>();
    List<String> newDepositList ;
    static Logger log = Logger.getLogger(Generator.class.getName());

    public synchronized void process(List<String> deposits, List<String> payments) throws NotMatchAccException {

        newDepositList  = deposits ;
        String[] debtorDeposit = deposits.get(0).split("\t");

        for (int i = 0; i < payments.size() ; i++) {
            String[] paymentSLine = payments.get(i).split("\t");

            for (int j = 0; j < deposits.size(); j++) {
                String[] depositSLine = deposits.get(j).split("\t");

                if ("creditor".equals(paymentSLine[0])) {

                    if (paymentSLine[1].equals(depositSLine[0])) {
                        BigDecimal newAmount = BigDecimal.valueOf(Integer.parseInt(depositSLine[1]) + Integer.parseInt(paymentSLine[2]));

                        Deposit newDeposit = new Deposit();
                        newDeposit.setDepositNumber(depositSLine[0]);
                        newDeposit.setAmount(newAmount);
                        newDepositList.remove(j);
                        newDepositList.add(j, newDeposit.toString());

                        Report report = new Report();
                        report.setSrcDepositNumber(debtorDeposit[0]);
                        report.setDstDepositNumber(paymentSLine[1]);
                        report.setAmount(Integer.parseInt(paymentSLine[2]));
                        reportList.add(report.toString());


                    }

                } else if ("debtor".equals(paymentSLine[0])) {
                    if (paymentSLine[1].equals(depositSLine[0])) {
                        BigDecimal newAmount = BigDecimal.valueOf(Integer.parseInt(depositSLine[1]) - Integer.parseInt(paymentSLine[2]));

                        Deposit newDeposit = new Deposit();
                        newDeposit.setDepositNumber(depositSLine[0]);
                        newDeposit.setAmount(newAmount);
                        newDepositList.remove(j);
                        newDepositList.add(j, newDeposit.toString());


                    }
                } else {
                    throw  new NotMatchAccException("The payment file contains Row or incorrect data") ;
                }

                }




            }
            buildReportFile(reportList);
            updateDepositFile(newDepositList);
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

}
