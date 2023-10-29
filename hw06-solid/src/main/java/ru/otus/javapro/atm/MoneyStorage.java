package ru.otus.javapro.atm;

import ru.otus.javapro.atm.banknote.Banknote;

import java.util.ArrayList;
import java.util.List;

public interface MoneyStorage {
    int put(List<Banknote> packOfMoney);

    ArrayList<Banknote> get(int amount) throws Exception;

    int getMinimalMultiple();
}
