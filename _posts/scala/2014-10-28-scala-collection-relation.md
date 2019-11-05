---
layout: post
title: scala-collection-relation
category: scala
tags: scala
description: scala集合
---
# Traversable
TraversableLike[A, Traversable[A]]
GenTraversable[A]
TraversableOnce[A]
GenericTraversableTemplate[A, Traversable]

# GenTraversableOnce
A template trait for all traversable-once objects which may be traversed in parallel.
Methods in this trait are either abstract or can be implemented in terms of other methods.

所有 可比案例一次的对象
```
abstract def isTraversableAgain: Boolean
```
Tests whether this collection or iterator can be repeatedly traversed.

```
abstract def toIterator: Iterator[A]
```
Returns an Iterator over the elements in this collection or iterator.

```
abstract def toStream: Stream[A]
```
Converts this collection or iterator to a stream.

# TraversableOnce
GenTraversableOnce

# Builders

# TraversableLike
## Factoring out common operations
HasNewBuilder[A, Repr]
FilterMonadic[A, Repr]
TraversableOnce[A]
GenTraversableLike[A, Repr]
Parallelizable[A, ParIterable[A]]

This is a base trait of all kinds of $mutability Scala collections. It
implements the behavior common to all collections, in terms of a method
`foreach` with signature:

```
 def foreach[U](f: Elem => U): Unit
```

Collection classes mixing in this trait provide a concrete `foreach` method which traverses all the elements contained in the collection, applying a given function to each.
They also need to provide a method `newBuilder`
which creates a builder for collections of the same kind.

# GenTraversable
GenTraversableLike[A, GenTraversable[A]]
GenTraversableOnce[A]
GenericTraversableTemplate[A, GenTraversable]





