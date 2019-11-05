package jsuereth-scala-in-depth-source-42fef3b.chapter11.src.main.scala.scalax.functional

trait Monoid[A] {
  def zero: A
  def combine(a: A, b: A): A
}

object Monoid {}