val commonSettings = Seq(
  version := "1.0",
  scalaVersion := "2.12.6"
)

lazy val root = (project in file("."))
  .settings(commonSettings)
  .settings(
    name := "sbt-assignment",
    publishArtifact := false
  )
  .aggregate(dbOperations, dto)
  .dependsOn(dbOperations, dto)

lazy val dbOperations = (project in file("db-operations"))
  .settings(commonSettings)
  .settings(
    name := "db",
    libraryDependencies ++= Seq(
      "com.typesafe.slick" %% "slick" % "3.2.0",

      "com.h2database" % "h2" % "1.4.196",

      "org.scalatest" %% "scalatest" % "3.0.5" % "test",
      "org.slf4j" % "slf4j-nop" % "1.6.4"

    )
  ).dependsOn(dto)

lazy val dto = (project in file("dto"))
  .settings(commonSettings)
  .settings(
    name := "dto",
    libraryDependencies ++= Seq(

      "org.scalatest" %% "scalatest" % "3.0.5" % "test",
      "org.slf4j" % "slf4j-nop" % "1.6.4"
    )
  )