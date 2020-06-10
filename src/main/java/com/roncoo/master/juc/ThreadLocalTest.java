package com.roncoo.master.juc;

import com.roncoo.master.redis.Person;

import java.util.concurrent.TimeUnit;

/**
 * @author wangmn
 * @version 1.0
 * @description
 * @date 2020/5/21 10:23
 */
public class ThreadLocalTest {
    private static ThreadLocal<Person> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(threadLocal.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                threadLocal.set(new Person("lisi", 11));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
}
