package function.test

trait Functor[T[_]] {
  def apply[A](a: A): T[A]
  def map[A, B](f: A => B): T[B]
}
