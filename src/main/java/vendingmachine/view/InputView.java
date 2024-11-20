package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private final static String NEW_LINE = System.lineSeparator();

    private void printNewLine() {
        System.out.printf(NEW_LINE);
    }

    public static String inputInitialMoney() {
        System.out.println("자판기가 보유하고 있는 금액을 입력해 주세요.");
        return Console.readLine();
    }
}