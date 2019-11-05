package future

/**
  * Created by xiongfeng on 16/4/25.
  */
object javaFureTest extends App {

  def start: Unit = {
    val r1 = new Runnable() {
      def run() = {
        java.lang.Thread.sleep(1100)
        println("do something")
      }
    }


    val t1 = new Thread(r1, "r1")
    val t2 = new Thread(r1, "r2")
    val t3 = new Thread(r1, "r3")
    val t4 = new Thread(r1, "r4")


    t1.start()
    t2.start()
    t3.start()
    t4.start()

  }

}


