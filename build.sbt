import sbt.Keys._
import sbt._

// sbt + publish-signed
name := "scalatest-embedmongo"
organization := "com.github.simplyscala"
description := "API to use embedded mongoDb database for testing in Scala"

version := "0.3.0"

scalaVersion := "2.13.8"

crossScalaVersions := Seq("2.13.8")

libraryDependencies ++= Seq(
  "de.flapdoodle.embed" % "de.flapdoodle.embed.mongo" % "3.4.5",
  "org.scalatest" %% "scalatest" % "3.2.12" % "test",
  "org.mongodb.scala" %% "mongo-scala-driver" % "4.6.0" % "test"
)

parallelExecution := false

publishMavenStyle := true
publishArtifact in Test := false
pomIncludeRepository := { _ => false }

pomExtra in Global := {
  <url>https://github.com/SimplyScala/scalatest-embedmongo</url>
    <licenses>
      <license>
        <name>GPLv3</name>
        <url>http://www.gnu.org/licenses/gpl-3.0.html</url>
        <distribution>repo</distribution>
      </license>
    </licenses>
    <scm>
      <url>git@github.com:SimplyScala/scalatest-embedmongo.git</url>
      <connection>scm:git:git@github.com:SimplyScala/scalatest-embedmongo.git</connection>
    </scm>
    <developers>
      <developer>
        <id>ugobourdon</id>
        <name>bourdon.ugo@gmail.com</name>
        <url>https://github.com/ubourdon</url>
      </developer>
      <developer>
        <id>tg44</id>
        <name>tg44</name>
        <url>https://github.com/tg44</url>
      </developer>
    </developers>
}

/**
  * how to publish
  * ______________
  *
  * https://github.com/xerial/sbt-sonatype
  * http://www.scala-sbt.org/sbt-pgp/usage.html
  * http://www.loftinspace.com.au/blog/publishing-scala-libraries-to-sonatype.html#install_pgp_plugin
  * http://blog.sonatype.com/2010/01/how-to-generate-pgp-signatures-with-maven/
  */

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if(version.value.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
}
