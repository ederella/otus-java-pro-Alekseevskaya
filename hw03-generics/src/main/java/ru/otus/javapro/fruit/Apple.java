package ru.otus.javapro.fruit;


public class Apple extends Fruit{


    public Apple(String variety, double weight) {
        super(variety, weight);
    }

    @Override
    public void printInfo() {
        System.out.println("Apple: "+variety);
    }
}
