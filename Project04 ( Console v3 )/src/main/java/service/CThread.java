package service;

import dto.Deposit;
import dto.Report;
import exception.NotMatchAccException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CThread {

    List<String> paymentList = new ArrayList<>();
    List<String> reportList = new ArrayList<>();

    public CThread(List<String> deposits, List<String> payments, int fromIndex, int toIndex) throws NotMatchAccException {

        String[] debtorDeposit = deposits.get(0).split("\t");

        for (int i = fromIndex; i <toIndex; i++) {

            String[] paymentSLine = payments.get(i).split("\t");
            String[] depositSLine = deposits.get(i).split("\t");

            if ("creditor".equals(paymentSLine[0])) {
                if (paymentSLine[1].equals(depositSLine[0])) {
                    BigDecimal newAmount = BigDecimal.valueOf(Integer.parseInt(depositSLine[1]) + Integer.parseInt(paymentSLine[2]));

                    Deposit newDeposit = new Deposit();
                    newDeposit.setDepositNumber(depositSLine[0]);
                    newDeposit.setAmount(newAmount);
                    paymentList.add(newDeposit.toString());

                    Report report = new Report();
                    report.setSrcDepositNumber(debtorDeposit[0]);
                    report.setDstDepositNumber(paymentSLine[1]);
                    report.setAmount(Integer.parseInt(paymentSLine[2]));
                    reportList.add(report.toString());

                } else {
                    throw new NotMatchAccException("DepositNumber Not match .") ;
                }


            } else if (paymentSLine[1].equals(depositSLine[0])) {
                    BigDecimal newAmount = BigDecimal.valueOf(Integer.parseInt(depositSLine[1]) - Integer.parseInt(paymentSLine[2]));
                    Deposit newDeposit = new Deposit();
                    newDeposit.setDepositNumber(depositSLine[0]);
                    newDeposit.setAmount(newAmount);
                    paymentList.add(0,newDeposit.toString());
                }
            }

    }

    public synchronized List<String> getPaymentList() {
        return paymentList ;
    }

    public synchronized List<String> getReportList () {
        return reportList ;
    }



}
