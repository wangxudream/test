package com.kataer.collections;

import java.security.MessageDigest;
import java.util.Objects;

public class SHA {
  public static void main(String[] args) {
    String str = "jsapi_ticket=sM4AOVdWfPE4DxkXGEs8VMCPGGVi4C3VM0P37wVUCFvkVAy_90u5h9nbSlYy3-Sl-HhTdfl2fzFy1AOcHKP7qg&noncestr=Wm3WZYTPz0wzccnW&timestamp=1414587457&url=http://mp.weixin.qq.com?params=value";
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-1");
      byte[] bytes = str.getBytes("UTF8");
      byte[] md5Bytes = digest.digest(bytes);
      StringBuffer hexValue = new StringBuffer();
      for (int i = 0; i < md5Bytes.length; i++) {
        int val = ((int) md5Bytes[i]) & 0xff;
        if (val < 16) {
          hexValue.append("0");
        }
        hexValue.append(Integer.toHexString(val));
      }
      System.out.println(Objects.equals("0f9de62fce790f9a083d5c99e95740ceb90c27ed", "0f9de62fce790f9a083d5c99e95740ceb90c27ed"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
