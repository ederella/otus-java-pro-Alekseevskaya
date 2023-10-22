package ru.otus.javapro.atm;

import ru.otus.javapro.atm.banknote.Banknote;
import ru.otus.javapro.atm.banknote.Nominal;

import java.util.*;

public class MoneyStorageTree<E extends Banknote> implements MoneyStorage {

    private final TreeMap<Nominal, LinkedList<E>> moneyBoxes;

    public MoneyStorageTree(Nominal[] nominals) {
        moneyBoxes = new TreeMap<>();
        for (Nominal nominal : nominals) {
            moneyBoxes.put(nominal, new LinkedList<>());
        }
    }

    @Override
    public int put(List<Banknote> packOfMoney) {
        int balance = 0;
        for (Banknote banknote : packOfMoney) {
            balance += banknote.getValue();
            for (Map.Entry<Nominal, LinkedList<E>> moneyBox : moneyBoxes.entrySet()) {
                if (moneyBox.getKey().equals(banknote.getNominal())) {
                    moneyBox.getValue().add((E) banknote);
                }
            }
        }
        return balance;
    }

    @Override
    public ArrayList<Banknote> get(int amount) throws Exception {
        ArrayList<Banknote> returnList = new ArrayList<>();
        NavigableSet<Nominal> banknotes = moneyBoxes.descendingKeySet();
        int amountTemp = amount;
        int attemptCounter = 0;
        for (Iterator<Nominal> iter = banknotes.iterator(); iter.hasNext(); ) {
            Nominal key = iter.next();
            int curBalance = moneyBoxes.get(key).size() * key.getValue();
            int numberOfBanknotesToGet = Math.min(curBalance, amountTemp) / key.getValue();

            if (numberOfBanknotesToGet > 0) {
                amountTemp = amountTemp - numberOfBanknotesToGet * key.getValue();
                for (int i = 0; i < numberOfBanknotesToGet; i++) {
                    returnList.add(moneyBoxes.get(key).pop());
                }
            }
            if (amountTemp == 0) {
                return returnList;
            }

            if (!iter.hasNext()) {
                put(returnList);
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
        for (Nominal nominal :
                moneyBoxes.navigableKeySet()) {
            if (!moneyBoxes.get(nominal).isEmpty())
                return nominal.getValue();
        }
        return 0;
    }
}
