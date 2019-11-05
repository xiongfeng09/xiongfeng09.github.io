package typeDemo.variance.base

/**
 * Created by xiongfeng on 15/7/1.
 */
trait Function[-Arg, +Retrun]

object FunctionTest extends App {
  val x = new Function[Any, String] {}

  val y: Function[Any, String] = x
  val y1: Function[Any, Any] = x
  val y2: Function[String, Any] = x


  val q: List[_] = List()
  val q1: List[A forSome { type A}] = q
}
