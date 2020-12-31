package oods4e.ch09.priorityQueues;

public class PriQOverflowException extends RuntimeException{

    public PriQOverflowException() {
        super();
    }

    public PriQOverflowException(String message) {
        super(message);
    }
}
