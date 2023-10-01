package ru.otus.javapro.annotations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class TestRunner {
    private final ArrayList <Method> methodsBefore;
    private final ArrayList <Method> methodsTest;
    private final ArrayList<Method> methodsAfter;
    private final ArrayList<Method> methodsRepeated;

    public TestRunner(Class<?> cls) {

        methodsBefore = new ArrayList<>();
        methodsTest = new ArrayList<>();
        methodsAfter = new ArrayList<>();
        methodsRepeated = new ArrayList<>();

        Method[] methods = cls.getDeclaredMethods();

        for (Method method : methods) {

            int methodAnnotationCount = 0;
            if (method.getAnnotation(Test.class) != null) {
                methodsTest.add(method);
                methodAnnotationCount++;
            }
            if (method.getAnnotation(Before.class) != null) {
                methodsBefore.add(method);
                methodAnnotationCount++;
            }
            if (method.getAnnotation(After.class) != null) {
                methodsAfter.add(method);
                methodAnnotationCount++;
            }
            if (methodAnnotationCount > 1) {
                methodsRepeated.add(method);
            }
        }
    }
    public void run() throws Exception {
        int successCount = 0;
        if(!methodsRepeated.isEmpty()){
            printIncorrectAnnotations();
            return;
        }
        for(Method method: methodsTest){
            method.setAccessible(true);
            Object obj = method.getDeclaringClass().getDeclaredConstructor().newInstance();

            try {
                runAllBefore(obj);
                System.out.println("call " + getStringName(method));
                method.invoke(obj);
                successCount++;

            } catch (Throwable e) {
                System.out.println(e.getMessage());
            }

            runAllAfter(obj);
        }
        printReport(successCount);
    }

    private void printReport(int successCount) {
        System.out.println("---------REPORT--------");
        System.out.println("TOTAL: "+ methodsTest.size());
        System.out.println("SUCCESS: "+ successCount);
        System.out.println("----------------------");
    }
    private void printIncorrectAnnotations(){
        System.out.println("---------REPORT--------");
        for (Method method: methodsRepeated) {
            System.out.println("Method "+ method.getName() + " has repeated annotations - test was skipped");
        }
        System.out.println("----------------------");
    }
    private static String getStringName(Method method) {
        return method.getDeclaringClass().getName() + '.' + method.getName();
    }

    private void runAllAfter(Object obj) throws IllegalAccessException, InvocationTargetException {
        for (Method methodAfter: methodsAfter) {
            methodAfter.setAccessible(true);
            System.out.println("call "+getStringName(methodAfter));
            methodAfter.invoke(obj);
        }
    }

    private void runAllBefore(Object obj) throws IllegalAccessException, InvocationTargetException {
        for (Method methodBefore: methodsBefore) {
            methodBefore.setAccessible(true);
            System.out.println("call "+getStringName(methodBefore));
            methodBefore.invoke(obj);
        }
    }
}
