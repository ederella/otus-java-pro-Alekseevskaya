package ru.otus.javapro.atm;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import ru.otus.javapro.atm.banknote.Banknote;
import ru.otus.javapro.atm.banknote.BanknoteMDL;
import ru.otus.javapro.atm.banknote.BanknoteRUB;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class ATMSimple implements ATM {

    private final MoneyStorage moneyBoxes;
    @Getter
    private int balance = 0;

    public ATMSimple(String currency) throws Exception {
        if (currency.equalsIgnoreCase("RUB"))
            moneyBoxes = new MoneyStorageTree<BanknoteRUB>(BanknoteRUB.getNominals());
        else if (currency.equalsIgnoreCase("MDL"))
            moneyBoxes = new MoneyStorageTree<BanknoteMDL>(BanknoteMDL.getNominals());
        else {
            throw new Exception("Currency is not supported");
        }
    }

    public void putMoney(List<Banknote> packOfMoney) {
        balance += moneyBoxes.put(packOfMoney);
    }

    public ArrayList<Banknote> getMoney(int amount) {
        try {
            log.info("Attempt to get " + amount);
            check(amount);
            ArrayList<Banknote> money = moneyBoxes.get(amount);
            balance -= amount;
            log.info("Success");
            return money;
        } catch (Exception e) {
            log.error(e.getMessage());
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
