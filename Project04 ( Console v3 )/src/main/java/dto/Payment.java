package dto;

import java.math.BigDecimal;

public class Payment {
    private DepositType type;
    private String deposit;
    private BigDecimal amount;

    public DepositType getType() {
        return type;
    }

    public void setType(DepositType type) {
        this.type = type;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return type + "\t" + deposit + "\t" + amount;
    }

    public enum DepositType {

        debtor, creditor

    }
}
