package dto;

import java.math.BigDecimal;

public class Report {

    private String srcDepositNumber;
    private String dstDepositNumber;
    private BigDecimal amount;


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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return srcDepositNumber + "\t" + dstDepositNumber + "\t" + amount;
    }
}
