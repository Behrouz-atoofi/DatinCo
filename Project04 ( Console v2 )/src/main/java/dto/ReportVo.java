package dto;

public class ReportVo {

    private String srcAccount ;
    private String dstAccount ;
    private int transValue ;

    public String getSrcAccount() {
        return srcAccount;
    }

    public void setSrcAccount(String srcAccount) {
        this.srcAccount = srcAccount;
    }

    public String getDstAccount() {
        return dstAccount;
    }

    public void setDstAccount(String dstAccount) {
        this.dstAccount = dstAccount;
    }

    public int getTransValue() {
        return transValue;
    }

    public void setTransValue(int transValue) {
        this.transValue = transValue;
    }

    @Override
    public String toString() {
        return srcAccount + "\t" + dstAccount + "\t" + transValue;
    }
}
