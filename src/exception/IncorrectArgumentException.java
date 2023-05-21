package exception;

public class IncorrectArgumentException extends Exception {
    public IncorrectArgumentException() {
    }

    public IncorrectArgumentException(String message) {
        super(message);
    }

}
