package exception;

import java.io.IOException;

public class SystemExceptions extends Exception {

    public SystemExceptions() {

    }

    public SystemExceptions(String message) {
        super(message);
    }

    public SystemExceptions(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemExceptions(Throwable cause) {
        super(cause);
    }

}
