package ru.otus.javapro.atm;

import ru.otus.javapro.atm.banknote.Banknote;

import java.util.List;

public interface ATM {
    void putMoney(List<Banknote> listOfMoney);

    List<Banknote> getMoney(int amount);

    int getBalance();

}
