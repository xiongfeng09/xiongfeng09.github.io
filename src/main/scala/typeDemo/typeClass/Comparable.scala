package typeDemo.typeClass

/**
 * Created by xiongfeng on 15/7/8.
 */
trait Comparable[T] {
  def compare(o: T): Int = this.hashCode - o.hashCode()
}

trait A extends Comparable[A]

//normal use
/*
  val a1 = new A {}
  val a2 = new A {}
  a1.compare(a2)
*/

//implicit method class use
trait B
object B {
  implicit def b2Comparable(b: B): Comparable[B] = {
    new B with Comparable[B]
  }
}


object testCompare extends App {
  val a1 = new A {}
  val a2 = new A {}
  a1.compare(a2)

  val b1 = new B {}
  val b2 = new B {}
  b1.compare(b2)
  List(1).sum

  //implicit value
  trait Comparable2[T] {
    def compare(x: T, y: T) = x.hashCode - y.hashCode
  }

  trait C
  object C {
    implicit val compA = new Comparable2[C]{}
  }
  val c1 = new C {}
  val c2 = new C {}

  def call[T](x: T, y: T)(implicit c: Comparable2[T]) = {
    c.compare(x, y)
  }

  implicit val compA = new Comparable2[C] {}
  print(call(c1, c2))
}
