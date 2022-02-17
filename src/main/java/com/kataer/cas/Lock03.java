package com.kataer.cas;

/**
 * 1、调用AQS acquire
 * 2、调用tryAcquire实现
 * 3、tryAcquire 失败 调用 addWaiter 添加 acquireQueued 尝试添加到队列
 * 4、添加到队列失败则中断
 */
public class Lock03 {

}
