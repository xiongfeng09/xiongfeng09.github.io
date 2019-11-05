package typeDemo.observer

trait Observable {
  type Handle
  type Callback = this.type => Unit

  val callbacks = scala.collection.mutable.Map[Handle, Callback]()
  def observe(callback: Callback): Handle = {
    val handle = createHandle(callback)
    callbacks += (handle -> callback)
    handle
  }

  def unobserve(handle: Handle): Unit = {
    callbacks -= handle
  }

  protected def createHandle(callback: Callback): Handle

  protected def notifyListeners(): Unit = {
    println(callbacks)
    for (callback <- callbacks.values) callback(this)
  }
}

trait DefaultHandles extends Observable {
  type Handle = Callback

  protected def createHandle(callback: Callback): Handle = callback

  override def toString: String = "Defalut Handles"
}

class IntStore(private var value: Int) extends DefaultHandles {
  def set(v: Int): Unit = {
    notifyListeners
    this.value = v
  }

  def get: Int = this.value
}

object observableTest extends App {
  val a = new IntStore(1)

  def change(a: Any) = println("i have change")

  a.observe(println)
  a.observe(change)
  a.set(2)
  a.get

  val b = new IntStore(2)
  b.observe(println)
  b.observe(change)
  a.set(2)
  a.get
}
