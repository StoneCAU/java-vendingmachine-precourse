package vendingmachine.domain;

import vendingmachine.exception.ErrorMessage;
import vendingmachine.exception.VendingMachineException;

public enum Coin {
    COIN_500(500),
    COIN_100(100),
    COIN_50(50),
    COIN_10(10);

    private final int amount;

    Coin(final int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public static Coin getByAmount(int amount) {
        if (amount == COIN_500.amount) return COIN_500;
        if (amount == COIN_100.amount) return COIN_100;
        if (amount == COIN_50.amount) return COIN_50;
        if (amount == COIN_10.amount) return COIN_10;
        throw new VendingMachineException(ErrorMessage.INVALID_COIN_AMOUNT);
    }
}
