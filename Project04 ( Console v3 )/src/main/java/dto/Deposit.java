package dto;

import java.math.BigDecimal;

public class Deposit {


    private String deposit;
    private BigDecimal amount;

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
        return deposit + "\t" + amount;
    }
}
