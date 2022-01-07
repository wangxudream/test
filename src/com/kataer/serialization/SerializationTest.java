package com.kataer.serialization;

import java.io.*;

/**
 * @author kataer
 * @date 2022/1/7
 */
public class SerializationTest {
  public static void main(String[] args) {
    testSuper();
//    testSub();
  }

  public static void testSuper() {
    Pet pet = new Pet();
    pet.setName("pet");
    pet.setAge(200);
    pet.setCountry("china");
    ObjectOutputStream oss = null;
    try {
      oss = new ObjectOutputStream(new FileOutputStream("D:\\serialization.txt"));
      oss.writeObject(pet);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      close(oss);
    }

    ObjectInputStream ois = null;
    try {
      ois = new ObjectInputStream(new FileInputStream("D:\\serialization.txt"));
      Object o = ois.readObject();
      Pet pet1 = (Pet) o;
      System.out.println(pet1);
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    } finally {
      close(ois);
    }
  }

  public static void testSub() {
    Dog dog = new Dog(1);
    dog.setName("dog");
    dog.setAge(200);
    ObjectOutputStream oss = null;
    try {
      oss = new ObjectOutputStream(new FileOutputStream("D:\\serialization.txt"));
      oss.writeObject(dog);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      close(oss);
    }

    ObjectInputStream ois = null;
    try {
      ois = new ObjectInputStream(new FileInputStream("D:\\serialization.txt"));
      Object o = ois.readObject();
      Dog dog1 = (Dog) o;
      System.out.println(dog1);
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    } finally {
      close(ois);
    }
  }


  public static void close(Closeable closeable) {
    if (closeable != null) {
      try {
        closeable.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
