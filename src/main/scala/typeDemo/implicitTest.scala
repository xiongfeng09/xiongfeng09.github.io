package typeDemo

object implicitTest extends App {
  B.start
}

trait CanBuildFrom[Repr]

trait TraversableLike[Repr] {
  def map(implicit a: CanBuildFrom[Repr]) = {
    println(a)
    List(1,2,3).map(_ + 1)
  }
}


class Y extends TraversableLike[Y]
object Y {
  implicit def x = new CanBuildFrom[Y] {}
}

object B {
  def start = {
    val y = new Y
    y.map
  }
}




