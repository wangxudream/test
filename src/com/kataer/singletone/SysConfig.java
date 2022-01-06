package com.kataer.singletone;

public class SysConfig {

  private static FlinkConfig flinkConfig;

  public static SysConfig getSysConfig() {
    return FlinkConfig.INSTANCE;
  }

  public static class FlinkConfig {
    private static SysConfig INSTANCE = new SysConfig();
  }
}
