package jsuereth-scala-in-depth-source-42fef3b.chapter1.jvm-examples

object FunctionUtil {
  def testFunction(f : Int => Int) : Int = f(5)
}

abstract class AbstractFunctionIntIntForJava extends (Int => Int) {
}

