package vendingmachine.controller;

import vendingmachine.domain.Coins;
import vendingmachine.domain.Money;
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
        Money inputMoney = getInputMoney();

        purchase(coins, products, inputMoney);
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
        while (true) {
            try {
                String input = InputView.inputProducts();
                return InputValidator.validateProducts(input);
            } catch (VendingMachineException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private Money getInputMoney() {
        while (true) {
            try {
                String input = InputView.inputPurchaseMoney();
                return new Money(InputValidator.validateMoney(input));
            } catch (VendingMachineException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void purchase(Coins coins, Products products, Money inputMoney) {

    }
}
