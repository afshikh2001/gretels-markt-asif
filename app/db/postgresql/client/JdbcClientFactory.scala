package db.postgresql.client

import db.postgresql.config.JdbcConfig

object JdbcClientFactory {
  def createClient(config: JdbcConfig): JdbcClient = {
    new JdbcClient(config.master, config.database, config.username, config.password)
  }
}
