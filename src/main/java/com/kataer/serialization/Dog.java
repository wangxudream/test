package com.kataer.serialization;

/**
 * @author kataer
 * @date 2022/1/5.
 */
public class Dog extends Pet {
  private static final long serialVersionUID = 1L;
  private Integer type;

  public Dog(Integer type) {
    this.type = type;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "Dog{" +
        "type=" + type +
        '}';
  }
}
