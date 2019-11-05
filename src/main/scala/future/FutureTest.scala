package future

import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.util.{Try, Failure, Success}

object FutureTest {

  def main(args: Array[String]) {
    callbackTest


    succ("")
    succ("R2D3")
    succ("R2D9")
    succ("A99")
    succ("Z99")
    succ("Zz99")
    succ("9Z")
  }

  def callbackTest = {
    val f1 = Future {
      1 / 2
    }

    f1.onSuccess {
      case i: Int => println("success")
    }

    val f2 = Future {
      1 / 0
    }

    f2.onSuccess {
      case i: Int => println("success")
    }

    f2.onFailure {
      case i: Exception => println("fail 1")
    }

    f1 onComplete {
      case Success(s) => println("on complete success")
      case Failure(e) => e.printStackTrace()
    }

    val a = f1.transform((x:Int) => {println(x);x+1}, (e:Throwable) => {e.printStackTrace();e})

    a onComplete {
      case Success(s) => println("on complete a success")
      case Failure(e) =>
    }
  }

  def collectTest = {
    val f = future { -5 }
    val g = f collect {
      case x if x < 0 => -x
    }
  }

  def succ(src: String): String = {
    def add(src: String): String = {
      src.headOption.map { head =>
        if (head == '9') '0' + (if (src.drop(1).isEmpty) "1" else add(src.drop(1)))
        else if (head == 'Z') 'A' + (if (src.drop(1).isEmpty) "A" else add(src.drop(1)))
        else if (head == 'z') 'a' + (if (src.drop(1).isEmpty) "a" else add(src.drop(1)))
        else (head.toInt + 1).toChar + src.drop(1)
      }.getOrElse("")
    }
    add(src.reverse).reverse
  }

}
