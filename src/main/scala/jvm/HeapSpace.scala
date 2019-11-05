package jvm

object HeapSpace extends App {
  class A

  val a = scala.collection.mutable.ListBuffer[A]()

//  Thread.sleep(10000)

  println("start")
  while (true) {
    a.+=(new A)
  }
}
