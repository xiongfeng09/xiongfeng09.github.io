package typeDemo.typeLambda

import net.liftweb.common.Box


trait Functor[A, F[_]] {
  def map[B](f: A => B): F[B]
}

object testFuntor extends App {

  implicit class ListFunctor[A](xs: List[A]) extends Functor[A, List] {
    def map[B](f: A => B): List[B] = xs.map(f)
  }

  implicit class BoxFunctor[A](x: Box[A]) extends Functor[A, Box] {
    def map[B](f: A => B): Box[B] = x.map(f)
  }

//  implicit class Fn1FunctorBefore[A, B](g: A => B) extends Functor[B, A => _] {
//    def map[C](f: B => C): (A => C) = a => f(g(a))
//  }

  implicit class Fn1Functor[A, B](g: A => B) extends Functor[B, ({type λ[α] = A => α})#λ] {
    def map[C](f: B => C): (A => C) = a => f(g(a))
  }

  val plusOne: Int => Int = { x => x + 1 }
  val timesTwo: Int => Int = { x => x * 2 }
  val itoa: Int => String = { x => x.toString }
  val plusOneTimesTwoToString: Int => String = plusOne.map(timesTwo).map(itoa)

}