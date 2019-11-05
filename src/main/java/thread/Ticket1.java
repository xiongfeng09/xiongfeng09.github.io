package thread;


/**
 * Created by xiongfeng on 15/10/10.
 */
public class Ticket1 {
  public static void main(String[] args) {
//    testThread();
    testRunable();
  }

  static void testThread() {
    TicketThread a = new TicketThread();
    TicketThread b = new TicketThread();
    TicketThread c = new TicketThread();
    a.start();
    b.start();
    c.start();
  }

  static void testRunable() {
    TicketRunable r = new TicketRunable();

    Thread a = new Thread(r);
    Thread b = new Thread(r);
    Thread c = new Thread(r);

    a.start();
    b.start();
    c.start();
  }
}


class TicketThread extends Thread {
  int count = 5;

  @Override
  public void run() {
    System.out.println("in run");
    while (count > 0) {
      System.out.println("卖了一张，还有张" + count);
      count--;
    }
  }
}

class TicketRunable implements Runnable {
  int count = 5;

  @Override
  public void run() {
    System.out.println("in run");
    while (count > 0) {
      System.out.println("卖了一张，还有张" + count);
      count--;
    }
  }
}

