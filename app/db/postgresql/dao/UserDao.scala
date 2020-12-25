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
             |VALUES(${user.id},${user.firstName},${user.lastName},${user.email},
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
  private val vorNameField = sqls"first_name"
  private val nachNameField = sqls"last_name"
  private val userTypeField = sqls"user_type"
  private val emailField = sqls"email"
  private val passwordField = sqls"password"
  private val plzField = sqls"plz"
  private val straßeField = sqls"strasse"
  private val ortField = sqls"ort"
  private val countryField = sqls"country"
  private val additionalAddressField = sqls"additional_address"
  private val createdAtField = sqls"created_at"
  private val updatedAtField = sqls"updated_at"

  private val userFields = sqls.csv(
    idField,
    vorNameField,
    nachNameField,
    userTypeField,
    emailField,
    passwordField,
    plzField,
    straßeField,
    ortField,
    countryField,
    additionalAddressField,
    createdAtField,
    updatedAtField
  )

  private val userMapper = (rs: WrappedResultSet) => User(
    id = rs.longOpt(idField),
    firstName = rs.string(vorNameField),
    lastName = rs.string(nachNameField),
    type_ = rs.string(userTypeField),
    email = rs.string(emailField),
    password = rs.string(passwordField),
    plz = rs.int(plzField),
    straße = rs.string(straßeField),
    ort = rs.string(ortField),
    country = rs.string(countryField),
    additionalAddress = rs.string(additionalAddressField),
    createdAt = rs.long(updatedAtField),
    updatedAt = rs.long(updatedAtField)
  )

}