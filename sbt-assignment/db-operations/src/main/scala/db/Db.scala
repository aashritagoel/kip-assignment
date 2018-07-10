package db

import slick.basic.DatabaseConfig
import slick.jdbc.H2Profile

/**
  * Slick
  */
trait Db {
  val config: DatabaseConfig[H2Profile]
  val db: H2Profile#Backend#Database = config.db
}
