---
layout: post
title: scala类型系统-单例类型
category: scala
tags: scala
description: scala中的高级类型
---

## 单例类型
给定任意任意引用v, v.type可以得到它的类型 有两个可能的值。`v`和`null`

```
class Document {
    def setTitle(title: String) = {
        println(title)
        this
    }
    def setAuthor(author: String) = {
        println(author)
        this
    }
}
```

```
class Book extends Document {
     def addChapter(chapter: String) = {
         println(chapter)
         this
     }
 }
```

```
scala> b.setTitle("a").setChapter("b")
<console>:11: error: value setChapter is not a member of Document
b.setTitle("a").setChapter("b")
```
setTitle返回的是this, scala将类型推断为Document.但Document没有addChapter方法。


```
class Document {
    def setTitle(title: String): this.type = {
        println(title)
        this
    }
    def setAuthor(author: String): this.type = {
        println(author)
        this
    }
}
```

```
object Title
def set(obj: Title.type) = {}
```

Title为单例对象,而方法需要的是类型。所以这里要使用 `Title.type`

以上部分来自快学scala
