package db.config

import com.typesafe.config.{Config, ConfigFactory}
import db.config.JdbcConnectionPoolSettings.default

case class JdbcConfig(master: JdbcServerConfig,
                      database: String,
                      username: String,
                      password: String)

object JdbcConfig {
  def parse(config: Config): JdbcConfig = {
    JdbcConfig(
      master = JdbcServerConfig.parse(config.getConfig("master")),
      database = config.getString("database"),
      username = config.getString("host"),
      password = config.getString("host")
    )
  }
}


case class JdbcServerConfig(host: String, port: Int, jdbcConnectionPoolSettings: JdbcConnectionPoolSettings)

object JdbcServerConfig {
  def parse(config: Config): JdbcServerConfig = {
    JdbcServerConfig(
      host = config.getString("host"),
      port = config.getInt("port"),
      jdbcConnectionPoolSettings = JdbcConnectionPoolSettings.parse(config.getConfig("connection-pool")))
  }
}


case class JdbcConnectionPoolSettings(intialSize: Int = 0, maxSize: Int = 0, connectionTimeOutMillis: Long = 5000L)

object JdbcConnectionPoolSettings {

  val default = JdbcConnectionPoolSettings()

  def parse(config: Config): JdbcConnectionPoolSettings = {
    JdbcConnectionPoolSettings(
      intialSize = Option(config.getInt("initial-size")).getOrElse(default.intialSize),
      maxSize = Option(config.getInt("max-size")).getOrElse(default.maxSize),
      connectionTimeOutMillis = Option(config.getLong("connection-timeout")).getOrElse(default.connectionTimeOutMillis))
  }
}



