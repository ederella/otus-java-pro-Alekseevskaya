package ru.otus.javapro;

import ru.otus.javapro.box.Box;
import ru.otus.javapro.fruit.Apple;
import ru.otus.javapro.fruit.Fruit;
import ru.otus.javapro.fruit.Orange;

public class Main {

    public static void main(String[] args) throws Exception {
        Apple redApple = new Apple("Red Сhief", 220d);
        Apple greenApple = new Apple("Granny Smith", 200d);
        Orange orange = new Orange("Hamlin", 200d);

        Box<Apple> appleBox = new Box<>();
        Box<Orange> orangeBox = new Box<>();
        Box<Fruit> fruitBox = new Box<>();

        for (int i = 0; i < 5; i++) {
            appleBox.put(redApple);
            appleBox.put(greenApple);

            orangeBox.put(orange);

            fruitBox.put(redApple);
            fruitBox.put(orange);
        }

        System.out.println("A box with apples weighs: "+ appleBox.weight());
        System.out.println("A box with oranges weighs: "+ orangeBox.weight());
        System.out.println("A box with mixed fruits weighs: "+ fruitBox.weight());

        System.out.println("A box with mixed fruits comparing with a box with apples: "+ fruitBox.compare(appleBox));
        System.out.println("A box with oranges comparing with a box with apples: "+ orangeBox.compare(appleBox));

        Box<Apple> emptyAppleBox = new Box<>();
        System.out.println("Replacing apples from the box with apples to new empty box");
        appleBox.replaceFruitsTo(emptyAppleBox);
        System.out.println("A box with apples weighs: "+ appleBox.weight());
        System.out.println("A new box with apples weighs: "+ emptyAppleBox.weight());
    }
}
