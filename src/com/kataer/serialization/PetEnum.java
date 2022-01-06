package com.kataer.serialization;

/**
 * @author kataer
 * @description: TODO
 * @date 2022/1/5.
 */
public enum PetEnum {
  DOG(1, "dog"),
  CAT(2, "cat");
  private final int type;
  private final String name;

  PetEnum(int type, String name) {
    this.type = type;
    this.name = name;
  }
}
