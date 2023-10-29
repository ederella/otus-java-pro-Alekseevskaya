package ru.otus.javapro.box;

import ru.otus.javapro.fruit.Fruit;

import java.util.ArrayList;

public class Box<T extends Fruit>{
    private ArrayList<T> fruits;

    final private int capacity = 10;

    public Box(){
        fruits = new ArrayList<>();
    }

    public boolean put(T fruit){
        if(fruits.size()< capacity) {
            fruits.add(fruit);
            return true;
        }
        return false;
    }

    public double weight() {
        int size = fruits.size();
        if (size == 0)
            return 0d;
        return fruits.get(0).getWeight() * size;
    }

    public boolean compare(Box<? extends Fruit> otherBox) {
        if (otherBox == null)
            return false;
        return Math.abs(this.weight() - otherBox.weight()) < 0.0001;
    }

    public void replaceFruitsTo(Box<T> otherBox) throws Exception {
        if(otherBox==null || otherBox == this){
            return;
        }
        if(otherBox.capacity - otherBox.fruits.size() < this.fruits.size()) {
            throw new Exception("Target box can't hold all fruits from source box");
        }
        otherBox.fruits.addAll(this.fruits);
        this.fruits.clear();
    }
}
