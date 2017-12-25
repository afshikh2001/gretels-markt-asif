package db.config

import com.typesafe.config.ConfigFactory

object JdbcConfigProvider {
  val config: JdbcConfig = JdbcConfig.parse(ConfigFactory.parseString(
    s"""
       |database=""
       |username=""
       |password=""
       |connection-pool{
       |  initial-size=4,
       |  max-size=8,
       |  connection-timeout=15000
       |}
       |master{
       |  host=""
       |  port=""
       |}
     """.stripMargin
  ))
}
