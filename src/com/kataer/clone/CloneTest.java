package com.kataer.clone;

public class CloneTest {
  public static void main(String[] args) throws CloneNotSupportedException {
    User user = new User();
    user.setName("aaa");
    user.setAge("18");
    Resp<User> resp = new Resp<User>();
    resp.setType("aaaa");
    resp.setT(user);
    resp.setUser(user);

    Resp resp1 = (Resp) resp.clone();

    System.out.println(resp1 == resp);
    System.out.println(resp.getT() == resp1.getT());
    System.out.println(resp.getUser() == resp1.getUser());
    System.out.println(resp.getType() == resp1.getType());
  }
}
