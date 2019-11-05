package typeDemo

object forSomeTest extends App {
  val x: List[_] = List()
  val y: List[X forSome { type X}] = List()
}

