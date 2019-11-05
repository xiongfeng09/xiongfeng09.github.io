package inherit

object Ant extends App {
  println()
}

class Creature {
  val range: Int = 10
  val env: Array[Int] = new Array[Int](range)
}

class Ant2 extends {
  override val range = 2
} with Creature
