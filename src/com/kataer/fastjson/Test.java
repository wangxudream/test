package com.kataer.fastjson;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.Method;

public class Test {
  public static void main(String[] args) {
    VipUser vipUser = new VipUser();
    vipUser.setName("vip");
    vipUser.setAge("18");
    vipUser.setLevel("1");
    System.out.println(JSON.toJSONString(vipUser));
    User user = vipUser;
    Class<? extends User> aClass = user.getClass();
    Method[] methods = aClass.getMethods();
    for (Method method : methods) {
      String name = method.getName();
      System.out.println(name);
    }
    System.out.println(JSON.toJSONString(user));
  }
}

class User {
  private String name;
  private String age;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public String getTest() {
    System.out.println("test");
    return "test";
  }
  public String get() {
    System.out.println("get");
    return "get";
  }

  private void privateMethod(){

  }

}

class VipUser extends User {
  private String level;

  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }
}