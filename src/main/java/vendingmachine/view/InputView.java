package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private final static String NEW_LINE = System.lineSeparator();

    private static void printNewLine() {
        System.out.printf(NEW_LINE);
    }

    public static String inputInitialMoney() {
        printNewLine();
        System.out.println("자판기가 보유하고 있는 금액을 입력해 주세요.");
        return Console.readLine();
    }

    public static String inputProducts() {
        printNewLine();
        System.out.println("상품명과 가격, 수량을 입력해 주세요.");
        return Console.readLine();
    }

    public static String inputPurchaseMoney() {
        printNewLine();
        System.out.println("투입 금액을 입력해 주세요.");
        return Console.readLine();
    }

    public static String inputPurchaseProduct() {
        System.out.println("구매할 상품명을 입력해 주세요.");
        return Console.readLine();
    }
}