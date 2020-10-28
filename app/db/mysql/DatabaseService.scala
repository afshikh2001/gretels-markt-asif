package db.mysql

import slick.jdbc.JdbcProfile

trait DatabaseService {

  val driver: JdbcProfile

  import driver.api._

  val db: Database
}
