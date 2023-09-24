package ru.otus.javapro.annotations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class TestRunner {
    private ArrayList <Method> methodsBefore;
    private ArrayList <Method> methodsTest;
    private ArrayList<Method> methodsAfter;

    public TestRunner(Class<?> cls) throws Exception {

        methodsBefore = new ArrayList<>();
        methodsTest = new ArrayList<>();
        methodsAfter = new ArrayList<>();

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
                throw new Exception("Annotations should not be repeated");
            }
        }
    }
    public void run() throws Exception {
        int successCount = 0;
        for(Method method: methodsTest){
            method.setAccessible(true);
            Object obj = method.getDeclaringClass().getDeclaredConstructor().newInstance();

            try {
                runAllBefore(obj);
                System.out.println("call " + getStringName(method));
                method.invoke(obj);
                successCount++;

            } catch (Throwable e) {
                e.printStackTrace();
            }

            runAllAfter(obj);
        }
        System.out.println("---------REPORT--------");
        System.out.println("TOTAL: "+ methodsTest.size());
        System.out.println("SUCCESS: "+ successCount);
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
