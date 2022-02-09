package com.kataer.reflect;

public interface DemoInterface {
    void say(String str);

    default void sayHello() {
        say("String");
    }
}
