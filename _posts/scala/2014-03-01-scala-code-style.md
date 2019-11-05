---
layout: post
title: scala代码风格
category: scala
tags: scala
description: scala style
---

## 格式化
代码格式化的规范并不重要，只要它们实用。它的定义形式没有先天的好与坏，几乎每个人都有自己的偏好。然而，对于一贯地采用同一格式化规则的总会增加可读性。已经熟悉某种特定风格的读者不必非要去掌握另一套当地习惯，或译解另一个角落里的语言语法。

这对Scala来说也特别重要，因为它的语法高度的重叠。一个例子是方法调用：方法调用可以用“.”后边跟圆括号，或不使用“.”，后边用空格加不带圆括号(针对空元或一元方法)方式调用。此外，不同风格的方法调用揭露了它们在语法上不同的分歧(ambiguities)。当然一致的应用慎重的选择一组格式化规则，对人和机器来说都会消除大量的歧义。

我们依着Scala style guide 增加了以下规则：


## 空格
用两个空格缩进。避免每行长度超过100列。在两个方法、类、对象定义之间使用一个空白行。


## 命名
对作用域较短的变量使用短名字：
is, js 和 ks等可出现在循环中。
对作用域较长的变量使用长名字：
外部APIs应该用长的，不需加以说明便可理解的名字。例如：Future.collect 而非 Future.all
使用通用的缩写，避开隐秘难懂的缩写：
例如每个人都知道 ok,err, defn等缩写的意思，而sfri是不常用的。
不要在不同用途时重用同样的名字：
使用val(注：Scala中的不可变类型)
避免用 `声明保留字变量`：
用typ替代 `type`
用主动语态(active)来命名有副作用的操作：
user.activate()而非 user.setActive()
对有返回值的方法使用具有描述性的名字：
src.isDefined 而非src.defined
getters不采用前缀get：
用get是多余的: site.count而非site.getCount
不必重复已经被package或object封装过的名字：
使用：

```
object User {
  def get(id: Int): Option[User]
}
```

而非：

```
object User {
  def getUser(id: Int): Option[User]
}
```

相比 get 方法 getUser 方法中的User是多余的，并不能提供额外的信息。

## Imports
对引入行按字母顺序排序：
这样机方便了视觉上的检查，也简化了自动操作。
当从一个包中引入多个时，用花括号：

```
import com.twitter.concurrent.{Broker, Offer}
```

当引入超过6个时使用通配符：
e.g.: 

```
import com.twitter.concurrent._ 
```

不要轻率的使用: 一些包导入了太多的名字
当引入集合的时候，通过用import scala.collections.immutable(不可变集合)或scala.collections.mutable(可变集合)来限定名称
可变和不可变集合有相同的名字。限定名称让读者很明确知道使用的是哪个变量(e.g. "immutable.Map")
(译注，通常也会默认immutable，而在使用mutable时显示引入)
不要使用来自其它包的相对引用：
避免

```
import com.twitter
import concurrent
```

而应该用清晰的：

```
import com.twitter.concurrent
```

(译注，实际上上面的import不能编译通过，第二个import应该为：import twitter.concurrent 即import一个包实际是定义了这个包的别名。)
将import放在文件的头部：
读者可以在一个地方参考所有的引用。

## 花括号
花括号用于创建复合表达式，复合表达式的返回值是最后一个表达式。避免对简单的表达式采用花括号；写成：

```
 def square(x: Int) = x*x
```

而不是：

```
def square(x: Int) = {
	x * x
}
```

尽管第二种方式用在区分方法体的语句构成很诱人。第一种选择更加简洁，易读。避免语句上的繁文缛节，除非需要阐明。

## 模式匹配
尽可能直接在函数定义的地方使用模式匹配。例如，下面的写法 match应该被折叠起来(collapse)

```
 list map { item =>
   item match {
     case Some(x) => x
     case None => default
   }
 }
```

用下面的写法替代：

```
 list map {
   case Some(x) => x
   case None => default
 }
```
它很清晰的表达了 list中的元素都被映射，间接的方式让人不容易明白。


## 注释
使用ScalaDoc提供API文档。用下面的风格：

```
 /**
  * ServiceBuilder builds services
  * ...
  */
```
而不是非标准的ScalaDoc风格：

```
 /** ServiceBuilder builds services
  * ...
  */
```
不要诉诸于ASCII码艺术或其他可视化修饰。用文档记录APIs但不要添加不必要的注释。如果你发现你自己添加注释解释你的代码行为，先问问自己是否可以调整代码结构，从而可以明显地可以看出它做了什么。相对于“it works, obviously” 更偏向于“obviously it works”
