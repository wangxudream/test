package com.kataer.stream;

import java.util.Objects;

public class User {
  private String name;
  private Integer age;
  private String address;
  private Boolean gender;
  private Double salary;

  public User() {
  }

  public User(String name, Integer age) {
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

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Boolean getGender() {
    return gender;
  }

  public void setGender(Boolean gender) {
    this.gender = gender;
  }

  public Double getSalary() {
    return salary;
  }

  public void setSalary(Double salary) {
    this.salary = salary;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof User)) return false;
    User user = (User) o;
    return Objects.equals(getName(), user.getName()) &&
        Objects.equals(getAge(), user.getAge()) &&
        Objects.equals(getAddress(), user.getAddress()) &&
        Objects.equals(getGender(), user.getGender()) &&
        Objects.equals(getSalary(), user.getSalary());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName(), getAge(), getAddress(), getGender(), getSalary());
  }

  @Override
  public String toString() {
    return "User{" +
        "name='" + name + '\'' +
        ", age=" + age +
        ", address='" + address + '\'' +
        ", gender=" + gender +
        ", salary=" + salary +
        '}';
  }
}
