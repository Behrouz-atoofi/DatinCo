package service;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import dto.AccountDto;
import exception.SystemExceptions;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dto.*;

public class Generator implements Runnable{

    static Logger log = Logger.getLogger(Generator.class.getName());

    // This method generates values for Accounts and transactions
    // the value that will be generated for debtor account and  debtor transaction is between 500_000 and 10_000_000
    // and for creditor accounts is between 100 and 5000
    public static int generateRandomValue(int n) {
        Random r = new Random();
        int low;
        int high;
        int randomValue;
        if (n == 0) {
            low = 100;
            high = 5000;
            randomValue = (Math.round((r.nextInt(high - low) + low) / 100)) * 100;
        } else {
            low = 500000;
            high = 10000000;
            randomValue = (Math.round((r.nextInt(high - low) + low) / 10000)) * 10000;
        }
        return randomValue;
    }

    public static List<String> generateAccounts(int n)  {
        BasicConfigurator.configure();


        List<String> accountList = new ArrayList<>();
        AccountDto debtorAccount = new AccountDto();
        debtorAccount.setAccountNumber("1.10.100.1");
        debtorAccount.setBalance(Generator.generateRandomValue(1));
        accountList.add(debtorAccount.toString());
        log.info("Balance generated for debtorAccount");


        for (int i = 1; i <= n; i++) {

            AccountDto creditorAccount = new AccountDto();
            creditorAccount.setAccountNumber("1.20.100." + i);
            creditorAccount.setBalance(generateRandomValue(0));
            accountList.add(creditorAccount.toString());


        }
        log.info("Balances generated for all creditorAccounts");

        Path accountFile = Paths.get("src/main/resources/" , "accounts.txt") ;

        try {
            Files.write(accountFile, accountList) ;
        } catch (IOException e) {
            e.getMessage() ;
            log.warn("AccountFile couldn't write");
        }


        return accountList;
    }

    public static List<String> generateTransactions(int n)  {

        List<String> transactionList = new ArrayList<>();

        int totalTransactions = 0;

        for (int i = 1; i <= n; i++) {

            int randomValue = generateRandomValue(0);
            TransactionDto transactionDto = new TransactionDto();
            transactionDto.setType("creditor");
            transactionDto.setAccountNumber("1.20.100." + i);
            transactionDto.setValue(randomValue);
            transactionList.add(transactionDto.toString());
            totalTransactions += randomValue;

        }
        log.info("Transactions generated successfully");

        TransactionDto debtorTransaction = new TransactionDto();
        debtorTransaction.setType("debtor");
        debtorTransaction.setAccountNumber("1.10.100.1");
        debtorTransaction.setValue(totalTransactions);
        transactionList.add(0, debtorTransaction.toString());
        log.info("DebtorTransaction calculated based on total Transactions");

        Path transactionsFile = Paths.get("src/main/resources","transactionsFile.txt") ;
        try {
            Files.write(transactionsFile,transactionList) ;
            log.info("TransactionFile wrote successfully");
        } catch (IOException e) {
            e.getMessage() ;
            log.warn("TransactionFile couldn't write");
        }
        return transactionList;
    }


    @Override
    public void run() {

    }

}
