---
layout: post
title: jvm-class-file-structure
category: jvm
tags: jvm
description: 类文件结构
---

# class 类文件结构

## TestClass 文件内容
```
public class TestClass {
  private int m;
  public int inc(){
    return m + 1;
  }
}
```

## TestClass class文件
```
cafe babe 0000 0033 0013 0a00 0400 0f09
0003 0010 0700 1107 0012 0100 016d 0100
0149 0100 063c 696e 6974 3e01 0003 2829
5601 0004 436f 6465 0100 0f4c 696e 654e
756d 6265 7254 6162 6c65 0100 0369 6e63
0100 0328 2949 0100 0a53 6f75 7263 6546
696c 6501 000e 5465 7374 436c 6173 732e
6a61 7661 0c00 0700 080c 0005 0006 0100
0954 6573 7443 6c61 7373 0100 106a 6176
612f 6c61 6e67 2f4f 626a 6563 7400 2100
0300 0400 0000 0100 0200 0500 0600 0000
0200 0100 0700 0800 0100 0900 0000 1d00
0100 0100 0000 052a b700 01b1 0000 0001
000a 0000 0006 0001 0000 0001 0001 000b
000c 0001 0009 0000 001f 0002 0001 0000
0007 2ab4 0002 0460 ac00 0000 0100 0a00
0000 0600 0100 0000 0400 0100 0d00 0000
0200 0e
```

Class 文件是一组以8位字节为基础单位的二进制流

# 魔数（Magic Number）与class文件的版本
每个class 文件的头四个字节成为Magic Number,它的唯一作用是确定这个文件是否为一个能被虚拟机接受的class文件
值为： 0xCAFEBABE 代表可以被虚拟机接受的class文件

# class文件的版本号
紧接着Magic Number的为class文件的版本号
5, 6代表次版本号（Minor Version）
7, 8带表主版本号
高版本的能向下兼容低版本的但不能运行更高版本的class文件

# 常量池
紧接着主次版本号之后的为常量池入口，常量池可以理解为class文件之中的资源仓库
由于常量池数量不固定，所以需要放一个U2类型的常量池容量计数值（constant_poop_count）
常量计数从1开始而不是0
所以十六进制0013代表18个常量，索引值从1-18
class 文件中只有常量计数器从1开始

## 常量池存放两大常量： 字面量（Literal）和符号引用(Symbolic Reference)
class 文件不会保存各个方法，字段的最终内存分布信息，因此这些字段方法符号不经过运行期转换的话无法得到真正的内存入口地址，也就无法被虚拟机使用。当虚拟机运行时，需要从常量池获得对应符号引用，
再在类创建时或运行时解析，翻译到具体的内存地址中

-verbose 参数输出文件字节码内容

```
javap -verbose TestClass
```

# 访问标志
常量池结束之后，两个字节代表访问标志（access_flags）这个标志用来识别类或者接口的访问信息。


# 类索引，父类索引与接口索引
类索引（this_class）和 父类索引 都是一个u2类型的数据，而接口索引十一组u2类型的数据集合，class文件由这三项数据来确定这个类的继承关系

# 字段表集合
字段表用来描述接口或者类中声明的变量。字段包括类级变量以及实例级变量，但不包括在方法内部声明的局部变量

# 方法表集合

# 属性表集合
## code属性
## Exception属性
## LineNumberTable属性
## LocalVariableTable属性
## SourceFile属性
## ConstantValue属性
## InnerClass属性
## Deprecated及Synthetic属性
## StackMapTable属性
## signature属性
## BootstrapMethod属性
