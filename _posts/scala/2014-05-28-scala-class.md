---
layout: post
title: scala类
category: scala
tags: scala
description: scala类
---

# 带getter 和 setter 的属性

## 字段
  val或者var定义的, 如果不带val或者var的参数至少被一个方法所使用，它被升格为字段

scala 对每个字段都提供getter和setter方法

```
class Person {
 var age = 0
}
```

scala 生成面向jvm的类。其中生成一个私有的字段以及相应的getter 和 setter 方法分别为 age 和 age_=

```
public class Person {
  public int age();
  public void age_$eq(int);
  public Person();
}
```

scala 对 每个字段都生成getter和setter方法听上去有些恐怖，不过你可以控制这个过程
 如果字段是私有的， 则 getter和setter方法也是私有的。
 如果字段是val， 则只有getter方法被生成。
 如果你不需要任何getter和setter方法，可以将字段声明为private[this]

## 重新定义getter 和 setter 方法

```
class Person {
  private var _age = 0

  def age = _age
  def age_=(age: Int) = {
    if (age < 10) _age = 25
    else _age = age
  }
}
```
