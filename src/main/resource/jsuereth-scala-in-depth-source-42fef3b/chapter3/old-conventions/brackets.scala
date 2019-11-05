package jsuereth-scala-in-depth-source-42fef3b.chapter3.old-conventions

trait FooHolder
{
  def foo() 

  {
    println("foo was called")
  }
}


trait FooHolder2
{
  def foo() : Unit =
  
  {
    println("foo2 was called")
  }
}

trait IfIssues {
  if(true)
  {
    println("true!")
  }
  else
  {
    println("false!")
  }
}

