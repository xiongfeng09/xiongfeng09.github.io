package thread;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    public static void main(String[] args) {
        try {
            new CountDownLatchTest().test1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        try {
            new CountDownLatchTest().test2();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void test1() throws InterruptedException {
        final int N = 16;
        CountDownLatch countDownLatch = new CountDownLatch(N);

        for (int i = 0; i < N; ++i)
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName());
                countDownLatch.countDown();
            }, "thread" + i).start();

        try {
            countDownLatch.await();
            System.out.println("done");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

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
}
