package com.kataer.clone;

public class Resp<T extends Cloneable> implements Cloneable {
  private String type;
  private T t;
  private User user;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public T getT() {
    return t;
  }

  public void setT(T t) {
    this.t = t;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public Object clone() throws CloneNotSupportedException {
    Resp resp = (Resp) super.clone();
    resp.user = (User) resp.user.clone();
    return resp;
  }


}
