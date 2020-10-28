package db.mysql

import com.typesafe.config.Config
import slick.jdbc.MySQLProfile
import slick.jdbc.MySQLProfile.api._

class MySQLDatabaseService(config: Config) extends DatabaseService {
  override val driver = MySQLProfile
  override val db = Database.forConfig("db", config)
}
