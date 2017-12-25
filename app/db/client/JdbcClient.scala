package db.client

import db.config.JdbcServerConfig
import scalikejdbc._

class JdbcClient(server: JdbcServerConfig, database: String, username: String, password: String) {

  private val uri = s"jdbc:postgresql://${server.host}:${server.port}/$database"
  val name = s"${server.host}:${server.port}/$database"

  Class.forName("org.postgresql.Driver")
  ConnectionPool.add(name = name,uri,username,password,ConnectionPoolSettings(
    initialSize = server.jdbcConnectionPoolSettings.intialSize,
    maxSize = server.jdbcConnectionPoolSettings.maxSize,
    connectionTimeoutMillis =server.jdbcConnectionPoolSettings.connectionTimeOutMillis
  ))

  def db=NamedDB(name)
}
