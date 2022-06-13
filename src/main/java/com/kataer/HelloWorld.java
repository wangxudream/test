package com.kataer;

import cn.hutool.crypto.digest.MD5;
import lombok.extern.slf4j.Slf4j;

/**
 * @author kataer
 * @description: 测试打包的启动类
 * @date 2022/2/10
 * 1654744850441
 * a5dcd3d9e0ddca24d699e845a2db1b83
 */
@Slf4j
public class HelloWorld {
  public static void main(String[] args) {
    long l = System.currentTimeMillis();
    System.out.println(l);
    final StringBuilder sb = new StringBuilder()
        .append("2018513694752768")
        .append(l)
        .append("123456")
        .append("{\n" +
            "  \"serialNo\": \"F0429058AEDACB8A8F91F5379C96B6D05194F219CF554649F1C4F9C615435A82\",\n" +
            "  \"wxId\": \"44309F35E82125DDB52F942BACD5C9275194F219CF554649F1C4F9C615435A82\"\n" +
            "}");
    String localSign = sb.append("12133908afdfc32a865f3e3f4a0cdadf4ec773c9").toString();
    String digestHex = MD5.create().digestHex(localSign);
    System.out.println(digestHex);
  }
}
