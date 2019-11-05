---
layout: post
title: scala类型系统-forsome
category: scala
tags: scala
description: scala类型系统-存在类型
---

# 存在类型是一种构造部分类型签名是已存在的类型的手段

```
  val x: List[_] = List()
```

## 正式语法
```
  val y: List[X forSome { type X}] = List()
  
  val z: List[X forSome { type x <: AnyRef }] = y
```

## 注意事项
_只能在类型声明里面用到




