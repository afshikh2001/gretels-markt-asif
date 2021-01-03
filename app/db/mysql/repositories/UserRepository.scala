package db.mysql.repositories

import javax.inject.Inject
import db.mysql.tables.UserTable
import model.User
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

class UserRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) extends UserTable with HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._

  def filterQuery(id: Long) =
    users.filter(_.id === id)

  def getAll(): Future[Seq[User]] = {
    val query = users.result
    db.run(query)
  }

  def get(id: Long): Future[User] =
    db.run(filterQuery(id).result.head)

  def insert(user: User): Future[User] = db
    .run(users returning users.map(_.id) += user)
    .map(id => user.copy(id = Some(id)))

  def update(id: Long, user: User): Future[Int] =
    db.run(filterQuery(id).update(user))

  def delete(id: Long): Future[Int] =
    db.run(filterQuery(id).delete)
}
