package com.kataer.collections;

/**
 * @author kataer
 * @version 1.0
 * @description: 商品展示VO
 * @date 2021/3/15 19:40:33
 */
public enum Gender {
    MAN(1, "man"), WOMEN(2, "women");
    /**
     * 编码
     */
    private Integer code;
    /**
     * 名称
     */
    private String name;

    Gender(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
