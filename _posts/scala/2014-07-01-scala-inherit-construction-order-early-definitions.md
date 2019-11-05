---
layout: post
title: scala继承-提前定义
category: scala
tags: scala
description: scala继承
---

# 构造顺序提前定义

```
class Creature {
  val range: Int = 10
  val env: Array[Int] = new Array[Int](range)
}
```

```
class Ant extends Creature {
  override val range = 2
}
```

编译后的类

```
public class Ant extends Creature {
  private final int range;

  public int range();
    Code:
       0: aload_0       
       1: getfield      #10                 // Field range:I
       4: ireturn       

  public Ant();
    Code:
       0: aload_0       
       1: invokespecial #16                 // Method Creature."<init>":()V
       4: aload_0       
       5: iconst_2      
       6: putfield      #10                 // Field range:I
       9: return        
}


public class Creature
{
  private final int range = 10;
  private final int[] env = new int[range()];

  public int range() {
    return this.range; 
  } 
  public int[] env() { return this.env; }

}
```


1.Ant调用Creature的构造器
2.Creature的构造器初始化range字段设为10
3.Creature构造器为了初始化env数组，调用range()取值器
4.该方法被重写调用Ant的range字段
5.range字段（还未被初始化）所以返回默认的值
6.env被设为长度为0的数组
7.range构造器继续执行，将其字段设为2

```
class Ant2 extends {
  override val range = 2
} with Creature
```

编译后的类

```
public class Ant2 extends Creature {
  private final int range;

  public int range();
    Code:
       0: aload_0       
       1: getfield      #10                 // Field range:I
       4: ireturn       

  public Ant2();
    Code:
       0: iconst_2      
       1: istore_1      
       2: aload_0       
       3: iload_1       
       4: putfield      #10                 // Field range:I
       7: aload_0       
       8: invokespecial #16                 // Method Creature."<init>":()V
      11: return        
}

```

这是可以看到 Ant2初始化range的顺序在初始化父类Creature之前