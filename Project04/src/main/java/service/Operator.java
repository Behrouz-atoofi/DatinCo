package service;

import dto.Deposit;
import dto.Payment;
import exception.AccNotFoundException;
import exception.AmountException;
import exception.DivisibleException;
import exception.NotMatchAccException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class Operator {

    static Logger log = Logger.getLogger(Generator.class.getName());

    public static void payment(List<Deposit> deposits, List<Payment> payments, int thread) throws InterruptedException, AccNotFoundException, DivisibleException, AmountException, NotMatchAccException, IOException {


        int paymentSize = payments.size();

        if ((paymentSize) % thread != 0) {
            log.warn("The number of thread should be divisible for deposit size");
            log.info("application stopped...");
            throw new DivisibleException("The number of thread should be divisible for deposit size");
        } else if (!checkDebtorAmount(deposits, payments)) {
            log.warn("Not enough money for processing this transaction");
            log.info("application stopped...");
            System.exit(0);
        }


        int divided = paymentSize / thread;

        if (thread > 1) {

            ExecutorService executor = Executors.newFixedThreadPool(thread) ;
            CThread cThread = new CThread();

            for (int i = 0; i < thread; i++) {
            int finalI = i;
            executor.execute(() -> {
                try {
                    cThread.process(deposits, payments.subList((divided * finalI), (divided * (finalI + 1))));
                } catch (NotMatchAccException | IOException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.awaitTermination(1, TimeUnit.SECONDS);
        executor.shutdown();

    } else {
        CThread cThread = new CThread();
        cThread.process(deposits, payments);
    }


    }

    public static boolean checkDebtorAmount(List<Deposit> deposits, List<Payment> payments) throws AccNotFoundException, AmountException {


        BigDecimal debtorAmount = BigDecimal.valueOf(0);
        BigDecimal totalPayment = BigDecimal.valueOf(0);
        String debtorAccount = null;

        for (int i = 0; i < payments.size(); i++) {

            Payment payment ;
            payment =payments.get(i);

            if (payment.getType().toString().equals("creditor")) {
                totalPayment = totalPayment.add(payment.getAmount());

            }
            else if (payment.getType().toString().equals("debtor")) {
                debtorAmount = debtorAmount.add(payment.getAmount());
                debtorAccount = payment.getDepositNumber();
            }

        }

        if (debtorAccount != null) {

            for (int i = 0; i < deposits.size(); i++) {

                Deposit deposit = new Deposit() ;
                deposit = deposits.get(i) ;
                if (deposit.getDepositNumber().equals(debtorAccount)) {
                    BigDecimal debtorBalance = deposit.getAmount();
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
