---
layout: post
title: scala基础-包和引用
category: scala
tags: scala
description: scala基础-包和引用
---

# 包

与对象和类的定义不同。同一个包可以定义在多个文件中。源文件的目录和包之间没有强制的关联关系。

包可以包含类，特质 和对象，但不能包含函数或变量，这是java虚拟机的局限。

某些情况下，例如把你希望把工具函数或常量添加到包 而不是某个对象，这是需要用到包对象。

# 包对象

每个包都可以有一个包对象。你需要在父包中定义它，并且名字和子包一样。

```
package info.xiongfeng

package object api {
  val defaultName="xiongfeng"
}

package api {
   class Person {
    val name = defaultName
   }
}
```

注意defaultName 不需要加限定词。因为在同一包内。
在其它地方，这个常量可以通过 info.xiongfeng.api.defaultName 调用

包对象被编译为带有静态方法和字段的JVM类

package 类

```
package info.xiongfeng.api;

import scala.reflect.ScalaSignature;

public final class package
{
  public static String defaultName()
  {
    return package..MODULE$.defaultName();
  }
}

```

package 虚构类

```
package info.xiongfeng.api;

public final class package$
{
  public static final  MODULE$;
  private final String defaultName;

  static
  {
    new ();
  }

  public String defaultName()
  {
    return this.defaultName;
  }
  private package$() {
    MODULE$ = this;

    this.defaultName = "xiongfeng";
  }
}
```

java.lang, scala, Predef 总是被引入


![image](http://imagizer.imageshack.com/img911/8498/cP4vEn.png)



