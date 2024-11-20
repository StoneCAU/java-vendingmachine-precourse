package vendingmachine.view;

public class OutputView {
    private static final String NEW_LINE = System.lineSeparator();

    private void printNewLine() {
        System.out.printf(NEW_LINE);
    }

    public void printErrorMessage(String message) {
        printNewLine();
        System.out.println(message);
    }
}
