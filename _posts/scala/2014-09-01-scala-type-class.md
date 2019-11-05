---
layout: post
title: scala类型类
category: scala
tags: scala
description: scala类型类
---

## 使用高阶类型方式
```
trait Comparabel[T] {
  def compare(x: T): Int
}

case class IntCompare(value: Int) extends Comparabel[IntCompare] {
  def compare(x: IntCompare): Int = x.value - value
}

def call[T <: Comparabel[T]](x: T, y: T) = {
  x.compare(y)
}

call(IntCompare(1), IntCompare(2))
```
使用高阶类型的缺点：
1.必须定义出来包装类型
2.基本类型不能够比较

## 使用类型类方式
```
implicit def ordered[T](x: T) = new Comparabel[T] {
  def compare(y: T): Int = x.hashCode - y.hashCode
}

def call1[T](x: T, y: T)(implicit c: Comparabel[T]) = x.compare(y)

```
