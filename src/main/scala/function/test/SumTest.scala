package function.test

object SumTest extends App {
  def sumInt(a: Int, b: Int) = a + b

  def sumString(a: String, b: String) = a + b

  def sum(a: A, b: A) = a.value + b.value

  def sum[T](a: T, b: T, addTrait: SumTrait[T]): T = addTrait.add(a, b)

  def sum1[T](a: T, b: T)(implicit addTrait: SumTrait[T]): T = addTrait.add(a, b)
}


trait SumTrait[T] {
  def add(a: T, b: T): T
}

object SumTrait {

  val intSumTrait = new SumTrait[Int] {
    def add(a: Int, b: Int) = a + b
  }
}


class A(val value: Int)