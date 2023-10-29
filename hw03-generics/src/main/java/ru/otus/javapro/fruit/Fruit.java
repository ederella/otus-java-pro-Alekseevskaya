package ru.otus.javapro.fruit;

public abstract class Fruit {
    String variety;
    double weight;

    public Fruit(String variety, double weight) {
        this.variety = variety;
        this.weight = weight;
    }

    public double getWeight(){
        return weight;
    }
    abstract public void printInfo();
}
