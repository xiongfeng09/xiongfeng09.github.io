package typeDemo.typeLambda

trait X[T]
class IntX extends X[Int] {
  override def toString = "intX"
}

class StringX extends X[String] {
  override def toString = "stringX"
}

object typeLambdaBase extends App {
  def foo[T](x: T) = println(x)

  def bar[T[_]](x: T[_]) = println(x)
  def bar1[T[_]](x: T[Int]) = println(x)
  def bar2[T[Int]](x: T[Int]) = println(x)

  def typeLambd[M[_]](f: M[Int]) = println(f)
  typeLambd(new IntX)
  typeLambd[({type X1[F] = Function1[F, Unit]})#X1]((x: Int) => println(x))

}
