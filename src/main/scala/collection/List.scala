//package collection
//
///**
// * Created by xiongfeng on 15/6/14.
// */
//
//abstract class Base
//
//case object A extends Base
//
//case object U extends Base
//
//case object G extends Base
//
//case object C extends Base
//
//object Base {
//  val fromInt: Int => Base = Array(A, U, G, C)
//  val toInt: Base => Int = Map(A -> 0, U -> 1, G -> 2, C -> 3)
//  List(1,2,3).
//}

object testList extends App {
  a
  def a: Unit = {
    List(1,2,3).map { x =>
      println(x)
      x
    }.map { x =>
      println(x)
      x
    }
  }
}