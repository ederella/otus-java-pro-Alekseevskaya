package ru.otus.processor.homework;

public class EvenException extends Exception {
    public EvenException() {
        new Exception("Even second is bad luck!");
    }
}
