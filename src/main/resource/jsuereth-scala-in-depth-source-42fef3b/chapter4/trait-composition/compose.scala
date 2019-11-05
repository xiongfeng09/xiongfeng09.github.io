package jsuereth-scala-in-depth-source-42fef3b.chapter4.trait Logger {
  def log(category : String, msg : String) : Unit = {
       println(msg)
  }
}composition


trait DataAccess {
   val logger = new Logger

   def query[A](in : String) : A = {
     logger.log("QUERY", in)
     5
   }
}



trait DataAccess {
   val logger = new Logger

   def query[A](in : String) : A = {
     logger.log("QUERY", in)     
     5
   }
}
