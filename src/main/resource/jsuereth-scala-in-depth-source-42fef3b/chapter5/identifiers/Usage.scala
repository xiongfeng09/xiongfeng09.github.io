package jsuereth-scala-in-depth-source-42fef3b.chapter5.identifiers

import test.{Foo=>Bar}

object Test {
  def main(args: Array[String]) : Unit = {
    val x = new Bar
    println( "Created new: " + x)
  }
}
