package ru.otus.javapro;

import lombok.extern.log4j.Log4j2;
import ru.otus.javapro.atm.ATM;
import ru.otus.javapro.atm.ATMSimple;
import ru.otus.javapro.atm.banknote.Banknote;
import ru.otus.javapro.atm.banknote.BanknoteMDL;
import ru.otus.javapro.atm.banknote.BanknoteRUB;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class Main {
    public static void main(String[] args) throws Exception {
        ArrayList<Banknote> packOfMoney = getPackOfMDL();
        ATM atm = new ATMSimple("MDL");

        atm.putMoney(packOfMoney);
        log.info("Balance is " + atm.getBalance());

        List<Banknote> a = atm.getMoney(10000);
        a.forEach((aa) -> log.info("banknote " + aa));

        log.info("Balance is " + atm.getBalance());

        List<Banknote> b = atm.getMoney(600);
        b.forEach((bb) -> log.info("banknote " + bb));

        log.info("Balance is " + atm.getBalance());

        List<Banknote> c = atm.getMoney(1350);
        c.forEach((cc) -> log.info("banknote " + cc));

        log.info("Balance is " + atm.getBalance());

        List<Banknote> d = atm.getMoney(111);
        d.forEach((dd) -> log.info("banknote " + dd));

        log.info("Balance is " + atm.getBalance());

        List<Banknote> e = atm.getMoney(50);
        e.forEach((ee) -> log.info("banknote " + ee));

        log.info("Balance is " + atm.getBalance());

        packOfMoney = getPackOfRUB();
        atm = new ATMSimple("RUB");

        atm.putMoney(packOfMoney);
        log.info("Balance is " + atm.getBalance());

        a = atm.getMoney(10000);
        a.forEach((aa) -> log.info("banknote " + aa));

        log.info("Balance is " + atm.getBalance());

        b = atm.getMoney(600);
        b.forEach((bb) -> log.info("banknote " + bb));

        log.info("Balance is " + atm.getBalance());

        c = atm.getMoney(1350);
        c.forEach((cc) -> log.info("banknote " + cc));

        log.info("Balance is " + atm.getBalance());

        d = atm.getMoney(111);
        d.forEach((dd) -> log.info("banknote " + dd));

        log.info("Balance is " + atm.getBalance());

        e = atm.getMoney(50);
        e.forEach((ee) -> log.info("banknote " + ee));

        log.info("Balance is " + atm.getBalance());
    }

    private static ArrayList<Banknote> getPackOfMDL() {

        ArrayList<Banknote> packOfMoney = new ArrayList<>();
        packOfMoney.add(new BanknoteMDL(BanknoteMDL.MDLNominal.NOMINAL_1));
        packOfMoney.add(new BanknoteMDL(BanknoteMDL.MDLNominal.NOMINAL_200));
        packOfMoney.add(new BanknoteMDL(BanknoteMDL.MDLNominal.NOMINAL_1000));
        packOfMoney.add(new BanknoteMDL(BanknoteMDL.MDLNominal.NOMINAL_200));
        packOfMoney.add(new BanknoteMDL(BanknoteMDL.MDLNominal.NOMINAL_500));
        packOfMoney.add(new BanknoteMDL(BanknoteMDL.MDLNominal.NOMINAL_500));
        packOfMoney.add(new BanknoteMDL(BanknoteMDL.MDLNominal.NOMINAL_200));
        return packOfMoney;
    }

    private static ArrayList<Banknote> getPackOfRUB() {

        ArrayList<Banknote> packOfMoney = new ArrayList<>();
        packOfMoney.add(new BanknoteRUB(BanknoteRUB.RUBNominal.NOMINAL_50));
        packOfMoney.add(new BanknoteRUB(BanknoteRUB.RUBNominal.NOMINAL_200));
        packOfMoney.add(new BanknoteRUB(BanknoteRUB.RUBNominal.NOMINAL_1000));
        packOfMoney.add(new BanknoteRUB(BanknoteRUB.RUBNominal.NOMINAL_200));
        packOfMoney.add(new BanknoteRUB(BanknoteRUB.RUBNominal.NOMINAL_500));
        packOfMoney.add(new BanknoteRUB(BanknoteRUB.RUBNominal.NOMINAL_500));
        packOfMoney.add(new BanknoteRUB(BanknoteRUB.RUBNominal.NOMINAL_200));
        return packOfMoney;
    }

}
