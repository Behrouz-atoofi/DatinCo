package exception;

public class AccNotFoundException extends Exception {

    String msg;


    public AccNotFoundException(String entryMsg) {

        this.msg = entryMsg;

    }

    @Override
    public String toString() {
        return "Error 1008 : " + msg;
    }
}
