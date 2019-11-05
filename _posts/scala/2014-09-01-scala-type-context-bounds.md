---
layout: post
title: scala类型系统-context bounds
category: scala
tags: scala
description: scala类型系统-context bounds
---

context bounds 是在scala 2.8版本增加的。为了语法上的方便，引入了“上下文界定”这个概念

### 比较两个数的大小

```
  def max[T](a: T, b: T)(implicit cp: Ordering[T]) = {
    if (cp.compare(a, b) > 0) a else b
  }
```

可以简化为

```
  def max[T : Ordering] (a:T, b:T) = { … }
```
上面的类型参数声明 T : Comparator 就表示存在一个 Comparator[T]类型的隐式值。
里面有两种实现方式：

```
  def max[T : Ordering] (a:T, b:T) = {
   def inner(implicit c:Ordering[T]) = c.compare(a,b);
    if(inner>0) a else b
  }
```

```
  def max2[T : Ordering] (a:T, b:T) = {
    val cp = implicitly[Ordering[T]]
      if( cp.compare(a,b)>0) a else b
    }
```

implicitly是在Predef.scala里定义的，它是一个特殊的方法，编译器会记录当前上下文里的隐式值，而这个方法则可以获得某种类型的隐式值。

```
@inline def implicitly[T](implicit e: T) = e
```
