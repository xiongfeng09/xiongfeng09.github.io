---
layout: post
title: scala对象-单例对象
category: scala
tags: scala
description: scala对象-单例对象
---

## class 与 java 的对照

## object 与 java的对照
 
  > javap是JDK提供的一个反编译工具。常用的选项有-c -l -s。如果仅仅是想查看编译的得到的字节码文件中函数与变量，不需要带选项。使用javap很容易发现源文件与编译后得到的字节码文件有什么不同，可以加深对编译器的理解。


 > JD-GUI

用法: javap <options> <classes>
其中, 可能的选项包括:

```
  -help  --help  -?        输出此用法消息
  -version                 版本信息
  -v  -verbose             输出附加信息
  -l                       输出行号和本地变量表
  -public                  仅显示公共类和成员
  -protected               显示受保护的/公共类和成员
  -package                 显示程序包/受保护的/公共类
                           和成员 (默认)
  -p  -private             显示所有类和成员
  -c                       对代码进行反汇编
  -s                       输出内部类型签名
  -sysinfo                 显示正在处理的类的
                           系统信息 (路径, 大小, 日期, MD5 散列)
  -constants               显示静态最终常量
  -classpath <path>        指定查找用户类文件的位置
  -bootclasspath <path>    覆盖引导类文件的位置
```

### 新建一个ObjectInScala.scala
```
  object ObjectInScala {
    def main(args: Array[String]) = {
      println("Hello, " + args(0))
    }
  }
```

会编译两个文件 ObjectInScala.class 和 ObjectInScala$.calss
也就是说， 这个孤立对象也被编译成一个同名类ObjectInScala。 除此之外， 还有一个叫做ObjectInScala$的类，这个以$结尾的类就是所谓的虚构类（synthetic class， 《Scala编程》中将之翻译为虚构类） 。


### 使用javap 反编译为java文件
ObjectInScala.class

```
public final class ObjectInScala
{
  public static void main(String[] paramArrayOfString)
  {
    ObjectInScala..MODULE$.main(paramArrayOfString);
  }
}
```

ObjectInScala$.class

```
import scala.Predef.;
import scala.collection.mutable.StringBuilder;

public final class ObjectInScala$
{
  public static final  MODULE$;

  static
  {
    new ();
  }

  public void main(String[] args)
  {
    Predef..MODULE$.println(new StringBuilder().append("Hello, ").append(args[0]).toString());
  }

  private ObjectInScala$()
  {
    MODULE$ = this;
  }
}
```
