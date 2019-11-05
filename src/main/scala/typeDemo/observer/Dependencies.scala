package typeDemo.observer

/**
 * Created by xiongfeng on 15/7/1.
 */
//trait Dependencies {
//  type Ref = x.Handle forSome {val x: Observable}
//  var handles = List[Ref]()
//
//  protected def addHandle(handle: Ref): Unit = {
//    handles :+= handle
//  }
//
//  protected def removeDependencies() {
//    for (h <- handles) h.remove()
//    handles = List()
//  }
//
//  protected def observe[T <: Observable](obj: T)(handler: T => Unit): Ref = {
//    val ref = obj.observe(handler)
//    addHandle(ref)
//    ref
//  }
//}