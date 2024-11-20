package vendingmachine.exception;

public enum ErrorMessage {
    INVALID_MONEY_FORMAT("금액은 숫자여야 합니다."),
    INVALID_COIN_AMOUNT("해당 금액의 동전은 존재하지 않습니다."),
    IS_NOT_NUMBER("입력은 숫자여야 합니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}