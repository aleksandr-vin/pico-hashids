name := "pico-hashids"

organization := "org.picoworks"

crossScalaVersions := Seq("2.10.6", "2.11.8", "2.12.0")

version in ThisBuild := Process("./version.sh").lines.head.trim

licenses in ThisBuild := Seq("MIT" -> url("http://opensource.org/licenses/MIT"))
publishMavenStyle in ThisBuild := false
bintrayOrganization in bintray := None
