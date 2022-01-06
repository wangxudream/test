package com.kataer.reflect;


public class DemoTask extends Task {
    private static final Integer ID = 1;

    @Override
    public void runTask() {
        super.runTask();
    }

    @Override
    protected void goodsSync() {
        System.out.println("开始同步>>>>>>>>>>>>>>>>>>>");
    }

    public void say() {
    }
}
