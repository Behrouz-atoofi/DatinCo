package dto;

import java.math.BigDecimal;

public class Payment {
    private DepositType type;
    private String depositNumber;
    private BigDecimal amount;

    public DepositType getType() {
        return type;
    }

    public void setType(DepositType type) {
        this.type = type;
    }

    public String getDepositNumber() {
        return depositNumber;
    }

    public void setDepositNumber(String depositNumber) {
        this.depositNumber = depositNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return type + "\t" + depositNumber + "\t" + amount;
    }

    public enum DepositType {

        debtor, creditor

    }
}
