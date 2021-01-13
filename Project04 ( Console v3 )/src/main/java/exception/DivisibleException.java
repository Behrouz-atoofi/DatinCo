package exception;

public class DivisibleException extends Exception {

    String msg;

    public DivisibleException(String entryMsg) {
        this.msg = entryMsg;
    }


    @Override
    public String toString() {
        return "Error 1006 : " + msg;
    }

}
