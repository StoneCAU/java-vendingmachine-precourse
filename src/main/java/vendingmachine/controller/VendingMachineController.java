package vendingmachine.controller;

import vendingmachine.domain.Coins;
import vendingmachine.validator.InputValidator;
import vendingmachine.view.InputView;

public class VendingMachineController {

    public void run() {
        int initialMoney = getMoney();
        Coins coins = new Coins(initialMoney);
    }

    private int getMoney() {
        String input = InputView.inputInitialMoney();

        return InputValidator.validateMoney(input);
    }
}
