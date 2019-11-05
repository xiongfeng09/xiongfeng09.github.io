//package reflection
//import scala.reflect.runtime.universe
//import scala.reflect.runtime.universe._
//
///**
// * Created by xiongfeng on 15/8/4.
// */
//object reflection {
//
//}
//
//case class Person(name: String)
//
//object Instantiating {
//  val m = universe.runtimeMirror(getClass.getClassLoader)
//
//  val classPerson = universe.typeOf[Person].typeSymbol.asClass
//
//  val cm = m.reflectClass(classPerson)
//
//  val ctor = universe.typeOf[Person].declaration(universe.nme.CONSTRUCTOR).asMethod
//
//
//  val ctorm = cm.reflectConstructor(ctor)
//
//  val p = ctorm("Mike")
//}
//
//case class Purchase(name: String, orderNumber: Int, var shipped: Boolean)
//object AccessAndInvoking {
//  val p = Purchase("Jeff Lebowski", 23819, false)
//
//  val m = universe.runtimeMirror(p.getClass.getClassLoader)
//
//  val shippingTermSymb = universe.typeOf[Purchase].declaration(universe.newTermName("shipped")).asTerm
//
//  val im = m.reflect(p)
//  val shippingFieldMirror = im.reflectField(shippingTermSymb)
//  shippingFieldMirror.get
//  shippingFieldMirror.set(true)
//}
//
//object trees {
////  val tree = Apply(Select(Ident(TermName("x")), TermName("$plus")), List(Literal(Constant(2))))
//  val expr = reify {
//    class Flower {
//      def name = "Rose"
//    }
//  }
//}