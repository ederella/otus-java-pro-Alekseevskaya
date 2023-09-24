package ru.otus.javapro;

import ru.otus.javapro.annotations.TestRunner;
import ru.otus.javapro.testclasses.AnotherTestCLass;
import ru.otus.javapro.testclasses.TestCLass;

public class Main {
    public static void main(String[] args) throws Exception {
        TestRunner tst = new TestRunner(TestCLass.class);
        tst.run();

        tst = new TestRunner(AnotherTestCLass.class);
        tst.run();
    }
}