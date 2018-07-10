package db

import slick.basic.DatabaseConfig
import slick.jdbc.H2Profile

/**
  * Slick
  */
trait DbConfiguration {
  lazy val config = DatabaseConfig.forConfig[H2Profile]("db")
}
