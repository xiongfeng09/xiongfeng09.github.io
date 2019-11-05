---
layout: post
title: scala特质
category: scala
tags: scala
description: scala特质
---

## 特点
1.特质的定义除了使用关键字trait外，与类定义无异议
2.和类一样有默认的超类AnyRef
3.extends 混入特质，默认继承特质的超类
4.在类中super调用都是静态绑定的，而特质是动态绑定的。
5.特质不能有任何的类参数

## trait 与 java 的对照

`如果一个Trait没有定义任何有具体实现的方法，那么它和接口是等价的。换句话说，如果一个Trait的所有方法（如果有的话）全都是抽象的，那么Scala会把它编译成Java接口。比如下面这个没有任何方法的TraitA`

```
trait traitA {
}
```

会被编译为

```
public abstract interface TraitA
{
}
```

```
trait TraitB  {
  val a: Int
  def b: Int
}
```

会编译为

```
public interface TraitB {
  public abstract int a();
  public abstract int b();
}
```

### 有具体方法的Trait会被编译成两个class文件
例如：

```
trait traitInScala {
  def name = "xiongfeng"
  var age = "25"
}
```

会被编译为两个文件 traitInScala 和 traitInScala$class
traitInScala.class

```
public abstract interface traitInScala
{
  public abstract String name();

  public abstract String age();

  @TraitSetter
  public abstract void age_$eq(String paramString);
}
```
traitInScala$class.class

```
public abstract class traitInScala$class
{
  public static String name(traitInScala $this)
  {
    return "xiongfeng";
  }

  public static void $init$(traitInScala $this) {
    $this.age_$eq("25");
  }
}
```

### 继承trait
ExtendsTrait

```
class ExtendsTrait extends traitInScala {
  val a = 1
  var b = 2
}
```

```
import scala.reflect.ScalaSignature;

public class ExtendsTrait
  implements traitInScala
{
  private final int a;
  private int b;
  private String age;

  public String age()
  {
    return this.age;
  }
  public void age_$eq(String x$1) { this.age = x$1; }
  public String name() { return traitInScala.class.name(this); }
  public int a() { return this.a; }
  public int b() { return this.b; }
  public void b_$eq(int x$1) { this.b = x$1; }


  public ExtendsTrait()
  {
    traitInScala.class.$init$(this);
    this.a = 1;
    this.b = 2;
  }
}
```

由此可知：
`Trait会被编译为等价的接口
如果Trait有具体方法，则这些方法会被复制到相应的$class类里，并且有下面两处变动：
方法变为static
Trait实例被插入到参数列表的最开始`


### 如果继承的不同特质中都有相同的方法

```
trait A {
  def show = println("a")
}
trait B {
  def show = println("b")
}
trait C {
  def show = println("c")
}

class MoreExtendsTrait extends C with B with A {
  override def show = println("...")
  def run = show
}
```

```
MoreExtendsTrait.scala:11: error: class MoreExtendsTrait inherits conflicting members:
  method show in trait B of type => Unit  and
  method show in trait A of type => Unit
(Note: this can be resolved by declaring an override in class MoreExtendsTrait.)
c
```

当类实现的特质中有相同的方法是，编译器会 告诉你类继承了冲突的成员，这时类中需要override 冲突的成员或者方法

### 特质的叠加
你可以为类或者对象添加多个互相调用的特质，从最后一个开始。对于需要分阶段处理某个值的场景非常实用

```
  trait Logged {
    def log(msg: String){}
  }

  trait TimestampLogger extends Logged {
    override def log(msg: String) {
      super.log(new java.util.Date() + " " + msg)
    }
  }

  trait ShortLogger extends Logged {
    val maxLength = 15
    override def log(msg: String) {
      super.log(
        if (msg.length <= 15) msg
        else msg.take(15)
      )
    }
  }

  val acct1 = new SavingsAccount with ShortLogger with TimestampLogger
  val acct2 = new SavingsAccount with TimestampLogger with ShortLogger
```

```
public class SavingsAccount
  implements TimestampLogger, ShortLogger
{
  private final int maxLength;

  public int maxLength()
  {
    return this.maxLength; }
  public void ShortLogger$_setter_$maxLength_$eq(int x$1) { this.maxLength = x$1; }
  public void log(String msg) { ShortLogger.class.log(this, msg); }
  public SavingsAccount() { Logged.class.$init$(this); TimestampLogger.class.$init$(this); ShortLogger.class.$init$(this);
  }
}

public abstract interface TimestampLogger extends Logged
{
  public abstract void log(String paramString);
}

public abstract class TimestampLogger$class
{
  public static void log(TimestampLogger $this, String msg)
  {
    $this.TimestampLogger$$super$log(new StringBuilder().append(Predef.any2stringadd..MODULE$.$plus$extension(Predef..MODULE$.any2stringadd(new Date()), " ")).append(msg).toString());
  }

  public static void $init$(TimestampLogger $this)
  {
  }
}

public abstract interface ShortLogger extends Logged
{
  public abstract void ShortLogger$_setter_$maxLength_$eq(int paramInt);

  public abstract int maxLength();

  public abstract void log(String paramString);
}


public abstract class ShortLogger$class
{
  public static void log(ShortLogger $this, String msg)
  {
    $this.ShortLogger$$super$log(msg.length() <= 15 ? msg : (String)new StringOps(Predef..MODULE$.augmentString(msg)).take(15));
  }

  public static void $init$(ShortLogger $this)
  {
    $this.ShortLogger$_setter_$maxLength_$eq(15);
  }
}

public abstract interface Logged
{
  public abstract void log(String paramString);
}

public abstract class Logged$class
{
  public static void log(Logged $this, String msg)
  {
  }

  public static void $init$(Logged $this)
  {
  }
}
```


acct1 的执行是 TimestampLogger 先执行, 然后 ShortLogger 再执行
acct2 与之相对

### 在特质中重写抽象的方法

```
  trait Logged {
    def log(msg: String)
  }

  trait ShortLogger extends Logged {
    override def log(msg: String) {
      super.log(
        if (msg.length <= 15) msg else msg.take(15)
      )
    }
  }

```
根据正常的继承规则，这个条用永远都是错的。但实际上如上面讲的，我们没法知道哪个log方法呗最终调用，这取决于特质被混入的顺序。
Scala认为ShortLogger 仍然是抽象的。所以你要加上abstract关键字。

### 特质中的具体字段
在JVM中一个类只能扩展子一个超类，因此来自特质的字段不能以相同的方式继承，所以被混入的字段都自动成为该类自己的字段
### 特质中的抽象字段
特质中没有被初始化的字段在具体的子类中必须被重写
### 特质的构造顺序
* 首先调用超类的构造器
* 特质构造器在超类构造器之后，类构造器之前执行
* 特质由左到右被构造
* 每个特质中，父特质被先构造
* 如果多个特质共有一个父特质，且那个父特质已经被构造，则不会再次构造
* 所有特质被构造完后，子类被构造


### 扩展类的特质
如果一个特质扩展自一个类A，那么当这个特质被混入类B的时候。A也就成了B的超类