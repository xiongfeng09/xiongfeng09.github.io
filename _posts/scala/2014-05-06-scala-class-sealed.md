---
layout: post
title: scala的密封类别sealed
category: scala
tags: scala
description: scala的密封类别sealed
---

## 密封类别sealed

```
case class Point(x: Int, y: Int)
case class Circle(p: Point, r: Int)
case class Cylinder(c: Circle, h: Int)
```

如果想进行模式比对，对传入的物件看看是不是点，圆或柱的一种：

```
def what(a: Any) = a match {
    case Point(_, _)    => "点"
    case Circle(_, _)   => "圆"
    case Cylinder(_, _) => "柱"
}
```

如果案例比较多，或者一时少写了其中的几个：

```
def what(a: Any) = a match {
    case Point(_, _)    => "点"
    case Cylinder(_, _) => "柱"
}
```

程序可以正常编译，但是若传入了Circle,则会发生 scala.MatchError

对于在进行模式匹配是，一定要出现的案例类型，可以将之密封（Sealed) , 例如：

```
sealed abstract class Drawing
case class Point(x: Int, y: Int) extends Drawing
case class Circle(p: Point, r: Int) extends Drawing
case class Cylinder(c: Circle, h: Int) extends Drawing
```

你使用sealed关键字修饰类别，则继承该类别的子类别，`必须与该父类位于同一个原始代码中`，你也可以使用sealed修饰Trait, 例如

```
sealed trait Drawing
case class Point(x: Int, y: Int) extends Drawing
case class Circle(p: Point, r: Int) extends Drawing
case class Cylinder(c: Circle, h: Int) extends Drawing

如果你之前，少写了其中一个案例：
def what(d: Drawing) = d match {
    case Point(_, _)    => "点"
    case Cylinder(_, _) => "柱"
}
```

则编译器会提出警示讯息：

```
warning: match is not exhaustive!
missing combination         Circle

def what(d: Drawing) = d match {
                        ^
```

编译器在告诉你，有些模式类型你没有列在match运算式的案例串中，你必须每个都列出来才可以：

```
def what(d: Drawing) = d match {
    case Point(_, _)    => "点"
    case Circle(_, _)   => "圆"
    case Cylinder(_, _) => "柱"
}
```

有时候， 你使用别人密封过的案例类别， 但也许你真的只想对其中几种案例类型，如果不想要编译器的警示讯息， 怎可以在最后使用 "_",例如：

```
def what(d: Drawing) = d match {
    case Point(_, _)    => "点"
    case Cylinder(_, _) => "柱"
    case _              => ""
}
```

如果真的不想要使用"_"，也可以使用 @unchecked 标注（Annotation）告诉编译器住嘴：

```
def what(d: Drawing) = (d: @unchecked) match {
    case Point(_, _)    => "点"
    case Cylinder(_, _) => "柱"
}
```
