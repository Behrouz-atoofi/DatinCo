package dto;

public class Report {

    private String srcDepositNumber;
    private String dstDepositNumber;
    private int amount;


    public String getSrcDepositNumber() {
        return srcDepositNumber;
    }

    public void setSrcDepositNumber(String srcDepositNumber) {
        this.srcDepositNumber = srcDepositNumber;
    }

    public String getDstDepositNumber() {
        return dstDepositNumber;
    }

    public void setDstDepositNumber(String dstDepositNumber) {
        this.dstDepositNumber = dstDepositNumber;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return srcDepositNumber + "\t" + dstDepositNumber + "\t" + amount;
    }
}
