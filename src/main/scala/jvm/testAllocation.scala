package jvm

/**
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 */
object testAllocation extends App {
//  Thread.sleep(10000)
  val _1M = 1024 * 1024


//  println("b1")
//  Thread.sleep(5000)
  val b1 = new Array[Byte](2 * _1M)
//  println("b2")
//  Thread.sleep(5000)
  val b2 = new Array[Byte](2 * _1M)
//  println("b3")
//  Thread.sleep(5000)
  val b3 = new Array[Byte](2 * _1M)
//  println("b4")
//  Thread.sleep(5000)
  val b4 = new Array[Byte](4 * _1M)
}
