package db.postgresql.dao

import db.postgresql.client.JdbcClient
import model.User
import scalikejdbc._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UserDao(client: JdbcClient) {

  import UserDao._

  def addUser(user: User): Future[Unit] = {
    Future {
      client.db autoCommit { implicit session =>
        sql"""
             |INSERT INTO $usersTable ($userFields)
             |VALUES(${user.id},${user.vorName},${user.nachName},${user.email},
             |${user.password},${user.plz},${user.straße},${user.ort},${user.country},
             |${user.additionalAddress},${user.updatedAt})
             |;""".stripMargin
          .update()
          .apply()
      }
    }
  }

  def deleteUser(id: Long): Future[Unit] = {
    Future {
      client.db autoCommit { implicit session =>
        sql"""DELETE FROM $usersTable
             | WHERE ${idField} = $id;
           """.stripMargin
          .update()
          .apply()
      }
    }
  }

  def getUser(id: Long): Future[Option[User]] = {
    Future {
      client.db readOnly { implicit session =>
        sql"""SELECT * FROM $usersTable
             | WHERE ${idField} = $id
             | Limit 1;""".stripMargin
          .map(userMapper)
          .single()
          .apply()
      }
    }
  }

  def getUsers(limit: Int = 20, offset: Int): Future[List[User]] = {
    Future {
      client.db readOnly { implicit session =>
        sql"""SELECT * FROM $usersTable
             | Limit $limit OFFSET $offset;""".stripMargin
          .map(userMapper)
          .collection
          .apply()
      }
    }
  }


}


object UserDao {

  private val usersTable = sqls"users"

  private val idField = sqls"id"
  private val vorNameField = sqls"name"
  private val nachNameField = sqls"type"
  private val emailField = sqls"quantity"
  private val passwordField = sqls"quantity_unit"
  private val plzField = sqls"price"
  private val straßeField = sqls"price_unit"
  private val ortField = sqls"angebot"
  private val countryField = sqls"gesmeck"
  private val additionalAddressField = sqls"media"
  private val updatedAtField = sqls"updated_at"

  private val userFields = sqls.csv(
    idField,
    vorNameField,
    nachNameField,
    emailField,
    passwordField,
    plzField,
    straßeField,
    ortField,
    countryField,
    additionalAddressField,
    updatedAtField
  )

  private val userMapper = (rs: WrappedResultSet) => User(
    id = rs.long(idField),
    vorName = rs.string(vorNameField),
    nachName = rs.string(nachNameField),
    email = rs.string(emailField),
    password = rs.string(passwordField),
    plz = rs.int(plzField),
    straße = rs.string(straßeField),
    ort = rs.string(ortField),
    country = rs.string(countryField),
    additionalAddress = rs.string(additionalAddressField),
    updatedAt = rs.long(updatedAtField)
  )
  
}