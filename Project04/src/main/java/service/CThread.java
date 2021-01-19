package service;

import dto.Deposit;
import dto.Payment;
import dto.Report;
import exception.NotMatchAccException;
import org.apache.log4j.Logger;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class CThread {


    List<Report> reportList = new ArrayList<>();
    List<Deposit> newDepositList ;
    static Logger log = Logger.getLogger(Generator.class.getName());

    public synchronized void process(List<Deposit> deposits, List<Payment> payments) throws NotMatchAccException, IOException {

        newDepositList  = deposits ;

        int debtorIndex = getDebtorIndex(deposits,payments) ;

        for (int i = 0; i < payments.size() ; i++) {
            Payment payment = payments.get(i) ;

            for (int j = 0; j < deposits.size(); j++) {
                Deposit deposit =  deposits.get(j) ;

                if (payment.getType().equals(Payment.DepositType.creditor)) {

                    if (payment.getDepositNumber().equals(deposit.getDepositNumber())) {

                        // new balance for creditor Deposit
                        BigDecimal ncBalance = BigDecimal.valueOf(0);
                        ncBalance = ncBalance.add(payment.getAmount());
                        ncBalance = ncBalance.add(deposit.getAmount());

                        // make new object for creditorDeposit
                        Deposit nDeposit = new Deposit();
                        nDeposit.setDepositNumber(deposit.getDepositNumber());
                        nDeposit.setAmount(ncBalance);
                        newDepositList.set(j, nDeposit);

                        // calc new balance for debtor Deposit
                        BigDecimal ndBalance = BigDecimal.valueOf(0);
                        ndBalance = ndBalance.add(deposits.get(debtorIndex).getAmount());
                        ndBalance = ndBalance.subtract(payment.getAmount());

                        // make new object for debtorDeposit
                        Deposit nDebtorDeposit = new Deposit();
                        nDebtorDeposit.setDepositNumber(deposit.getDepositNumber());
                        nDebtorDeposit.setAmount(ndBalance);
                        newDepositList.set(debtorIndex, nDebtorDeposit);

                        // make an object for reportList
                        Report report = new Report();
                        report.setSrcDepositNumber(deposits.get(debtorIndex).getDepositNumber());
                        report.setDstDepositNumber(payment.getDepositNumber());
                        report.setAmount(payment.getAmount());
                        reportList.add(report);

                        // UPDATE FILES IN A SINGLE THREAD . IF the app crashed or server Downed, the last changes stores ...
                        buildReportFile(reportList);
                        updateDepositFile(newDepositList);

                    }
                }
//                } else if (payment.getType().equals(Payment.DepositType.debtor)) {
//                    if (payment.getDepositNumber().equals(deposit.getDepositNumber())) {
//
//                        BigDecimal ndBalance = BigDecimal.valueOf(0);
//                        ndBalance = ndBalance.add(deposits.get(debtorIndex).getAmount());
//                        ndBalance = ndBalance.subtract(payment.getAmount());
//
//                        Deposit nDebtorDeposit = new Deposit();
//                        nDebtorDeposit.setDepositNumber(deposit.getDepositNumber());
//                        nDebtorDeposit.setAmount(ndBalance);
//                        newDepositList.set(j,nDebtorDeposit) ;
//
//                        buildReportFile(reportList);
//                        updateDepositFile(newDepositList);
//
//                    }
//                } else {
//                    throw  new NotMatchAccException("The payment file contains Row or incorrect data") ;
//                }

            }




        }

    }









    public int getDebtorIndex (List<Deposit> deposits ,List<Payment> payments) {

        String debtorAcc = null ;
        int index =0 ;
        for (int i = 0 ; i< payments.size();i++) {
            Payment payment = payments.get(i) ;
            if (payment.getType().toString().equals("debtor")) {
                debtorAcc = payment.getDepositNumber();
            }
        }
        for (int i = 0 ; i< deposits.size();i++) {
            Deposit deposit = deposits.get(i) ;
            if (deposit.getDepositNumber().equals(debtorAcc)) {
                index = i ;
            }
        }
        return index ;


    }

    public static void updateDepositFile(List<Deposit> depositList) throws IOException {

        // create tempFile
        Path tempFile = Paths.get("src/main/resources/", "temp.txt");
        if (Files.exists(tempFile))
            Files.delete(tempFile);
            Files.createFile(tempFile) ;
        for (Deposit deposit : depositList) {

            try {
                Files.write(tempFile,(deposit.toString()+ "\n" ).getBytes(),StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.getMessage();
                log.warn("PaymentFile couldn't write");
            }
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

    public static void buildReportFile(List<Report> reportList) throws IOException {

        Path reportFile = Paths.get("src/main/resources/", "report.txt");
        if (Files.exists(reportFile))
            Files.delete(reportFile);
        Files.createFile(reportFile) ;
        for (Report report : reportList) {

            try {
                Files.write(reportFile,(report.toString()+ "\n" ).getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.getMessage();
                log.warn("PaymentFile couldn't write");
            }
        }

    }

}
