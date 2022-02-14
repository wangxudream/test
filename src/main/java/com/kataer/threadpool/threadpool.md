#### 池化技术

##### 池化技术思想

```text
1、核心思想
    核心思想是利用空间换取时间
    事先缓存一定的资源，在高请求的情况下能提升效率
2、实现
    线程池，连接池(数据库连接次，redis连接池)

```

##### 线程池

```text
1、避免频繁创建线程，提高执行效率
2、限制资源的占用
```

###### 线程池继承关系

```java
//只提供了execute接口
interface Executor {
  void execute(Runnable command);
}

//增加了线程池服务管理相关接口
interface ExecutorService extends Executor {
  void shutdown();

  <T> Future<T> submit(Callable<T> task);

  <T> Future<T> submit(Runnable task, T result);
}

//实现具体的方法
abstract class AbstractExecutorService implements ExecutorService {

}

//线程池实现
class ThreadPoolExecutor extends AbstractExecutorService {

}
```

###### ThreadPoolExecutor参数

##### Core and maximum pool sizes

```text
1、当任务被提交时，线程数少于核心线程数时，会创建新的线程来处理该任务，即使其它线程处于空闲状态
2、当线程数大于核心线程数且任务队列满时才会创建线程
3、通常这两个参数通过构造传递，但是也可以通过set方法动态调整
4、通过prestartCoreThread or prestartAllCoreThreads可以提前创建核心线程

```

##### ThreadFactory

```text
用于创建新的线程，不指定的情况下会使用defaultThreadFactory
默认情况下创建的线程在相同group，NORM_PRIORITY，non-daemon
```

##### Keep-alive times

```text
线程的空闲时间，可以动态调整
使用Long.MAX_VALUE防止线程被回收
allowCoreThreadTimeOut 可以通过调整该参数使核心线程回收
```

##### Queuing

```text
1、少于核心线程数 创建线程接受task
2、线程数达到核心线程数时，任务会被存储在队列中
3、任务无法进入队列，假如线程数少于核心线程数，会创建新的线程处理任务
4、任务无法进入队列，线程数达到最大值时，任务会被拒绝
general strategies for queuing
存储任务的三种策略
1、Direct handoffs 直接处理
 可以使用SynchronousQueue
 该队列的capacity为0，每一次insert操作，必须等待其他线性的remove操作。而每一个remove操作也必须等待其他线程的insert操作。
 通常最大线程数设置为最大时使用该策略
2、Unbounded queues 使用无界队列 
  可以使用LinkedBlockingQueue
  通常结合较小的核心线程数使用，可以减少cpu的使用，线程上下文切换带来的消耗
  但也可能人为的减少了吞吐量
3、Bounded queues 有界队列
  可以使用ArrayBlockingQueue
  通常线程数需要设置较高的线程数以避免触发拒绝策略，坏处是可能使cpu繁忙，反而降低吞吐量
```

##### Rejected tasks 拒绝任务

```java
interface RejectedExecutionHandler {
  void rejectedExecution(Runnable r, ThreadPoolExecutor executor);
}
/*
 * 1、AbortPolicy
 * 抛出RejectedExecutionException
 * 2、CallerRunsPolicy
 * 线程池未关闭则调用当前线程执行
 * 3、DiscardOldestPolicy
 * 丢弃最久的任务，然后提交任务
 * if (!e.isShutdown()) {
 * e.getQueue().poll();
 * e.execute(r);
 * }
 * 4、DiscardPolicy
 * 直接丢弃，实现的接口中没有任何代码
 */
```

##### Hook methods

```java
protected void beforeExecute(Thread t,Runnable r){}
protected void afterExecute(Runnable r,Throwable t){}
```

##### Queue maintenance 队列维护

```text
public BlockingQueue<Runnable> getQueue() 
public boolean remove(   Runnable task )
public void purge() 清除
可以获取队列用于监听和维护
```
##### Finalization
```text
线程池没有被引用或者没有线程时会回收reclaimed
通过设置适当的保持活动时间、使用零核心线程的下限和或设置 allowCoreThreadTimeOut(boolean) 来安排未使用的线程最终死亡。
```