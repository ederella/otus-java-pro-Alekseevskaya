package ru.otus.javapro;

import ru.otus.javapro.input.sms.Sms;
import ru.otus.javapro.output.OutputList;

import java.io.IOException;

public class Main {
    public static void main(String... args) throws IOException {
        Sms sms = Sms.deserializeFromFile("hw08-serialization\\src\\main\\resources\\sms-348536-95ecd0.json");

        OutputList list = new OutputList(sms);
        list.serializeCSVtoFile("hw08-serialization\\src\\main\\resources\\output.csv");

        list = new OutputList().deserializeFromCSV("hw08-serialization\\src\\main\\resources\\output.csv");
        list.getList().forEach(System.out::println);
    }
}