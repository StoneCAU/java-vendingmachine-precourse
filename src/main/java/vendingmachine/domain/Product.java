package vendingmachine.domain;

import vendingmachine.exception.ErrorMessage;
import vendingmachine.exception.VendingMachineException;

public class Product {
    private final String name;
    private final int price;
    private int quantity;

    public Product(String name, int price, int quantity) {
        validatePrice(price);
        validateQuantity(quantity);
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void purchase() {
        validateQuantity(quantity);
        quantity -= 1;
    }

    private void validatePrice(int price) {
        if  (price < 100 || price % 10 != 0) {
            throw new VendingMachineException(ErrorMessage.INVALID_PRICE);
        }
    }

    private void validateQuantity(int quantity) {
        if (quantity < 1) throw new VendingMachineException(ErrorMessage.INVAlID_QUANTITY);
    }

    private void validatePurchasable() {
        if (quantity == 0) throw new VendingMachineException(ErrorMessage.NOT_PURCHASABLE);
    }
}
