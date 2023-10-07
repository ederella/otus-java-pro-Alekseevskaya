package ru.otus.javapro.annotations;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class TestRunner {
    public static void run(Class<?> cls) throws Exception {
        MethodArrays ma = new MethodArrays(cls.getDeclaredMethods());

        if (!ma.methodsRepeated.isEmpty()) {
            printIncorrectAnnotations(ma.methodsRepeated);
            return;
        }

        int successCount = 0;
        for (Method method : ma.methodsTest) {
            method.setAccessible(true);
            Object obj = method.getDeclaringClass().getDeclaredConstructor().newInstance();

            try {
                for (Method methodBefore : ma.methodsBefore) {
                    methodBefore.setAccessible(true);
                    System.out.println("call " + getStringName(methodBefore));
                    methodBefore.invoke(obj);
                }
                System.out.println("call " + getStringName(method));
                method.invoke(obj);
                successCount++;

            } catch (Throwable e) {
                System.out.println(e.getMessage());
            }

            for (Method methodAfter : ma.methodsAfter) {
                methodAfter.setAccessible(true);
                System.out.println("call " + getStringName(methodAfter));
                methodAfter.invoke(obj);
            }
        }
        printReport(successCount, ma.methodsTest);
    }

    private static void printIncorrectAnnotations(ArrayList<Method> methodsRepeated) {
        System.out.println("---------REPORT--------");
        for (Method method : methodsRepeated) {
            System.out.println("Method " + method.getName() + " has repeated annotations - test was skipped");
        }
        System.out.println("----------------------");
    }

    private static String getStringName(Method method) {
        return method.getDeclaringClass().getName() + '.' + method.getName();
    }

    private static void printReport(int successCount, ArrayList<Method> methodsTest) {
        System.out.println("---------REPORT--------");
        System.out.println("TOTAL: " + methodsTest.size());
        System.out.println("SUCCESS: " + successCount);
        System.out.println("----------------------");
    }
}

