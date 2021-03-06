import sbt._
import Keys._

object Build extends sbt.Build {
  val specs2_core       = "org.specs2"      %%  "specs2-core"         % "3.8.8"
  val specs2_scalacheck = "org.specs2"      %%  "specs2-scalacheck"   % "3.8.8"
  val scalacheck        = "org.scalacheck"  %%  "scalacheck"          % "1.13.4"

  implicit class ProjectOps(self: Project) {
    def standard: Project = {
      self
          .settings(organization := "org.picoworks")
          .settings(resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases")
          .settings(scalacOptions := Seq("-feature", "-deprecation", "-unchecked", "-Xlint", "-Yrangepos", "-encoding", "utf8"))
          .settings(scalacOptions in Test ++= Seq("-Yrangepos"))
    }

    def notPublished: Project = {
      self
          .settings(publish := {})
          .settings(publishArtifact := false)
    }

    def libs(modules: ModuleID*) = self.settings(libraryDependencies ++= modules)

    def testLibs(modules: ModuleID*) = self.libs(modules.map(_ % "test"): _*)
  }

  lazy val `pico-hashids` = Project(id = "pico-hashids", base = file("pico-hashids"))
      .standard
      .testLibs(scalacheck, specs2_core, specs2_scalacheck)

  lazy val root = Project(id = "all", base = file("."))
      .notPublished
      .aggregate(`pico-hashids`)
}
