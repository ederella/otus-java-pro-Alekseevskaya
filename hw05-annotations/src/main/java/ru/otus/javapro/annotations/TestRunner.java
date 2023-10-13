package ru.otus.javapro.annotations;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

public class TestRunner {
    public static void run(Class<?> cls) throws Exception {
        HashMap<String, ArrayList<Method>> methods = divideAnnotedMethods(cls.getDeclaredMethods());

        if (!methods.get("Repeated").isEmpty()) {
            printIncorrectAnnotations(methods.get("Repeated"));
            return;
        }

        int successCount = 0;
        for (Method method : methods.get("Test")) {
            method.setAccessible(true);
            Object obj = method.getDeclaringClass().getDeclaredConstructor().newInstance();

            try {
                for (Method methodBefore : methods.get("Before")) {
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

            for (Method methodAfter : methods.get("After")) {
                methodAfter.setAccessible(true);
                System.out.println("call " + getStringName(methodAfter));
                methodAfter.invoke(obj);
            }
        }
        printReport(successCount, methods.get("Test"));
    }

    private static HashMap<String, ArrayList<Method>> divideAnnotedMethods(Method[] declaredMethods) {
        HashMap<String, ArrayList<Method>> dividedMethods = new HashMap<>();
        dividedMethods.put("Test", new ArrayList<>());
        dividedMethods.put("Before", new ArrayList<>());
        dividedMethods.put("After", new ArrayList<>());
        dividedMethods.put("Repeated", new ArrayList<>());

        for (Method method : declaredMethods) {

            int methodAnnotationCount = 0;
            if (method.getAnnotation(Test.class) != null) {
                dividedMethods.get("Test").add(method);
                methodAnnotationCount++;
            }
            if (method.getAnnotation(Before.class) != null) {
                dividedMethods.get("Before").add(method);
                methodAnnotationCount++;
            }
            if (method.getAnnotation(After.class) != null) {
                dividedMethods.get("After").add(method);
                methodAnnotationCount++;
            }
            if (methodAnnotationCount > 1) {
                dividedMethods.get("Repeated").add(method);
            }
        }

        return dividedMethods;
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

