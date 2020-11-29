package service;

import dto.AccountVo;
import dto.ReportVo;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Operator {

    static Logger log = Logger.getLogger(Generator.class.getName());

    public static List<String> payment(List<String> accounts, List<String> transactions)  {


        List<String> tempList = new ArrayList<>();
        List<String> reportList = new ArrayList<>();
        String[] debtorAcc = accounts.get(0).split("\t");
        String[] debtorTrans = transactions.get(0).split("\t") ;

        if (checkDebtorBalance(accounts,transactions) ) {

        for (int i = 1; i < accounts.size(); i++) {
            String[] spitedTLine = transactions.get(i).split("\t");
            String[] spitedALine = accounts.get(i).split("\t");
            int updatedBalance = Integer.parseInt(spitedALine[1]) + Integer.parseInt(spitedTLine[2]);

            AccountVo updatedAcc = new AccountVo();
            updatedAcc.setAccountNumber(spitedALine[0]);
            updatedAcc.setBalance(updatedBalance);
            tempList.add(updatedAcc.toString());

            ReportVo report = new ReportVo();
            report.setSrcAccount(debtorAcc[0]);
            report.setDstAccount(spitedTLine[1]);
            report.setTransValue(Integer.parseInt(spitedTLine[2]));
            reportList.add(report.toString());
        }
            AccountVo updatedDebtor = new AccountVo();
            updatedDebtor.setAccountNumber(debtorAcc[0]);
            updatedDebtor.setBalance(Integer.parseInt(debtorAcc[1]) - Integer.parseInt(debtorTrans[2] ));
            tempList.add(0,updatedDebtor.toString());


            Path reportFile = Paths.get("src/main/resources/", "report.txt");
            try {
                Files.write(reportFile, reportList);
            } catch (IOException e) {
                e.getMessage();
                log.warn("reportFile couldn't write ");
            }

            Path tempFile = Paths.get("src/main/resources/", "temp.txt");
            try {
                Files.write(tempFile, tempList);
            } catch (IOException e) {
                e.getMessage();
                log.warn("reportFile couldn't write ");
            }


            // Update accountFile
            Path accountFile = Paths.get("src/main/resources/","accounts.txt") ;
            updateAccountFile(accountFile,tempFile);

        }

        return tempList;

    }

    public static boolean checkDebtorBalance(List<String> accounts, List<String> transactions) {

        String[] debtorAcc = accounts.get(0).split("\t");
        String[] debtorTrans = transactions.get(0).split("\t");
        int debtorBalance = Integer.parseInt(debtorAcc[1]);
        int transValue = Integer.parseInt(debtorTrans[2]);

        if (debtorBalance > transValue) {
            return true;
        } else
            log.info(" not enough balance ");
        return false;

    }

    public static void updateAccountFile (Path accounts , Path tempFile)  {

        try {
            Files.delete(accounts);
        } catch (IOException e) {
             e.getMessage() ;
             log.warn("accountFile couldn't delete ");
        }

        try {
            Files.move(tempFile, tempFile.resolveSibling(accounts.getFileName()));
        } catch (IOException e) {
            e.getMessage();
            log.warn("tempFile couldn't replace");
        }

    }
}
