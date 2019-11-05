package typeDemo

import scala.annotation.implicitNotFound

object TypeProve extends App {
  List(1,2,3).combinations(2).toList.foreach{a =>
    println("---")
    a.permutations.toList.foreach { l =>
      l.foreach(print);println()
    }
  }

//  @implicitNotFound(msg = "Cannot prove that ${From} <:< ${To}.")
//  sealed abstract class <:<[-From, +To] extends (From => To) with Serializable
//
//  private[this] final val singleton_<:< = new <:<[Any, Any] {
//    def apply(x: Any): Any = x
//  }
//
//  // not in the <:< companion object because it is also
//  // intended to subsume identity (which is no longer implicit)
//  implicit def conforms[A]: A <:< A = singleton_<:<.asInstanceOf[A <:< A]
//
//  def firstLast[A, C](it: C)(implicit ev: C <:< Iterable[A]): (A, A) = {
//    println(ev)
//    (it.head, it.last)
//  }
//
//  firstLast(List(1,2))
//
//  def a(x:Int)(implicit y: Int) = {}
}

