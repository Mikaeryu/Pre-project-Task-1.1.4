package jm.task.core.jdbc.exceptions;

public class ExecuteSQLException extends RuntimeException{
    public ExecuteSQLException() {

    }

    public ExecuteSQLException(String message) {
        super(message);
    }
}