package ru.otus.javapro.atm.banknote;

import lombok.Getter;

@Getter
public class BanknoteMDL implements Banknote {
    private final Nominal nominal;
    private final int value;

    public BanknoteMDL(Nominal nominal) {
        this.nominal = nominal;
        this.value = this.nominal.getValue();
    }

    public static Nominal[] getNominals() {
        return MDLNominal.values();
    }

    public String toString() {
        return value + " MDL";
    }

    @Getter
    public enum MDLNominal implements Nominal {
        NOMINAL_1(1),
        NOMINAL_5(5),
        NOMINAL_10(10),
        NOMINAL_50(50),
        NOMINAL_100(100),
        NOMINAL_200(200),
        NOMINAL_500(500),
        NOMINAL_1000(1000);

        private final int value;

        MDLNominal(int value) {
            this.value = value;
        }
    }
}
