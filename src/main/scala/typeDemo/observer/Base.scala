package typeDemo.observer
import scala.collection.mutable.ListBuffer
/**
 * Created by xiongfeng on 15/6/30.
 */
trait Subject {
  val observers = ListBuffer[Observer]()

  def addObserver(o: Observer): ListBuffer[Observer] = observers += o
  def deleteObserver(o: Observer): ListBuffer[Observer] = observers -= o

  def notifyObservers = observers.foreach(observer => observer.update(this))
}

trait Observer {
  def update(s: Subject): Unit
}

class concreateSubject extends Subject

class concreateObserver extends Observer {
  def update(s: Subject): Unit = {
    println(s)
  }
}
