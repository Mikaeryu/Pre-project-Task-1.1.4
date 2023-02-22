package jm.task.core.jdbc.Exceptions;

public class ExecuteSQLException extends RuntimeException{
    public ExecuteSQLException() {

    }

    public ExecuteSQLException(String message) {
        super(message);
    }
}