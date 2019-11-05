---
layout: post
title: scala-value-class
category: scala
tags: scala
description: scala-value-class
---

### Value class
是在SIP-15中提出的一种通过继承AnyVal类来避免运行时对象分配的新机制。以下是一个最简的value class。

```
class Wrapper(val underlying: Int) extends AnyVal
```

它仅有一个被用作运行时底层表示的公有val参数。在编译期，其类型为Wrapper，但在运行时，它被表示为一个Int。Value class可以带有def定义，但不能再定义额外的val、var，以及内嵌的trait、class或object：

`文章大部分内容来自[scala官网](http://docs.scala-lang.org/overviews/core/value-classes.html)`
我们来看一下Wrapper反编译后的java代码吧
会生成两个class 
#### Wrapper

```
public final class Wrapper
{
  private final int underlying;

  public static boolean equals$extension(int paramInt, Object paramObject)
  {
    return Wrapper..MODULE$.equals$extension(paramInt, paramObject);
  }

  public static int hashCode$extension(int paramInt)
  {
    return Wrapper..MODULE$.hashCode$extension(paramInt);
  }

  public int underlying()
  {
    return this.underlying;
  } 
  
  public int hashCode() {
    return Wrapper..MODULE$.hashCode$extension(underlying()); 
  } 
  public boolean equals(Object x$1) {
    return Wrapper..MODULE$.equals$extension(underlying(), x$1);
  }
  public Wrapper(int underlying)
  {
  }
}
```
#### Wrapper$

```
public final class Wrapper$
{
  public static final  MODULE$;

  static
  {
    new ();
  }

  public final int hashCode$extension(int $this)
  {
    return BoxesRunTime.boxToInteger($this).hashCode(); 
  } 
  public final boolean equals$extension(int $this, Object x$1) {
    Object localObject = x$1;
    int i;
    if ((localObject instanceof Wrapper)) i = 1; else i = 0;
    int j;
    if (i != 0) j = ((Wrapper)x$1).underlying(); return ($this == j ? 1 : 0) != 0; 
  } 
  private Wrapper$() { MODULE$ = this; }

}
```



Value class只能继承universal traits，但其自身不能再被继承。

### universal trait
所谓universal trait就是继承自Any的、只有def成员，且不作任何初始化工作的trait。继承自某个universal trait的value class同时继承了该trait的方法，但是（调用这些方法）会带来一定的对象分配开销。例如：

```
trait Printable extends Any {
  def print(): Unit = println(this)
}
class Wrapper(val underlying: Int) extends AnyVal with Printable
val w = new Wrapper(3)
w.print() // 这里实际上会生成一个Wrapper类的实例
```

本文后续篇幅将介绍相关用例和与对象分配时机相关的细节，并给出一些有关value class自身限制的具体实例。

### 扩展方法

关于value类的一个用例，是将它们和隐含类联合（SIP-13）以获得免分配扩展方法。使用隐含类可以提供便捷的语法来定义扩展方法，同时 value 类移除运行时开销。一个好的例子是在标准库里的RichInt类。RichInt 继承自Int类型并附带一些方法。由于它是一个 value类，使用RichInt 方法时不需要创建一个RichInt 的实例。

下面有关RichInt的代码片段示范了RichInt是如何继承Int来允许3.toHexString的表达式：

```
class RichInt(val self: Int) extends AnyVal {
  def toHexString: String = java.lang.Integer.toHexString(self)
}
```

在运行时，表达式3.toHexString 被优化并等价于静态对象的方法调用 （RichInt$.MODULE$.extension$toHexString(3)），而不是创建一个新实例对象，再调用其方法。

### 正确性

关于value类的另一个用例是：不增加运行时开销的同时，获得数据类型的类型安全。例如，一个数据类型片断代表一个距离 ，如：

```
class Meter(val value: Double) extends AnyVal {
  def +(m: Meter): Meter = new Meter(value + m.value)
}
```

代码：对两个距离进行相加，例如：

```
val x = new Meter(3.4)
val y = new Meter(4.3)
val z = x + y
```

实际上不会分配任何Meter实例，而是在运行时仅使用原始双精浮点数（double） 。

注意：在实践中，可以使用条件类（case）and/or 扩展方法来让语句更清晰。






