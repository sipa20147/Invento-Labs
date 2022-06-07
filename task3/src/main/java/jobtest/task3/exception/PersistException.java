package jobtest.task3.exception;

import java.sql.SQLException;

public class PersistException extends SQLException {

    public PersistException(String reason, String SQLState, int vendorCode) {
        super(reason, SQLState, vendorCode);
    }

    public PersistException(String reason, String SQLState) {
        super(reason, SQLState);
    }

    public PersistException(String reason) {
        super(reason);
    }

    public PersistException() {
    }

    public PersistException(Throwable cause) {
        super(cause);
    }

    public PersistException(String reason, Throwable cause) {
        super(reason, cause);
    }

    public PersistException(String reason, String sqlState, Throwable cause) {
        super(reason, sqlState, cause);
    }

    public PersistException(String reason, String sqlState, int vendorCode, Throwable cause) {
        super(reason, sqlState, vendorCode, cause);
    }
}