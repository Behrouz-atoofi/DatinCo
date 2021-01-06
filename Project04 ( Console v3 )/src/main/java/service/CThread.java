package service;

import dto.Deposit;
import dto.Report;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CThread {


    public synchronized List<String> processPayment(List<String> deposits, List<String> payments, int fromIndex, int toIndex) {

        List<String> paymentList = new ArrayList<>();
        for (int i = fromIndex; i <=toIndex; i++) {
            String[] transactionSLine = payments.get(i).split("\t");
            String[] accountSLine = deposits.get(i).split("\t");
            BigDecimal updatedAmount = BigDecimal.valueOf(Integer.parseInt(accountSLine[1]) + Integer.parseInt(transactionSLine[2]));

            Deposit updatedDep = new Deposit();
            updatedDep.setDepositNumber(accountSLine[0]);
            updatedDep.setAmount(updatedAmount);
            paymentList.add(updatedDep.toString());

        }
        return paymentList;
    }

    public synchronized List<String> processReport(List<String> deposits, List<String> payments, int fromIndex, int toIndex) {
        String[] debtorDeposit = deposits.get(0).split("\t");
        List<String> reportList = new ArrayList<>();
        for (int i = fromIndex; i <=toIndex; i++) {
            String[] transactionSLine = payments.get(i).split("\t");
            String[] accountSLine = deposits.get(i).split("\t");
            BigDecimal updatedAmount = BigDecimal.valueOf(Integer.parseInt(accountSLine[1]) + Integer.parseInt(transactionSLine[2]));

            Report report = new Report();
            report.setSrcDepositNumber(debtorDeposit[0]);
            report.setDstDepositNumber(transactionSLine[1]);
            report.setAmount(Integer.parseInt(transactionSLine[2]));
            reportList.add(report.toString());

        }

        return reportList;
    }

}
