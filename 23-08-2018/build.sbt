name := "kafka-assignment"

version := "0.1"

scalaVersion := "2.12.6"

libraryDependencies ++= {
  Seq(
    "org.apache.kafka" %% "kafka" % "2.0.0",
    "org.apache.kafka" % "kafka-clients" % "0.11.0.0",
    "com.fasterxml.jackson.core" % "jackson-core" % "2.9.6",
    "log4j" % "log4j" % "1.2.17",
    "org.slf4j" % "slf4j-log4j12" % "1.8.0-beta1" % Test

  )
}