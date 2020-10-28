package db.postgresql.config

import com.typesafe.config.ConfigFactory

object JdbcConfigProvider {
  val config: JdbcConfig = JdbcConfig.parse(ConfigFactory.parseString(
    s"""
       |database="gretelsmarkt"
       |username="root"
       |password="Changeit456!"
       |master{
       |  host="localhost"
       |  port="5432"
       |connection-pool{
       |  initial-size=4
       |  max-size=8
       |  connection-timeout=15000
       |}
       |}
     """.stripMargin
  ))
}
