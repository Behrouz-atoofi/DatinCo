package exception;

public class NotMatchAccException extends Exception {

    String msg;

    public NotMatchAccException(String entryMsg) {
        this.msg = entryMsg;

    }

    @Override
    public String toString() {
        return "Error 1005 : " + msg;
    }
}
