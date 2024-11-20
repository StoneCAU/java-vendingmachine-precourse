package vendingmachine.controller;

import java.util.InputMismatchException;
import vendingmachine.domain.Coins;
import vendingmachine.exception.VendingMachineException;
import vendingmachine.validator.InputValidator;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class VendingMachineController {

    public void run() {
        Coins coins = getCoins();
        OutputView.printCoins(coins);
    }

    private Coins getCoins() {
        int initialMoney = getMoney();

        return new Coins(initialMoney);
    }

    private int getMoney() {
        while (true) {
            try {
                String input = InputView.inputInitialMoney();
                return InputValidator.validateMoney(input);
            } catch (VendingMachineException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
