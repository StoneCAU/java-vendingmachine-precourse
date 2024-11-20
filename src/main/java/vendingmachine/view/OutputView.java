package vendingmachine.view;

import static vendingmachine.domain.Coin.COIN_10;
import static vendingmachine.domain.Coin.COIN_100;
import static vendingmachine.domain.Coin.COIN_50;
import static vendingmachine.domain.Coin.COIN_500;

import java.util.List;
import java.util.Map;
import vendingmachine.domain.Coin;
import vendingmachine.domain.Coins;

public class OutputView {
    private static final String NEW_LINE = System.lineSeparator();

    private static void printNewLine() {
        System.out.printf(NEW_LINE);
    }

    public static void printCoins(Coins coins) {
        printNewLine();
        System.out.println("자판기가 보유한 동전");
        System.out.println(coins);
    }

    public static void printInputMoney(int amount) {
        System.out.printf("%n투입 금액: %d원%n", amount);
    }

    public static void printChange(Map<Coin, Integer> change) {
        printNewLine();
        System.out.println("잔돈");

        change.forEach((coin, quantity) -> System.out.printf("%d원 - %d개%n", coin.getAmount(), quantity));
    }

    public static void printErrorMessage(String message) {
        printNewLine();
        System.out.println(message);
    }
}
