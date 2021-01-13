package exception;

public class AmountException extends Exception {

String msg ;


public AmountException(String entryMsg) {

    this.msg = entryMsg ;
}

    @Override
    public String toString() {
        return "Error 1007 : " + msg ;
    }
}
