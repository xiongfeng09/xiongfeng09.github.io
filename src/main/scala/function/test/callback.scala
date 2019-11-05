package function.test

case class CallBackFunction(name: String, fromUser: String, toUser: String, content: String, url: String)
object callback extends App {
  val func: PartialFunction[CallBackFunction, Unit]  = {
    case CallBackFunction("template", fromUser, toUser, content, _) => println(fromUser)
    case _ => sys.error("invalid callback function name")
  }

  func(CallBackFunction("aa", "xiongfeng", "sun", "nihao", "url"))
}

object testAAA extends App {
  def find(s: String): Object = {
    println(s)
    s
  }

  def find2(s: Object): String = {
    println(s)
    s.toString
  }

  def mapList(list: List[String], f: String => Object) = {
    list.map(f)
  }

  mapList(List("a", "b"), find)
  mapList(List("a", "b"), find2)
}