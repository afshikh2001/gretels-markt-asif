package db.mysql.tables

import model.User
import slick.jdbc.MySQLProfile.api._

trait UserTable {

  class UserTable(tag: Tag) extends Table[User](tag, "user") {

    def id = column[Long]("id")

    def firstName = column[String]("firstName")

    def lastName = column[String]("lastName")

    def type_ = column[String]("user_type")

    def email = column[String]("email")

    def password = column[String]("password")

    def plz = column[Int]("plz")

    def strasse = column[String]("strasse")

    def ort = column[String]("ort")

    def country = column[String]("country")

    def additionalAddress = column[String]("additional_address")

    def createdAt = column[Long]("created_at")

    def updatedAt = column[Long]("updated_at")


    def * =
      (id,
        firstName,
        lastName,
        type_,
        email,
        password,
        plz,
        strasse,
        ort,
        country,
        additionalAddress,
        createdAt,
        updatedAt) <> ((User.apply _).tupled, User.unapply)
  }

  protected val users = TableQuery[User]

}