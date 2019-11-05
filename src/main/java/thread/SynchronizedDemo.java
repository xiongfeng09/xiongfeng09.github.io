package thread;

public class SynchronizedDemo {
  private boolean ready = false;
  private int result = 0;
  private int number = 1;

  public void write() {
    ready = true;                 //1.1
    number = 2;                        //1.2
  }

  public void read() {
    if (ready) {                 //2.1
      result = number * 3;    //2.2
    }
    System.out.println("result=" + result);
  }

  class ReadWriteThread extends Thread {
    private boolean flag;

    public ReadWriteThread(boolean flag) {
      this.flag = flag;
    }

    @Override
    public void run() {
      System.out.println("in run");
      if (flag) {
        System.out.println("in write");
        write();
      } else {
        System.out.println("in read");
        read();
      }
    }
  }

  public static void main(String[] args) {
    SynchronizedDemo synDemo = new SynchronizedDemo();
    //写线程
    (synDemo.new ReadWriteThread(true)).start();
//    try {
//      Thread.sleep(1000);
//    } catch (InterruptedException e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }

    //读线程
    System.out.println(2);
    synDemo.new ReadWriteThread(false).start();
  }
}

