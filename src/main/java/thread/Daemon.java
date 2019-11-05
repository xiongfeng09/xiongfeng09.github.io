package thread;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Scanner;

/**
 * Created by xiongfeng on 15/10/10.
 */

class DaemonThread implements Runnable {
  @Override
  public void run() {
    System.out.println("进入守护进程" + Thread.currentThread().getName());
    write();
    System.out.println("结束守护进程" + Thread.currentThread().getName());
  }

  void write() {
    File f = new File("/tmp/testDaemon.txt");
    OutputStream o = null;
    try {
      o = new FileOutputStream(f, true);
      int count = 0;
      while(count < 999) {
        String s = "\r\nword" + count;
        o.write(s.getBytes());
        System.out.println("正在写文件" + Thread.currentThread().getName());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

public class Daemon {
  public static void main(String[] args) {
    System.out.println("主线程开始");
    DaemonThread d = new DaemonThread();


    Thread t = new Thread(d);
    t.setDaemon(true);
    t.start();

    Scanner c = new Scanner(System.in);
    c.next();

    System.out.println("主线程结束");
  }
}
