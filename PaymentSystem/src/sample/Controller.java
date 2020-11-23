package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Controller {
    @FXML
    private TextField PaymentSourceAddr;
    @FXML
    private TextField AccountBalanceSourceAddr;


    public void exit(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void info(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText("Payment System  v1  | DatinCo | ");
        alert.setContentText("Behrouz Atoofi - 2020 ");
        alert.show();
    }

    public void startTransactionClicked(ActionEvent actionEvent) throws IOException {


        Alert alert = new Alert(Alert.AlertType.ERROR);

        Path paymentFile = Paths.get(PaymentSourceAddr.getText());
        Path accountBalanceFile = Paths.get(AccountBalanceSourceAddr.getText());

        if (Files.exists(paymentFile) && Files.exists(accountBalanceFile)) {


            List<String> paymentLines = Files.readAllLines(paymentFile);
            List<String> accountBalanceLines = Files.readAllLines(accountBalanceFile);
            String[] debtorTrans = paymentLines.get(0).split("\t");      // the value of debtor transaction
            String[] debtorAcc = accountBalanceLines.get(0).split("\t"); // the balance of debtor account
            int debtorValueTrans = Integer.parseInt(debtorTrans[2]);
            int debtorAccBalance = Integer.parseInt(debtorAcc[1]);

            if (debtorValueTrans > debtorAccBalance) {
                alert.setTitle("Error");
                alert.setHeaderText("The debtor account balance not enough for these transactions");
                alert.setContentText(" please charge the debtor Account ");
                alert.show();
            } else {

                Path tempFile = Paths.get(String.valueOf(paymentFile.getParent()), "tempFile.txt.tmp");
                Files.deleteIfExists(tempFile);
                Files.createFile(tempFile);

                Path reportFile = Paths.get(String.valueOf(paymentFile.getParent()), "report.txt");
                Files.deleteIfExists(reportFile);
                Files.createFile(reportFile);

                debtorAccBalance -= debtorValueTrans; // final value for debtor account
                String debtorLine = debtorAcc[0] + "\t" + debtorAccBalance + "\n"; // writing the first line into tempFile
                Files.write(tempFile, debtorLine.getBytes());

                // fetching data from both files and writing new data info temp file
                for (int i = 1; i < accountBalanceLines.size() - 1; i++) {
                    String[] splitPaymentLine = paymentLines.get(i).split("\t");
                    String[] splitAccountBalanceLine = accountBalanceLines.get(i).split("\t");

                    int finalBalance = Integer.parseInt(splitAccountBalanceLine[1]) + Integer.parseInt(splitPaymentLine[2]);

                    String finalBalanceLine = splitAccountBalanceLine[0] + "\t" + finalBalance + "\n";
                    Files.write(tempFile, finalBalanceLine.getBytes(), StandardOpenOption.APPEND);
                    String reportTransactionLine = debtorTrans[1] + "\t" + splitPaymentLine[1] + "\t" + splitPaymentLine[2] + "\n";
                    Files.write(reportFile, reportTransactionLine.getBytes(), StandardOpenOption.APPEND);
                }

                Files.delete(accountBalanceFile);
                Files.move(tempFile, tempFile.resolveSibling(accountBalanceFile.getFileName()));

                Alert sAlert = new Alert(Alert.AlertType.INFORMATION) ;
                sAlert.setTitle("Successful Process ! ");
                sAlert.setHeaderText("Successful Process ! ");

                sAlert.show();

            }
        }

        else {
            alert.setTitle("Error");
            alert.setHeaderText(" Payment.txt or AccountBalance.txt  do not Exist ! ");
            alert.setContentText("please check the directory");
            alert.show();
        }

//            for (int i = 0; i < paymentLines.size(); i++) {
//                String[] splitPaymentLine = paymentLines.get(i).split("\t");
//                if ("debtor".equalsIgnoreCase(splitPaymentLine[0])) {
//                    debtor += Integer.parseInt(splitPaymentLine[2]);
//                } else if ("creditor".equalsIgnoreCase(splitPaymentLine[0])) {
//                    creditor += Integer.parseInt(splitPaymentLine[2]);
//                }
//            }
//
//            System.out.println("Total debtor Value is : " + debtor);
//            System.out.println("Total creditor value is : "+ creditor);
//            if (creditor > debtor) {
//
//            } else {


//                String[]debtorLine = AccountBalanceLine.get(0).split("\t") ;
//                int a = Integer.parseInt(debtorLine[0]) ;
//                a -= debtor ;
//                fileWriter.write(debtorLine[0]+"\t"+a);
//                for (int i = 1; i < paymentLines.size(); i++) {
//                    String[] splitPaymentLine = paymentLines.get(i).split("\t");
//                    String[] splitAccountBalanceLine = AccountBalanceLine.get(i).split("\t") ;
//
//                    if (splitPaymentLine[1].equalsIgnoreCase(splitAccountBalanceLine[0])) {
//                        splitPaymentLine[2] +=splitAccountBalanceLine[1] ;
//                    }
//                }
//                    fileWriter.close();
//            }

    }

}
