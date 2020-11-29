package service;

import dto.AccountDto;
import dto.ReportDto;
import org.apache.log4j.BasicConfigurator;
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

        if (checkBalance(accounts,transactions) ) {

        for (int i = 1; i < accounts.size(); i++) {
            String[] spitedTLine = transactions.get(i).split("\t");
            String[] spitedALine = accounts.get(i).split("\t");
            int updatedBalance = Integer.parseInt(spitedALine[1]) + Integer.parseInt(spitedTLine[2]);

            AccountDto updatedAcc = new AccountDto();
            updatedAcc.setAccountNumber(spitedALine[0]);
            updatedAcc.setBalance(updatedBalance);
            tempList.add(updatedAcc.toString());

            ReportDto report = new ReportDto();
            report.setSrcAccount(debtorAcc[0]);
            report.setDstAccount(spitedTLine[1]);
            report.setTransValue(Integer.parseInt(spitedTLine[2]));
            reportList.add(report.toString());
        }
            AccountDto updatedDebtor = new AccountDto();
            updatedDebtor.setAccountNumber(debtorAcc[0]);
            updatedDebtor.setBalance(Integer.parseInt(debtorAcc[1]) - Integer.parseInt(debtorTrans[2] ));
            tempList.add(0,updatedDebtor.toString());


            Path reportFile = Paths.get("src/main/resources/", "report.txt");
            try {
                Files.write(reportFile, reportList);
            } catch (IOException e) {
                e.getMessage();
            }

            Path tempFile = Paths.get("src/main/resources/", "temp.txt");
            try {
                Files.write(tempFile, tempList);
            } catch (IOException e) {
                e.getMessage();
            }

        }

        return tempList;

    }

    public static boolean checkBalance(List<String> accounts, List<String> transactions) {

        String[] debtorAcc = accounts.get(0).split("\t");
        String[] debtorTrans = transactions.get(0).split("\t");
        int debtorBalance = Integer.parseInt(debtorAcc[1]);
        int transValue = Integer.parseInt(debtorTrans[2]);

        if (debtorBalance > transValue) {
            log.info(" enough balance ");
            return true;
        } else
            log.info(" not enough balance ");
        return false;

    }
}
