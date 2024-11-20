package vendingmachine.validator;

import vendingmachine.exception.ErrorMessage;
import vendingmachine.exception.VendingMachineException;

public class InputValidator {

    public static int validateMoney(String input) {
        int money;

        try {
            money = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new VendingMachineException(ErrorMessage.INVALID_MONEY_FORMAT);
        }

        return money;
    }
}
