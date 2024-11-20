package vendingmachine.controller;

import java.util.List;
import java.util.Map;
import vendingmachine.domain.Coin;
import vendingmachine.domain.Coins;
import vendingmachine.domain.Money;
import vendingmachine.domain.Product;
import vendingmachine.domain.Products;
import vendingmachine.exception.ErrorMessage;
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
        while (!purchaseNotAvailable(products, inputMoney)) {
            OutputView.printInputMoney(inputMoney.getAmount());
            Product product = findByName(products);
            product.purchase();
            inputMoney = new Money(inputMoney.getAmount() - product.getPrice());
        }

        Map<Coin, Integer> change = coins.calculateChange(inputMoney.getAmount());
        OutputView.printChange(change);
    }

    private Product findByName(Products products) {
        while (true) {
            try {
                String name = InputView.inputPurchaseProduct();
                return products.getProductByName(name)
                        .orElseThrow(() -> new VendingMachineException(ErrorMessage.NOT_FOUND_PRODUCT));
            } catch (VendingMachineException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private boolean purchaseNotAvailable(Products products, Money inputMoney) {
        List<Product> productList = products.getProducts();
        int currentAmount = inputMoney.getAmount();

        return moneyIsShortage(productList, currentAmount) || productIsSoldOut(productList);
    }

    private boolean moneyIsShortage(List<Product> productList, int currentAmount) {
        return productList.stream()
                .allMatch(product -> product.getPrice() > currentAmount);
    }

    private boolean productIsSoldOut(List<Product> productList) {
        return productList.stream()
                .allMatch(product -> product.getQuantity() < 1);
    }
}
