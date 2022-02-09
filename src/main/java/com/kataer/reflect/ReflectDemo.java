package com.kataer.reflect;

import java.lang.reflect.Method;

public class ReflectDemo {
    public static void main(String[] args) {
        Method[] declaredMethods = DemoTask.class.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod.getName());
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        Method[] methods = DemoTask.class.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        Method[] superMethods = DemoTask.class.getSuperclass().getDeclaredMethods();
        for (Method superMethod : superMethods) {
            System.out.println(superMethod.getName());
        }
        DemoTask demoTask = new DemoTask();
        demoTask.runTask();
    }
}
