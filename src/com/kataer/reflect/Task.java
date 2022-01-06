package com.kataer.reflect;

import java.lang.reflect.Field;

/**
 * @author kataer
 * @version 1.0
 * @description: 定时任务
 * @date 2021/3/17 19:30:57
 */
public abstract class Task {
    public void runTask() {
        System.out.println("开始执行任务>>>>>>>>>>>>>>>>>>>");
        goodsSync();
        dailyCount();
    }

    public void hello() {

    }


    protected abstract void goodsSync();

    protected void dailyCount() {
        System.out.println("开始统计>>>>>>>>>>>>>>>>>>>");
        System.out.println(this.getClass().getName());
        try {
            Field field = this.getClass().getDeclaredField("ID");
            field.setAccessible(true);
            System.out.println((Integer) field.get(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
