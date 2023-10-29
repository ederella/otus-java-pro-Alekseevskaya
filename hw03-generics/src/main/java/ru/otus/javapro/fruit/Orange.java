package ru.otus.javapro.fruit;

public class Orange extends Fruit{
    public Orange(String variety, double weight) {
        super(variety, weight);
    }

    @Override
    public void printInfo() {
        System.out.println("Orange: "+variety);
    }
}
