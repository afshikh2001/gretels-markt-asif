package db.mysql.repositories

import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import javax.inject.Inject
import db.mysql.tables.OrderTable
import model.Order

import scala.concurrent.{ExecutionContext, Future}

class OrderRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) extends OrderTable with HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._

  def filterQuery(id: Long) =
    orders.filter(_.id === id)

  def getAll: Future[Seq[Order]] = {
    val query = orders.result
    db.run(query)
  }

  def get(id: Long): Future[Order] =
    db.run(filterQuery(id).result.head)

  def insert(order: Order): Future[Order] = db
    .run(orders returning orders.map(_.id) += order)
    .map(id => order.copy(id = Some(id)))

  def update(id: Long, order: Order): Future[Int] =
    db.run(filterQuery(id).update(order))

  def delete(id: Long): Future[Int] =
    db.run(filterQuery(id).delete)
}
