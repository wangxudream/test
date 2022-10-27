package com.kataer;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.digest.MD5;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.xml.internal.security.algorithms.implementations.SignatureDSA;
import com.sun.org.apache.xml.internal.security.signature.XMLSignatureException;
import lombok.extern.slf4j.Slf4j;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

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
//    long a = 1663238578000L;
//    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    System.out.println(format.format(new Date(a)));
//    MD5 md5 = MD5.create();
//    String digestHex = md5.digestHex("Gg9527Wee");
//    System.out.println(digestHex);
//    long l = Long.parseLong("1453706130522566656");
//    System.out.println(l);
//    HashMap<String, String> map = new HashMap<>();
//    map.put("1", "1");
//    map.put("2", "2");
//    map.put("3", "3");
//    HashMap<String, String> map_b = new HashMap<>();
//    map_b.put("2", "2");
//    boolean b = map.keySet().retainAll(map_b.keySet());
//    System.out.println(map);
//    String remove = map.remove("3");
//    System.out.println(remove);
//    System.out.println(map);
//    List<String> asList = Arrays.asList("1", "2");
//    Random random = new Random(System.currentTimeMillis());
//    for (int i = 0; i < 10; i++) {
//      System.out.println(random.nextInt(asList.size()));
//    }
//    AtomicInteger atomicInteger = new AtomicInteger(100);
//    int andSet = atomicInteger.getAndSet(101);
//    System.out.println(andSet);
//    String aaa = Base64.encode("我是铭鑫");
//    System.out.println(aaa);
//    System.out.println(Base64.decodeStr("5Li75pKt5ZOq6YeM5Lq6"));
//    JSONArray jsonObject = JSON.parseArray("[{\"actualPayment\":11900,\"productCnt\":1,\"productId\":\"10000000204505\",\"saleParam\":\"\\\" 粉色; 均码\\\"\",\"salePrice\":11900,\"skuId\":\"477460958\",\"thumbImg\":\"https://store.mp.video.tencent-cloud.com/161/20304/snscosdownload/SH/reserved/62e0a19300005f881efc8f31f8c59609000000a000004f50\",\"title\":\"连衣裙女春秋季女装V领收腰显瘦法式茶歇碎花裙子女潮MX21A1635\"}]");
//    System.out.println(jsonObject);
//    MD5 md5 = MD5.create();
//    String digestHex = md5.digestHex("Gg9527Wee");
//    System.out.println(digestHex);

    long l = System.currentTimeMillis();
    System.out.println(l);
    StringBuilder sb = new StringBuilder()
        .append("4137169018945536")
        .append(l)
        .append("123456")
        .append("{\n" +
            "  \"nactivityId\": \"1100007466\"\n" +
            "}");
    String localSign = sb.append("b461ca7b0f2a9e2d34ff0aa66bc2d3fe5204a622").toString();
    System.out.println(MD5.create().digestHex(localSign));
    /**
     * 0
     * 0 1
     * 01 10
     * 0110 1001
     * 01101001 10010110
     */
//    int n = 5;
//    int respIndex = 4;
//    int len = (int) Math.pow(2, n - 1);
//    System.out.println(Math.pow(2, 0));
//    byte[] res = new byte[len];
//    test(n, 1, res, respIndex);
//    System.out.println(Arrays.toString(res));
//    System.out.println(res[respIndex]);
  }

//  //  public
//  public static byte[] test(int n, int index, byte[] res, int respIndex) {
//    if (index < n) {
//      //加上原来的反转
//      int start = (int) Math.pow(2, index - 1);
//      for (int i = 0; i < start; i++) {
//        int temp = start + i;
//        res[temp] = (byte) (res[i] == 0 ? 1 : 0);
//        if (temp == respIndex) {
//          return res;
//        }
//      }
//      test(n, ++index, res, respIndex);
//    }
//    return res;
//  }
}
