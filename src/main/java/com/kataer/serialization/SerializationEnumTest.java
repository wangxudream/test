package com.kataer.serialization;

import java.io.*;

/**
 * @author kataer
 * @description: TODO
 * @date 2022/1/5.
 */
public class SerializationEnumTest {
  public static void main(String[] args) {
    ObjectOutputStream oss = null;
    try {
      oss = new ObjectOutputStream(new FileOutputStream("D:\\serializationEnum.txt"));
      oss.writeObject(PetEnum.CAT);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      close(oss);
    }

    ObjectInputStream ois = null;
    try {
      ois = new ObjectInputStream(new FileInputStream("D:\\serializationEnum.txt"));
      Object o = ois.readObject();
      PetEnum petEnum = (PetEnum) o;
      /**
       * 输出结果为true
       * 反序列化后依旧是同一个对象
       * 序列化除基本信息外只序列化了枚举的name
       * 反序列化通过valueOf查找枚举对象
       */
      System.out.println(petEnum == PetEnum.CAT);
      System.out.println(petEnum);
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

  /**
   * 【继承自Enum类，使用final修饰不可被继承】
   * public final class com.kataer.serialization.PetEnum extends java.lang.Enum<com.kataer.serialization.PetEnum> {
   *   【枚举对象作为常量】
   *   public static final com.kataer.serialization.PetEnum DOG;
   *
   *   public static final com.kataer.serialization.PetEnum CAT;
   *
   *   public static com.kataer.serialization.PetEnum[] values();
   *     Code:
   *        0: getstatic     #1                  // Field $VALUES:[Lcom/kataer/serialization/PetEnum;
   *        3: invokevirtual #2                  // Method "[Lcom/kataer/serialization/PetEnum;".clone:()Ljava/lang/Object;
   *        6: checkcast     #3                  // class "[Lcom/kataer/serialization/PetEnum;"
   *        9: areturn
   *
   *   public static com.kataer.serialization.PetEnum valueOf(java.lang.String);
   *     Code:
   *        0: ldc           #4                  // class com/kataer/serialization/PetEnum
   *        2: aload_0
   *        3: invokestatic  #5                  // Method java/lang/Enum.valueOf:(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;
   *        6: checkcast     #4                  // class com/kataer/serialization/PetEnum
   *        9: areturn
   *
   *   static {};
   *     Code:
   *        0: new           #4                  // class com/kataer/serialization/PetEnum
   *        3: dup
   *        4: ldc           #9                  // String DOG
   *        6: iconst_0
   *        7: iconst_1
   *        8: ldc           #10                 // String dog
   *       10: invokespecial #11                 // Method "<init>":(Ljava/lang/String;IILjava/lang/String;)V
   *       13: putstatic     #12                 // Field DOG:Lcom/kataer/serialization/PetEnum;
   *       16: new           #4                  // class com/kataer/serialization/PetEnum
   *       19: dup
   *       20: ldc           #13                 // String CAT
   *       22: iconst_1
   *       23: iconst_2
   *       24: ldc           #14                 // String cat
   *       26: invokespecial #11                 // Method "<init>":(Ljava/lang/String;IILjava/lang/String;)V
   *       29: putstatic     #15                 // Field CAT:Lcom/kataer/serialization/PetEnum;
   *       32: iconst_2
   *       33: anewarray     #4                  // class com/kataer/serialization/PetEnum
   *       36: dup
   *       37: iconst_0
   *       38: getstatic     #12                 // Field DOG:Lcom/kataer/serialization/PetEnum;
   *       41: aastore
   *       42: dup
   *       43: iconst_1
   *       44: getstatic     #15                 // Field CAT:Lcom/kataer/serialization/PetEnum;
   *       47: aastore
   *       48: putstatic     #1                  // Field $VALUES:[Lcom/kataer/serialization/PetEnum;
   *       51: return
   * }
   */
}
