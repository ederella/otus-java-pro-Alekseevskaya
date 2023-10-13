package ru.otus.javapro.atm;

import lombok.Getter;
import ru.otus.javapro.atm.banknote.Banknote;

import java.util.ArrayList;
import java.util.List;

public class ATMSimple implements ATM {
    private final MoneyStorage moneyBoxes;

    @Getter
    private int balance = 0;

    public ATMSimple() {
        moneyBoxes = new MoneyStorageTree();
    }

    public void putMoney(List<Banknote> packOfMoney) {
        balance += moneyBoxes.put(packOfMoney);
    }

    public ArrayList<Banknote> getMoney(int amount) {
        try {
            System.out.println("Attempt to get " + amount);
            check(amount);
            ArrayList<Banknote> money = moneyBoxes.get(amount);
            balance -= amount;
            System.out.println("Success");
            return money;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    private void check(int amount) throws Exception {
        if (amount <= 0) {
            throw new Exception("Requested amount should be greater than 0");
        }
        int minimalMult = moneyBoxes.getMinimalMultiple();
        if (minimalMult == 0) {
            throw new Exception("ATM has no money");
        }
        if (amount % minimalMult > 0) {
            throw new Exception("Requested amount should be multiple of " + minimalMult);
        }
        if (balance < amount) {
            throw new Exception("Balance is not enough");
        }
    }

}
