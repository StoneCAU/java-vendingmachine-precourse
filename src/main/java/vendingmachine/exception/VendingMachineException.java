package vendingmachine.exception;

public class VendingMachineException extends IllegalArgumentException{
    private static final String PREFIX = "[ERROR] ";

    public VendingMachineException(ErrorMessage errorMessage) {
        super(PREFIX + errorMessage.getMessage());
    }
}