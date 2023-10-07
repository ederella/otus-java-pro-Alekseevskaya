package ru.otus.javapro.annotations;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class MethodArrays {
    ArrayList<Method> methodsTest;
    ArrayList<Method> methodsBefore;
    ArrayList<Method> methodsAfter;
    ArrayList<Method> methodsRepeated;

    public MethodArrays(Method[] methods) {
        methodsTest = new ArrayList<>();
        methodsBefore = new ArrayList<>();
        methodsAfter = new ArrayList<>();
        methodsRepeated = new ArrayList<>();
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

}
