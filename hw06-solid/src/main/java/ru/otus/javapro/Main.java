package ru.otus.javapro;

import ru.otus.javapro.atm.ATM;
import ru.otus.javapro.atm.ATMSimple;
import ru.otus.javapro.atm.banknote.Banknote;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArrayList<Banknote> packOfMoney = getPackOfMoney();
        ATM atm = new ATMSimple();
        atm.putMoney(packOfMoney);
        System.out.println("Balance is " + atm.getBalance());

        List<Banknote> a = atm.getMoney(10000);
        a.forEach((aa) -> System.out.println("banknote " + aa));

        System.out.println("Balance is " + atm.getBalance());

        List<Banknote> b = atm.getMoney(600);
        b.forEach((bb) -> System.out.println("banknote " + bb));

        System.out.println("Balance is " + atm.getBalance());

        List<Banknote> c = atm.getMoney(1350);
        c.forEach((cc) -> System.out.println("banknote " + cc));

        System.out.println("Balance is " + atm.getBalance());

        List<Banknote> d = atm.getMoney(111);
        d.forEach((dd) -> System.out.println("banknote " + dd));

        System.out.println("Balance is " + atm.getBalance());

        List<Banknote> e = atm.getMoney(50);
        e.forEach((ee) -> System.out.println("banknote " + ee));

        System.out.println("Balance is " + atm.getBalance());
    }

    private static ArrayList<Banknote> getPackOfMoney() {
        ArrayList<Banknote> packOfMoney = new ArrayList<>();
        packOfMoney.add(Banknote.NOMINAL_50);
        packOfMoney.add(Banknote.NOMINAL_200);
        packOfMoney.add(Banknote.NOMINAL_1000);
        packOfMoney.add(Banknote.NOMINAL_200);
        packOfMoney.add(Banknote.NOMINAL_500);
        packOfMoney.add(Banknote.NOMINAL_500);
        packOfMoney.add(Banknote.NOMINAL_200);
        return packOfMoney;
    }
}
