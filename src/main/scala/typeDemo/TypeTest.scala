package typeDemo

object test extends App {

//  def gernerate[T] = new Array[](0) doesn't compile
  def gernerate1[T](implicit m: Manifest[T]) = new Array[T](0)
  def gernerate2[T: Manifest] = new Array[T](0)

  gernerate1[String]

  trait B {
    def p: Unit
  }
  class StringB(a: String) extends B {
    def p = println("hello" + a)
  }
  implicit def string2B(a: String): B = {
    new StringB(a)
  }


  trait C[T] {
    def p: Unit
  }
  class StringC(c: String) extends C[String] {
    def p = println("hello" + c)
  }
  implicit def string2C(a: String): C[String] = {
    new StringC(a)
  }


  def a1[A](a: A)(implicit mf: A => B) = {
    val b = mf(a)
    b.p
  }


  def a2[A: C](a: A) = {
    val c = implicitly[C[A]]
  }

  def a3[A](a: A)(implicit cp : A => C[A]) = {
    cp(a).p
  }

  a1("111")
  a3("333")


  def max[T](a: T, b: T)(implicit cp: Ordering[T]) = {
    if (cp.compare(a, b) > 0) a else b
  }

  def max2[T: Ordering](a: T, b: T) = {
    val cp = implicitly[Ordering[T]]
    if (cp.compare(a, b) > 0) a else b
  }

  println(max(1, 2))
  println(max(1, 2))

  class A(name: String) {
    def sayHello = println("hi," + name)
  }

  implicit def string2A(name: String) = new A(name)

  "xiongfeng".sayHello
}