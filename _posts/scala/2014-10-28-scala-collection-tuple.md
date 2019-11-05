---
layout: post
title: scala-collection-tuple
category: scala
tags: scala
description: scala集合-tuple
---

# tuple 的迭代

tuple不同于普通的集合可以通过foreach形式迭代，或许tuple更像是一个轻量级的对象或者结构(struct)，你必须清楚它内部有多少元素，如果不采用模式匹配的话，就是用t.1, t.2 这样类似数组索引的方式来访问里面的元素

不同长度的tuple比如Tuple2和Tuple3并没有继承一个公共的抽象父类如Tuple。不过tuple继承了Product特质，在Product里提供了一个productIterator的方法来获取迭代器，所以在需要遍历未知长度的tuple时，可以通过这个迭代器来进行：

```
scala> def foo(tuple:Product) = tuple.productIterator.foreach(print)
foo: (tuple: Product)Unit

scala> foo((1,2,3))
123
```
