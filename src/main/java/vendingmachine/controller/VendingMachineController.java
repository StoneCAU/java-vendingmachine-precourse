package vendingmachine.controller;

import vendingmachine.validator.InputValidator;
import vendingmachine.view.InputView;

public class VendingMachineController {

    public void run() {
        int initialMoney = getMoney();
    }

    private int getMoney() {
        String input = InputView.inputInitialMoney();

        return InputValidator.validateMoney(input);
    }
}
