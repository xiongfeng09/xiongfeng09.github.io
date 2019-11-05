package jsuereth-scala-in-depth-source-42fef3b.chapter7.manifests

class Test {
  def first[A : ClassManifest](x : Array[A]) = Array(x(0))
}
