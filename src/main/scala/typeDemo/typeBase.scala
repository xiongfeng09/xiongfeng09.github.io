package typeDemo

/**
 * Created by xiongfeng on 15/6/26.
 */
class Outer {
  trait Inner
  def y = new Inner {}
  def foo(x: this.Inner) = null
  def bar(x: Outer#Inner) = null
}

object test11 extends App {
  val x = new Outer
  val y = new Outer

  x.foo(x.y)
  //x.foo(y.y)
  x.bar(x.y)
  x.bar(y.y)

  val i = new in {}
  i.close
  val d = System.in
  Resources.closeResource(i)

  type T = {
    type X = Int
    def x: X

    type Y
    def y: Y
  }

  object Foo {
    type X = Int

    def x: X = 5

    type Y = Int

    def y: Y = 5
  }

  def test(t: T) = t.x
}




object Resources {
  type Resource = {
    def close(): Unit
  }

  def closeResource(r: Resource) = r.close()
}


trait in {
  def close(): Unit = {
    println("close")
  }
}
