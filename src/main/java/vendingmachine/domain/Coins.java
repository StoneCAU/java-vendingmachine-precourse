package vendingmachine.domain;

import static vendingmachine.domain.Coin.COIN_10;
import static vendingmachine.domain.Coin.COIN_100;
import static vendingmachine.domain.Coin.COIN_50;
import static vendingmachine.domain.Coin.COIN_500;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;

public class Coins {
    private List<Coin> coins;

    public Coins(int initialMoney) {
        this.coins = getCoins(initialMoney);
    }

    private List<Coin> getCoins(int initialMoney) {
        List<Coin> coins = new ArrayList<>();
        int currentMoney  = initialMoney;

        while (currentMoney > 0) {
            List<Integer> availableList = createAvailableList(currentMoney);
            int pickedCoinAmount = Randoms.pickNumberInList(availableList);
            currentMoney -= pickedCoinAmount;
            coins.add(Coin.getByAmount(pickedCoinAmount));
        }

        return coins;
    }

    private List<Integer> createAvailableList(int currentMoney) {
        List<Integer> availableList = new ArrayList<>();

        if (currentMoney >= COIN_500.getAmount()) availableList.add(COIN_500.getAmount());
        if (currentMoney >= COIN_100.getAmount()) availableList.add(COIN_100.getAmount());
        if (currentMoney >= COIN_50.getAmount()) availableList.add(COIN_50.getAmount());
        if (currentMoney >= COIN_10.getAmount()) availableList.add(COIN_10.getAmount());

        return availableList;
    }
}
