package thread;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class VolatileDemo {
  public static volatile int race = 0;

  public static void increase() {
    race ++;
  }

  public static final int THREAD_COUNT = 20;

  public static void main(String[] args) {
    System.out.println("in main");
    int existNum = Thread.activeCount();
    System.out.println("in existNum" + existNum);
    Thread[] threads = new Thread[THREAD_COUNT];

    for (int i = 0; i < THREAD_COUNT; i ++) {
      threads[i] = new Thread(
        new Runnable() {
          @Override
          public void run() {
            for(int i = 0; i < 1000000; i ++) {
              increase();
            }
          }
        }
      );

      threads[i].start();
    }

    while (Thread.activeCount() > existNum) {
      System.out.println(Thread.activeCount());
      Thread.yield();
    }

    System.out.println(race);
  }

}
