lazy val root = (project in file(".")).
  settings(
    name := "xiongfeng165",
    version := "1.0",
    scalaVersion := "2.11.7"
  )

credentials += Credentials("Sonatype Nexus Repository Manager", "repo.qiaobutang.com", "qbt", "52b2ca75e4b068789b5bc5eb")

resolvers ++= Seq(
  "qbt-private" at "http://repo.qiaobutang.com:9876/nexus/content/groups/public/"
)

libraryDependencies ++= {
  val liftVersion = "2.6"
  Seq(
    "net.liftweb" % "lift-webkit_2.10" % liftVersion,
    "com.typesafe.akka" % "akka-actor_2.10" % "2.3.14" withSources(),
    "junit" % "junit" % "4.12" withSources(),
    "javax.servlet" % "javax.servlet-api" % "3.1.0" withSources(),
    "org.apache.spark" % "spark-core_2.10" % "1.6.0" withSources(),
    "com.github.xuwei-k" % "html2image" % "0.1.0"
  )
}