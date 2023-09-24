package ru.otus.javapro.testclasses;

import ru.otus.javapro.annotations.After;
import ru.otus.javapro.annotations.Before;
import ru.otus.javapro.annotations.Test;

public class AnotherTestCLass {

    int someint;

    @Before
    void before() {
        someint = 100;
    }

    @Before
    void beforePrint() {

        System.out.println("Start another");
    }

    @Test
    @After
    void testOk() {
         System.out.println(someint / 10);
    }

    @Test
    void testException() {
        System.out.println(someint / 0);
    }

    @Test
    void testOkAgain() {
        System.out.println(someint);
    }

    @After
    void after() {
        someint = 0;
    }

    @After
    void afterPrint() {
        System.out.println("Finish another");
    }

}
