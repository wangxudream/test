package com.kataer;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

public class IterorDemo {
    public static void main(String[] args) {
        Cat cat1 = new Cat("tom", 25);
        Cat cat2 = new Cat("tom", 26);

        HashSet<Cat> cats = new HashSet<>();
        cats.add(cat1);
        cats.add(cat2);
        Iterator<Cat> iterator = cats.iterator();
        while (iterator.hasNext()) {
            Cat cat = iterator.next();
            if (cat.getAge().equals(26)) {
                iterator.remove();
            }
        }
        System.out.println(cats);
    }

    private static class Cat {
        private String name;
        private Integer age;


        public Cat(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}
