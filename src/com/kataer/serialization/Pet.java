package com.kataer.serialization;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 * @author kataer
 * @description: TODO
 * @date 2022/1/5.
 */
public class Pet implements Serializable {
  /**
   * 1、序列化和反序列化时serialVersionUID值需要相同
   * local class incompatible: stream classdesc serialVersionUID = -6849794470754667710, local class serialVersionUID = 1
   * 2、静态变量不会被序列化
   */
  private static final long serialVersionUID = 1L;
  private String name;
  private Integer age;
  /**
   * transient 指定字段不会被序列化
   * 反序列化后取默认值 0 null
   */
  private transient String country;

  public String getName() {
    return name;
  }

  public Integer getAge() {
    return age;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }


  /**
   * 在类中增加writeObject 和 readObject 方法可以实现自定义序列化策略
   *
   * @param in
   * @throws IOException
   * @throws ClassNotFoundException
   */
  private void readObject(ObjectInputStream in) throws IOException,
      ClassNotFoundException {
    throw new InvalidObjectException("can't deserialize Pet");
  }

  @Override
  public String toString() {
    return "Pet{" +
        "name='" + name + '\'' +
        ", age=" + age +
        ", country='" + country + '\'' +
        '}';
  }
}
