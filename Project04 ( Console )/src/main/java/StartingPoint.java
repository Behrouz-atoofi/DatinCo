import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Random;

public class StartingPoint {

    public static void main(String[] args) throws IOException {

        // making 2 files
        Path transactionsFile = Paths.get("src/main/resources/", "transactionsFile.txt");
        Path accountBalanceFile = Paths.get("src/main/resources/", "accountBalance.txt");

        // deleting if exist
        Files.deleteIfExists(transactionsFile);
        Files.deleteIfExists(accountBalanceFile);

        // creating the files
        Files.createFile(transactionsFile);
        Files.createFile(accountBalanceFile);

        // generating creditor accounts & creditor transactions
        int totalTransactions = 0;
        for (int i = 100; i <= 200; i++) {
            int randomValue = generateRandomValue(0);
            String creditorTransaction = "creditor" + "\t" + "1.20.100." + i + "\t" + randomValue + "\n";
            String creditorAccountBalance = "1.20.100." + i + "\t" + generateRandomValue(0) + "\n";
            Files.write(transactionsFile, creditorTransaction.getBytes(), StandardOpenOption.APPEND);
            Files.write(accountBalanceFile, creditorAccountBalance.getBytes(), StandardOpenOption.APPEND);
            totalTransactions += randomValue;

        }

        // generating debtor transaction
        String debtorTransaction = "debtor" + "\t" + "1.10.100.1" + "\t" + totalTransactions;
        Files.write(transactionsFile, debtorTransaction.getBytes(), StandardOpenOption.APPEND);

        /// generating debtor balance
        String debtorAccount = "1.10.100.1" + "\t" + generateRandomValue(1) + "\n";
        Files.write(accountBalanceFile, debtorAccount.getBytes(), StandardOpenOption.APPEND);


        // inserting every line from 2 files into arrayLists
        List<String> transactionLines = Files.readAllLines(transactionsFile);
        List<String> accountBalanceLines = Files.readAllLines(accountBalanceFile);

        // calculating the final balance based on debtor transaction for debtor account
        String[] debtorAcc = accountBalanceLines.get(accountBalanceLines.size() - 1).split("\t"); // the balance of debtor account
        int debtorAccBalance = Integer.parseInt(debtorAcc[1]);


        // comparing debtorBalance and debtorTransaction
        if (debtorAccBalance > totalTransactions) {

            Path tempFile = Paths.get("src/main/resources/", "tempFile.txt.tmp");
            Path reportFile = Paths.get("src/main/resources/", "report.txt");

            Files.deleteIfExists(reportFile);
            Files.deleteIfExists(tempFile);
            Files.createFile(tempFile);
            Files.createFile(reportFile);

            // calculating the final Balance for every creditor Account after transactions and writing into tempFile
            for (int i = 0; i < accountBalanceLines.size() - 1; i++) {

                String[] spitedTransactionLine = transactionLines.get(i).split("\t");
                String[] spitedAccountBalanceLine = accountBalanceLines.get(i).split("\t");

                int finalBalance = Integer.parseInt(spitedAccountBalanceLine[1]) + Integer.parseInt(spitedTransactionLine[2]);

                String finalBalanceLine = spitedAccountBalanceLine[0] + "\t" + finalBalance + "\n";
                Files.write(tempFile, finalBalanceLine.getBytes(), StandardOpenOption.APPEND);
                String reportTransactionLine = "1.10.100.1" + "\t" + spitedTransactionLine[1] + "\t" + spitedTransactionLine[2] + "\n";
                Files.write(reportFile, reportTransactionLine.getBytes(), StandardOpenOption.APPEND);
            }

            // Updated Balance for debtor Account

            int debtorFinalBalance = debtorAccBalance - totalTransactions;
            String debtorLine = debtorAcc[0] + "\t" + debtorFinalBalance + "\n";
            Files.write(tempFile, debtorLine.getBytes(), StandardOpenOption.APPEND);

            // Updating accountBalanceFile
            Files.delete(accountBalanceFile);
            Files.move(tempFile, tempFile.resolveSibling(accountBalanceFile.getFileName()));
        } else {
            System.out.println("Not enough balance ...");
        }

    }


    // This method generate values for Accounts and transactions
    // the value that will be generated for debtor account and  debtor transaction is between 500_000 and 1_000_000
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
            high = 1000000;
            randomValue = (Math.round((r.nextInt(high - low) + low) / 1000)) * 1000;
        }
        return randomValue;
    }


}
