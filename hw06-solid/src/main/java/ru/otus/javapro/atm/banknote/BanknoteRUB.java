package ru.otus.javapro.atm.banknote;

import lombok.Getter;

@Getter
public class BanknoteRUB implements Banknote {

    private final Nominal nominal;
    private final int value;

    public BanknoteRUB(Nominal nominal) {
        this.nominal = nominal;
        this.value = this.nominal.getValue();
    }

    public static Nominal[] getNominals() {
        return RUBNominal.values();
    }


    public String toString() {
        return value + " RUB";
    }

    @Getter
    public enum RUBNominal implements Nominal {
        NOMINAL_50(50),
        NOMINAL_100(100),
        NOMINAL_200(200),
        NOMINAL_500(500),
        NOMINAL_1000(1000),
        NOMINAL_2000(2000),
        NOMINAL_5000(5000);

        private final int value;

        RUBNominal(int value) {
            this.value = value;
        }
    }


}
