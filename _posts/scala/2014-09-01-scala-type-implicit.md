---
layout: post
title: scala类型系统-隐式转换和隐式参数
category: scala
tags: scala
description: scala类型系统-隐式转换和隐式参数
---

# 隐式转换函数（implicit conversion function）
指的是以implicit关键字生命的带有单个参数的函数，这样函数会被自动应用将值从一个类型转换到另一个类型

```
  class A(name: String) {
    def sayHello = println("hi," + name)
  }
  
  implicit def string2A(name: String) = new A(name)
  
  "xiongfeng".sayHello
```

# 引入隐式转换
scala会考虑如下的隐式转换函数：
1.位于源或目标类型的半生对象中的隐式函数
2.位于当前作用域可以以单个标识符指代的隐式函数

如果某个特定的隐式转换给你带来麻烦，也可以排除
如下表示引入除了A2B的隐式转换
```
    import info.xiongfeng.{ A2B => _, _}  
```

在REPL下
```
  :implicits 查看所有除Predef外被引入的隐式成员
  :implicits -v 查看全部
```

# 隐式转换规则

## 如下三种情况会被考虑
1.当表达是的类型与预期类型不同时
2.当访问一个对象不存在时
3.当独享调用某个方法，改方法的参数声明与传入参数不匹配时

## 如下三种情况不会尝试使用隐式转换
1.如果代码在不使用隐式转换的前提下通过编译
2.编译器不会同时执行多个转换。例如convert1(convert2(a))*b
3.(被尝试转换的对象)存在二义性的转换是个错误。例如convert1(a)*b 和 convert2(a)*b都是合法的时候

## 如果打算了解编译器使用了哪些隐式转换。可以用如下命令编译程序
```
  scalac -Xprint:typer Test.scala
```

# 隐式参数
函数与方法可以带有一个标记implicit的参数列表，这种情况下，编译器会查找缺省值提供给改方法或者函数。

```
  case class Delimiters(left: String, right: String)
  
  def quote(what: String)(implicit delims: Delimiters) =
    delims.left + what + delims.right
```

显示调用
```
  quote("Bonjour le monde")(Delimiters("<", ">"))
```

隐式调用
```
  quote("Bonjour le monde")
```

编译器在如下两个地方查找这样的一个对象
1.当前作用域所有可以用单个标识符只带的满足类型要求的val和def
2.与所要求类型相关联的类型的伴生对象。相关联的类型包括所要求类型的本身，以及它的类型参数（如果它是一个参数化类型的话）。

# 利用隐式参数进行隐式转换
```
  def smaller[T](a: T, b: T) = if (a > b) a else b
```
这实际上行不通，因为编译器并不知道你传入的对象是否有'>' 方法

常用的做法
```
  def smaller[T](a: T, b: T)(implicit order: T => Ordered[T]) = if (order(a) < b) a else b
```

order 是一个带有单个参数的函数，
因此它不仅仅是一个隐式参数，它还是一个隐式函数
所以

```
def smaller[T](a: T, b: T)(implicit order: T => Ordered[T]) = if (order(a) < b) a else b
```

# 上下文界定界
类型参数有一个形式为T:M的上下文定界，其中M为另一个泛型类型。要求作用于中存在一个类型为M[T]的隐式值

```
class Pair[T: Ordering]
```

要求存在类型为Ordering[T] 的隐式值

```
class Pair[T: Ordering](val first: T, val second: T) {
  def smaller(implicit order: Ordering[T]) = {
    if (ord.compare(first, second) < 0) first else second
  }
}
```
或者也可以用Predef类中定义的implicitly方法

```
class Pair[T: Ordering](val first: T, val second: T) {
  def smaller =  {
    if (implicitly[Ordering[T]].compare(first, second) < 0) first else second
  }
}
```

implicitly函数在Predef.scala中定义如下
```
def implicitly[T](implicit e: T) = T
```

# 类型证明
类型约束
T =:= U
T <:< U
T <%< U
这些约束讲校验T是否等于U, 是否是U的子类型, 是否可以隐式转换为U

to be continue ...




