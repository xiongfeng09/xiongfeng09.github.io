---
layout: post
title: countDownLatch的基本用法
category: java
tags: java
description: countDownLatch的基本用法
---

### 1.用于等待所有线程结束做统一处理

```
private void test1() throws InterruptedException {
    final int N = 16;
    CountDownLatch countDownLatch = new CountDownLatch(N);

    for (int i = 0; i < N; ++i)
        new Thread(() -> {
            System.out.println(Thread.currentThread());
            countDownLatch.countDown();
        }, "thread" + N).start();

    try {
        countDownLatch.await();
        System.out.println("done");
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

}

```

这里创建了N个线程,和初始化为N的 CountDownLatch,当所有的线程执行完毕后 输出done;


### 2.多了一个`startSignal`用于控制线程多个线程同步的开始执行

```
private void test2() throws InterruptedException {
        final int N = 16;
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(N);

        for (int i = 0; i < N; ++i)
            new Thread(() -> {
                try {
                    startSignal.await();
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                doneSignal.countDown();
            }, "thread" + i).start();


        long startTime = System.currentTimeMillis();
        //do something
        System.out.println("don't let run yet");
        startSignal.countDown();
        //do something else
        System.out.println("let all threads proceed");
        try {
            doneSignal.await();
            System.out.println("all threads done, time " + (System.currentTimeMillis() - startTime));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
```

### 3.关于 AQS 的实现

```
    /**
     * Synchronization control For CountDownLatch.
     * Uses AQS state to represent count.
     */
    private static final class Sync extends AbstractQueuedSynchronizer {
        private static final long serialVersionUID = 4982264981922014374L;

        Sync(int count) {
            setState(count);
        }

        int getCount() {
            return getState();
        }

        protected int tryAcquireShared(int acquires) {
            return (getState() == 0) ? 1 : -1;
        }

        protected boolean tryReleaseShared(int releases) {
            // Decrement count; signal when transition to zero
            for (;;) {
                int c = getState();
                if (c == 0)
                    return false;
                int nextc = c-1;
                if (compareAndSetState(c, nextc))
                    return nextc == 0;
            }
        }
    }
```
