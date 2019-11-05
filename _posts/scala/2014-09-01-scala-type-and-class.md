---
layout: post
title: scala类型系统-class和type
category: scala
tags: scala
description: scala类型系统-context bounds
---
# 类型
在 scala 里可以用一下两种方式定义类型
1.定义类 特质 和对象
2.直接用 type关键字定义类型

# type关键字
## 结构化类型

```
object Resources {
  type Resource = {
    def close(): Unit
  }

  def closeResource(r: Resource) = r.close()
}

trait A {
  def close() = println("close")
}

Resources.closeResource(new A {})

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
```


## 对象类型的引用

```
object ObjectName
def bar (x: ObjectName.type)
```

## 有两种机制引用scala 里定义的类型
hash(#) dot(.)

hash(#) 被成为类型注入。类型注入是一种引入嵌套的类型但是又不需要对象实例的方式

```
class Outer {
  trait Inner
  def y = new Inner {}
  def foo(x: this.Inner) = null
  def bar(x: Outer#Inner) = null
}

object test extends App {
  val x = new Outer
  val y = new Outer

  x.foo(x.y)
  //x.foo(y.y)
  x.bar(x.y)
  x.bar(y.y)
}
```

# 类型与类

import scala.reflect.runtime.universe._

typeOf

classOf

to be continue ...