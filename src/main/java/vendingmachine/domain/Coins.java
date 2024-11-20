package vendingmachine.domain;

import static vendingmachine.domain.Coin.COIN_10;
import static vendingmachine.domain.Coin.COIN_100;
import static vendingmachine.domain.Coin.COIN_50;
import static vendingmachine.domain.Coin.COIN_500;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Coins {
    private List<Coin> coins;

    public Coins(int initialMoney) {
        this.coins = getCoins(initialMoney);
    }

    private List<Coin> getCoins(int initialMoney) {
        List<Coin> coins = new ArrayList<>();
        int currentMoney = initialMoney;

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

        if (currentMoney >= COIN_500.getAmount()) {
            availableList.add(COIN_500.getAmount());
        }
        if (currentMoney >= COIN_100.getAmount()) {
            availableList.add(COIN_100.getAmount());
        }
        if (currentMoney >= COIN_50.getAmount()) {
            availableList.add(COIN_50.getAmount());
        }
        if (currentMoney >= COIN_10.getAmount()) {
            availableList.add(COIN_10.getAmount());
        }

        return availableList;
    }

    @Override
    public String toString() {
        Map<Coin, Integer> coinNumber = getCoinNumber();

        return String.format("500원 - %d개%n100원 - %d개%n50원 - %d개%n10원 - %d개", coinNumber.get(COIN_500),
                coinNumber.get(COIN_100), coinNumber.get(COIN_50), coinNumber.get(COIN_10));
    }

    public Map<Coin, Integer> calculateChange(int amount) {
        Map<Coin, Integer> coinNumber = getCoinNumber();
        Map<Coin, Integer> change = new HashMap<>();
        List<Coin> initialCoins = List.of(COIN_500, COIN_100, COIN_50, COIN_10);
        AtomicInteger temp = new AtomicInteger(amount);
        initialCoins.stream()
                .filter(coin -> coinNumber.get(coin) > 0)
                .forEach(coin -> {
                    while (isConditionSatisfied(temp, coin, coinNumber)) {
                        temp.addAndGet(-coin.getAmount());
                        coinNumber.put(coin, coinNumber.get(coin) - 1);
                        change.put(coin, change.getOrDefault(coin, 0) + 1);
                    }
                });
        return change;
    }

    private Map<Coin, Integer> getCoinNumber() {
        Map<Coin, Integer> coinNumber = initialCoinNumber();

        coins.forEach(coin -> coinNumber.put(coin, coinNumber.getOrDefault(coin, 0) + 1));

        return coinNumber;
    }

    private Map<Coin, Integer> initialCoinNumber() {
        List<Coin> initialCoins = List.of(COIN_500, COIN_100, COIN_50, COIN_10);
        Map<Coin, Integer> coinNumber = new HashMap<>();
        initialCoins.forEach(coin -> coinNumber.put(coin, 0));

        return coinNumber;
    }

    private boolean isConditionSatisfied(AtomicInteger temp, Coin coin, Map<Coin, Integer> coinNumber) {
        return temp.get() >= coin.getAmount()
                && coinNumber.get(coin) > 0
                && temp.get() - coin.getAmount() > 0;
    }
}
