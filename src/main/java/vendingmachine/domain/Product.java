package vendingmachine.domain;

import vendingmachine.exception.ErrorMessage;
import vendingmachine.exception.VendingMachineException;

public class Product {
    private String name;
    private int price;
    private int quantity;

    public Product(String name, int price, int quantity) {
        validatePrice(price);
        validateQuantity(quantity);
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    private void validatePrice(int price) {
        if  (price < 100 || price % 10 != 0) {
            throw new VendingMachineException(ErrorMessage.INVALID_PRICE);
        }
    }

    private void validateQuantity(int quantity) {
        if (quantity < 1) throw new VendingMachineException(ErrorMessage.INVAlID_QUANTITY);
    }
}
