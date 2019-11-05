package typeDemo

object MainfestTest extends App {
  def foo[T](x: List[T])(implicit m: Manifest[T]) = {
    if (m <:< manifest[String])
      println("Hey, this list is full of strings")
    else
      println("Non-stringy list")
  }

//  CareerProfiles(profiles: List[CareerProfile])

//  foo(List.empty[String])
//  foo(List.empty[Int])


  import scala.reflect.runtime.universe

  def arrayConformsTo[A: universe.TypeTag](as: Array[_]) = {
    val mirror = universe.runtimeMirror(getClass.getClassLoader)
    val classSym = mirror.classSymbol(as.getClass.getComponentType)
    println(classSym.toType <:< implicitly[universe.TypeTag[A]].tpe)
  }

//  def arrayConformsTo[A](as: Array[_])(implicit arrayOfA: ClassTag[Array[A]]) = as match {
//    case arrayOfA(_) => true
//    case _ => false
//  }
//
//  arrayConformsTo[Float](Array[Float]())
//  arrayConformsTo[Int](Array[Float]())
//  def arrayConformsTo[A](as: Array[_])(implicit t: ClassTag[A]) = {
//    ClassTag(as.getClass().getComponentType()) equals t
//  }

  import scala.reflect.runtime.universe._

  def paramInfo[T](x: T)(implicit tag: TypeTag[T]): Unit = {
    val targs = tag.tpe match {
      case TypeRef(_, _, args) => args
    }
    println(s"type of $x has type arguments $targs")
  }


  def first[A: ClassManifest](x: Array[A]) = println(x)
//  first(Array(1,2,3))


  def foo1[A](x: List[A])(f: A => Boolean) = null
//  foo1(List("1"))(_.isEmpty)

  def foo2[A](x: List[A], f: A => Boolean) = null
//  foo1(List("1"), x => x.isEmpty)

  def peek[A, C <: Traversable[A]](col: C) = println(col.head, col)

  //peek(List(1,2,3))

  def peek1[C, A](col: C)(implicit ev: C <:< Traversable[A]): (A, C) = (col.head, col)

//  peek1(List(1, 2, 3))


//  paramInfo(List(Array(1)))
//  paramInfo("adjflaksdf")
//  paramInfo(1231232)
}

