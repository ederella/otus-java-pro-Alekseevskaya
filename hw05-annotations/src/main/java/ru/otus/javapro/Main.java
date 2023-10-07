package ru.otus.javapro;

import ru.otus.javapro.annotations.TestRunner;
import ru.otus.javapro.testclasses.AnotherTestCLass;
import ru.otus.javapro.testclasses.TestCLass;

public class Main {
    public static void main(String[] args) throws Exception {
        TestRunner.run(TestCLass.class);
        TestRunner.run(AnotherTestCLass.class);
    }
}