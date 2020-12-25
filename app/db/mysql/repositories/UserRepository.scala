package db.mysql.repositories

import db.mysql.DatabaseService
import db.mysql.tables.UserTable
import model.User

import scala.concurrent.{ExecutionContext, Future}

class UserRepository (databaseService: DatabaseService) extends UserTable {

  import databaseService._
  import databaseService.driver.api._

  def filterQuery(id: Long) =
    users.filter(_.id === id)

  def getAll()(implicit ec: ExecutionContext): Future[Seq[User]] = {
    val query = users.result
    db.run(query)
  }

  def get(id: Long)(implicit ec: ExecutionContext): Future[User] =
    db.run(filterQuery(id).result.head)

  def insert(user: User)(implicit ec: ExecutionContext): Future[User] = db
    .run(users returning users.map(_.id) += user)
    .map(id => user.copy(id = Some(id)))

  def update(id: Long, user: User)(implicit ec: ExecutionContext): Future[Int] =
    db.run(filterQuery(id).update(user))

  def delete(id: Long)(implicit ec: ExecutionContext): Future[Int] =
    db.run(filterQuery(id).delete)
}
