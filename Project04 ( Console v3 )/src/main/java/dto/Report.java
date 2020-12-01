package dto;

public class Report {

    private String srcDeposit;
    private String dstDeposit;
    private int amount;


    public String getSrcDeposit() {
        return srcDeposit;
    }

    public void setSrcDeposit(String srcDeposit) {
        this.srcDeposit = srcDeposit;
    }

    public String getDstDeposit() {
        return dstDeposit;
    }

    public void setDstDeposit(String dstDeposit) {
        this.dstDeposit = dstDeposit;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return srcDeposit + "\t" + dstDeposit + "\t" + amount;
    }
}
