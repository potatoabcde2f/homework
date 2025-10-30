package com.my.blog.website;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;
import java.util.concurrent.Future;

/**
 * 异步调用测试类
 * 演示Spring的@Async注解的使用，测试多任务异步执行的性能
 * 通过模拟三个耗时任务，验证异步执行能够提高程序执行效率
 * 使用Future对象获取异步任务的执行结果
 */
@RunWith(SpringRunner.class)                    // 使用Spring测试运行器
@SpringBootTest                                 // Spring Boot测试环境
@EnableAsync                                    // 启用Spring的异步执行功能
public class AsyncTest {

    @Autowired
    private Task task;                          // 注入异步任务组件

    /**
     * 测试异步任务执行
     * 启动三个异步任务，并等待所有任务完成，统计总执行时间
     * 由于任务是并行执行的，总执行时间应该接近最慢任务的时间，而不是三个任务时间的总和
     */
    @Test
    public void Test() throws Exception {

        long start = System.currentTimeMillis(); // 记录测试开始时间

        // 启动三个异步任务，返回Future对象用于获取任务执行结果
        Future<String> task1 = task.doTaskOne();
        Future<String> task2 = task.doTaskTwo();
        Future<String> task3 = task.doTaskThree();

        // 循环等待所有任务完成
        while(true) {
            // 检查三个任务是否都已完成
            if(task1.isDone() && task2.isDone() && task3.isDone()) {
                // 三个任务都调用完成，退出循环等待
                break;
            }
            Thread.sleep(1000);                 // 每次检查间隔1秒，避免过度消耗CPU
        }

        long end = System.currentTimeMillis();   // 记录测试结束时间
        System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
    }

}

/**
 * 异步任务组件类
 * 包含三个模拟的异步任务方法，每个方法都有随机的执行时间
 */
@Component
class Task{

    private static Random random =new Random();  // 随机数生成器，用于模拟任务执行时间

    /**
     * 异步任务一
     * 模拟一个耗时任务，执行时间在0-10秒之间随机
     */
    @Async
    Future<String> doTaskOne() throws Exception {
        System.out.println("开始做任务一");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));    // 随机休眠0-10秒
        long end = System.currentTimeMillis();
        System.out.println("完成任务一，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务一OK");     // 返回异步执行结果
    }

    /**
     * 异步任务二
     * 模拟另一个耗时任务，执行时间在0-10秒之间随机
     */
    @Async
    Future<String> doTaskTwo() throws Exception {
        System.out.println("开始做任务二");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));    // 随机休眠0-10秒
        long end = System.currentTimeMillis();
        System.out.println("完成任务二，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务二OK");     // 返回异步执行结果
    }

    /**
     * 异步任务三
     * 模拟第三个耗时任务，执行时间在0-10秒之间随机
     */
    @Async
    Future<String> doTaskThree() throws Exception {
        System.out.println("开始做任务三");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));    // 随机休眠0-10秒
        long end = System.currentTimeMillis();
        System.out.println("完成任务三，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务三OK");     // 返回异步执行结果
    }
}