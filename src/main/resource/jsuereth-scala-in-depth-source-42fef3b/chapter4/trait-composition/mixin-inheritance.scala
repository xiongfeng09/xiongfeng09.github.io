package jsuereth-scala-in-depth-source-42fef3b.chapter4.trait Logger {
  def log(category : String, msg : String) : Unit = {
       println(msg)
  }
}composition


trait DataAccess {
   def query[A](in : String) : A = {
     5
   }
}



trait LoggedDataAccess extends DataAccess with Logger {
   def query[A](in : String) : A = {
      log("QUERY", in)
      super.query(in)
   }
}


trait LoggedDataAccess extends DataAccess with Logger {
   def query[A](in : String) : A = {
      log("QUERY", in)
      super.query(in)
   }
}
