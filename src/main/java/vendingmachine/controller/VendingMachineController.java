package vendingmachine.controller;

import vendingmachine.domain.Coins;
import vendingmachine.domain.Products;
import vendingmachine.exception.VendingMachineException;
import vendingmachine.validator.InputValidator;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class VendingMachineController {

    public void run() {
        Coins coins = getCoins();
        OutputView.printCoins(coins);

        Products products = getProducts();
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

    private Products getProducts() {
        String input = InputView.inputProducts();
        return InputValidator.validateProducts(input);
    }
}
