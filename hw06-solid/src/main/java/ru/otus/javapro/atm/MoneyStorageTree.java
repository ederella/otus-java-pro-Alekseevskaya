package ru.otus.javapro.atm;

import ru.otus.javapro.atm.banknote.Banknote;

import java.util.*;

public class MoneyStorageTree implements MoneyStorage {

    private final TreeMap<Banknote, Integer> moneyBoxes;

    public MoneyStorageTree() {
        moneyBoxes = new TreeMap<>();
        for (Banknote banknote : Banknote.values()) {
            moneyBoxes.put(banknote, 0);
        }
    }

    @Override
    public int put(List<Banknote> packOfMoney) {
        int balance = 0;
        for (Banknote banknote : packOfMoney) {
            balance += banknote.getValue();
            for (Map.Entry<Banknote, Integer> moneyBox : moneyBoxes.entrySet()) {
                if (moneyBox.getKey().equals(banknote)) {
                    moneyBox.setValue(moneyBox.getValue() + 1);
                }
            }
        }
        return balance;
    }

    @Override
    public ArrayList<Banknote> get(int amount) throws Exception {
        ArrayList<Banknote> returnList = new ArrayList<>();
        NavigableSet<Banknote> banknotes = moneyBoxes.descendingKeySet();
        int amountTemp = amount;
        int attemptCounter = 0;
        for (Iterator<Banknote> iter = banknotes.iterator(); iter.hasNext(); ) {
            Banknote key = iter.next();
            int curBalance = moneyBoxes.get(key) * key.getValue();
            int numberOfBanknotesToGet = Math.min(curBalance, amountTemp) / key.getValue();

            if (numberOfBanknotesToGet > 0) {
                amountTemp = amountTemp - numberOfBanknotesToGet * key.getValue();
                for (int i = 0; i < numberOfBanknotesToGet; i++) {
                    returnList.add(key);
                }
            }
            if (amountTemp == 0) {
                returnList.forEach((b) -> moneyBoxes.put(b, moneyBoxes.get(b) - 1));
                return returnList;
            }

            if (!iter.hasNext()) {
                returnList.clear();
                amountTemp = amount;
                attemptCounter++;
                if (attemptCounter < banknotes.size()) {
                    iter = banknotes.iterator();
                    for (int i = 0; i < attemptCounter; i++) {
                        iter.next();
                    }
                }
            }
        }
        throw new Exception("No enough banknotes");
    }


    @Override
    public int getMinimalMultiple() {
        for (Banknote banknote :
                moneyBoxes.navigableKeySet()) {
            if (moneyBoxes.get(banknote) > 0)
                return banknote.getValue();
        }
        return 0;
    }
}
