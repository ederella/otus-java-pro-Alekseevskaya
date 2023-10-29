package ru.otus.javapro.testclasses;

import ru.otus.javapro.annotations.After;
import ru.otus.javapro.annotations.Before;
import ru.otus.javapro.annotations.Test;

public class TestCLass {

    String someString;

    @Before
    void before() {
        someString = "Otus";
    }

    @Before
    void beforePrint() {
        System.out.println("Start");
    }

    @Test
    void testOk() {
        char ch = someString.charAt(1);
        System.out.println(ch);
    }

    @Test
    void testException() {
        char ch = someString.charAt(1000);
        System.out.println(ch);
    }

    @Test
    void testOkAgain() {
        System.out.println(someString.toUpperCase());
    }

    @After
    void after() {
        someString = null;
    }

    @After
    void afterPrint() {
        System.out.println("Finish");
    }

}
