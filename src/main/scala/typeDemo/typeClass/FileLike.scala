package typeDemo.typeClass

import java.io.InputStream

/**
 * Created by xiongfeng on 15/7/7.
 */
trait FileLike[T] {
  def name(file: T): String

  def isDirectory(file: T): Boolean

  def children(directory: T): Seq[T]

  def child(parent: T, name: String): T

  def mkdirs(file: T): Unit

  def content(file: T): InputStream

  def writeContent(file: T, otherContent: InputStream): Unit
}

object testFileLike extends App {
  def synchronize[F: FileLike, T: FileLike](from: F, to: T): Unit = {
    val fromHelper = implicitly[FileLike[F]]
    val toHelper = implicitly[FileLike[T]]

    def synchronizeFile(file1: F, file2: T): Unit = {
      toHelper.writeContent(file2, fromHelper.content(file1))
    }

    def synchronizeDirectory(dir1: F, dir2: T): Unit = {
      def findFile(file: F, directory: T): Option[T] =
        (for {file2 <- toHelper.children(directory)
              if fromHelper.name(file) == toHelper.name(file2)
        } yield file2).headOption

      for (file1 <- fromHelper.children(dir1)) {
        val file2 = findFile(file1, dir2).
          getOrElse(toHelper.child(dir2,
          fromHelper.name(file1)))
        if (fromHelper.isDirectory(file1)) {
          toHelper.mkdirs(file2)
        }
        synchronize[F, T](file1, file2)
      }
    }

    if (fromHelper.isDirectory(from)) {
      synchronizeDirectory(from, to)
    } else {
      synchronizeFile(from, to)
    }
  }

}