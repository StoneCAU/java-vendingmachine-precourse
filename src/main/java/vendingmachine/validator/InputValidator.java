package vendingmachine.validator;

import java.util.List;
import vendingmachine.domain.Product;
import vendingmachine.domain.Products;
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

        if (money % 10 != 0) throw new VendingMachineException(ErrorMessage.INVALID_INPUT_FORMAT);

        return money;
    }

    public static Products validateProducts(String input) {
        List<String> parsedProductInput = parseProduct(input);

        List<Product> productList = parsedProductInput.stream()
                .map(InputValidator::validateProductInput).toList();

        return new Products(productList);
    }

    private static void validateIsNumber(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new VendingMachineException(ErrorMessage.IS_NOT_NUMBER);
        }
    }

    private static void validateProductInfo(List<String> productInfo) {
        if (productInfo.size() != 3) {
            throw new VendingMachineException(ErrorMessage.INVALID_INPUT_FORMAT);
        }
        validateIsNumber(productInfo.get(1));
        validateIsNumber(productInfo.get(2));
    }

    private static Product validateProductInput(String productInput) {
        List<String> productInfo = parseProductInfo(productInput);
        validateProductInfo(productInfo);
        return new Product(productInfo.get(0),
                Integer.parseInt(productInfo.get(1)), Integer.parseInt(productInfo.get(2)));
    }

    private static List<String> parseProduct(String input) {
        return List.of(input.split(";"));
    }

    private static List<String> parseProductInfo(String input) {
        if (!input.matches("\\[([^]]+)\\]")) {
            throw new VendingMachineException(ErrorMessage.INVALID_INPUT_FORMAT);
        }

        input = input.replaceAll("[\\[\\]]","");
        return List.of(input.split(","));
    }
}
