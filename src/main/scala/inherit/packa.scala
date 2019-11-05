package inherit

/**
 * Created by xiongfeng on 15/7/16.
 */
object packa {
  val b = new B
}

trait AAA {
  protected def sayHello = "1"
}

class B extends AAA {
  sayHello
}
