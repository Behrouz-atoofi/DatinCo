import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StartingPoint {

    public static void main(String[] args) throws IOException {

        List<String> transactions = new ArrayList<>();
        List<String> accountsBalance = new ArrayList<>();

        // generating random Balance for debtor account and adding the first line into the List
        int debtorAccBalance = generateRandomValue(1);
        accountsBalance.add("1.10.100.1" + "\t" + debtorAccBalance);

        // generating creditor accounts & creditor transactions and adding them to the Lists
        int totalTransactions = 0;
        for (int i = 100; i <= 999; i++) {
            int randomValue = generateRandomValue(0);
            transactions.add("creditor" + "\t" + "1.20.100." + i + "\t" + randomValue);
            accountsBalance.add("1.20.100." + i + "\t" + generateRandomValue(0));
            totalTransactions += randomValue;
        }

        //Adding debtorTransaction based on totalTransactions
        transactions.add(0, "debtor" + "\t" + "1.10.100.1" + "\t" + totalTransactions);

        // making 2 files
        Path transFile = Paths.get("src/main/resources/", "transactions.txt");
        Path accountFile = Paths.get("src/main/resources/", "accountsBalance.txt");
        Files.deleteIfExists(transFile);
        Files.deleteIfExists(accountFile);
        Files.write(transFile, transactions);
        Files.write(accountFile, accountsBalance);


        // comparing debtorBalance and debtorTransactionValue
        if (debtorAccBalance > totalTransactions) {

            List<String> temp = new ArrayList<>();
            List<String> report = new ArrayList<>();

            // calculating the final Balance for every creditor Account after transactions and adding to List
            for (int i = 1; i < accountsBalance.size(); i++) {

                String[] spitedTLine = transactions.get(i).split("\t");
                String[] spitedALine = accountsBalance.get(i).split("\t");
                int updatedBalance = Integer.parseInt(spitedALine[1]) + Integer.parseInt(spitedTLine[2]);
                temp.add(spitedALine[0] + "\t" + updatedBalance);
                report.add("1.10.100.1" + "\t" + spitedTLine[1] + "\t" + spitedTLine[2]);
            }

            // updating Balance for debtor Account
            int debtorFinalBalance = debtorAccBalance - totalTransactions;
            temp.add(0, "1.10.100.1" + "\t" + debtorFinalBalance);

            // Making files
            Path tempFile = Paths.get("src/main/resources/", "tempFile.txt.tmp");
            Path reportFile = Paths.get("src/main/resources/", "report.txt");
            Files.deleteIfExists(reportFile);
            Files.deleteIfExists(tempFile);
            Files.write(tempFile,temp) ;
            Files.write(reportFile,report) ;

            // Updating accountFile
            Files.delete(accountFile);
            Files.move(tempFile, tempFile.resolveSibling(accountFile.getFileName()));

        } else {
            System.out.println("Not enough balance ...");
        }

    }


    // This method generate values for Accounts and transactions
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


}
