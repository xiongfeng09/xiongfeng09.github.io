//package macros
//
///**
//  * Created by xiongfeng on 16/6/13.
//  */
//
//import scala.reflect.macros.runtime.Context
//
//object Asserts {
//  val universe: scala.reflect.runtime.universe.type = scala.reflect.runtime.universe
//  import universe._
//  def log(msg: String): Unit = macro impl
//
//  def impl(c: Context)(msg: c.Expr[String]): c.Expr[Unit] = {
//    q"""
//      if (Logger.enabled)
//      Logger.log($msg)
//    """
//  }
//}