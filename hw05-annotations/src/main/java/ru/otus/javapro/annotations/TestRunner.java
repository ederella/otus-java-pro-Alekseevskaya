package ru.otus.javapro.annotations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class TestRunner {
    ArrayList <Method> methodsBefore;
    ArrayList <Method> methodsTest;
    ArrayList<Method> methodsAfter;

    public TestRunner(Class<?> cls) throws Exception {
        methodsBefore = new ArrayList<>();
        methodsTest = new ArrayList<>();
        methodsAfter = new ArrayList<>();

        Method[] methods = cls.getDeclaredMethods();

        for (Method method : methods) {

            int methodAnnotationCount = 0;
            if(method.getAnnotation(Test.class) !=null){
                methodsTest.add(method);
                methodAnnotationCount++;
            }
            if(method.getAnnotation(Before.class) !=null){
                methodsBefore.add(method);
                methodAnnotationCount++;
            }
            if(method.getAnnotation(After.class) !=null){
                methodsAfter.add(method);
                methodAnnotationCount++;
            }
            if(methodAnnotationCount > 1)
                throw new Exception("Annotations should not be repeated");
        }

    }
    public void run() throws Exception {
        for(Method method: methodsTest){
            method.setAccessible(true);
            Object obj = method.getDeclaringClass().getDeclaredConstructor().newInstance();

            try {
                runAllBefore(obj);
                method.invoke(obj);

            } catch (Throwable e) {
                e.printStackTrace();
            }

            runAllAfter(obj);
        }
    }

    private void runAllAfter(Object obj) throws IllegalAccessException, InvocationTargetException {
        for (Method methodAfter: methodsAfter) {
            methodAfter.setAccessible(true);
            methodAfter.invoke(obj);
        }
    }

    private void runAllBefore(Object obj) throws IllegalAccessException, InvocationTargetException {
        for (Method methodBefore: methodsBefore) {
            methodBefore.setAccessible(true);
            methodBefore.invoke(obj);
        }
    }

}
