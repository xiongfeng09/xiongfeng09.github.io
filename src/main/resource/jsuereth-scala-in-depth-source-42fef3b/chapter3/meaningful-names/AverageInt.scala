package jsuereth-scala-in-depth-source-42fef3b.chapter3.meaningful-names

object `Average$int` {
   def avg(values : List[Int]) = {
     val sum = values.foldLeft(0.0) { _ + _.toDouble }
     sum / values.size.toDouble
   }

}
