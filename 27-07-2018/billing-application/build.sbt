
val commonSettings = Seq(
  version := "0.1",
  scalaVersion := "2.12.6"
)

lazy val root = (project in file("."))
  .settings(commonSettings)
  .settings(
    name := "billing-application",
    libraryDependencies ++= Seq ("org.scalatest" %% "scalatest" % "3.0.5" % "test",
      "org.mockito" % "mockito-all" % "1.9.5"
    )
  )
  .dependsOn(cart, product)
  .aggregate(cart, product)

lazy val cart = (project in file("Cart"))
  .settings(commonSettings)
  .settings(
    name := "cart",
    libraryDependencies ++= Seq ("org.scalatest" %% "scalatest" % "3.0.5" % "test",
                                 "org.mockito" % "mockito-all" % "1.9.5"
    )
  ).dependsOn(product)

lazy val product = (project in file("Product"))
  .settings(commonSettings)
  .settings(
    name := "product",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"
  )
