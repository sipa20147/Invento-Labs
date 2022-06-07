package jobtest.task3.exception;

public class IncorrectRequest extends RuntimeException {
    public IncorrectRequest(String msg) {
        super(msg);
    }
}
