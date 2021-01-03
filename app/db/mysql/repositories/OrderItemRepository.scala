package db.mysql.repositories

import db.mysql.tables.OrderItemTable
import model.OrderItem
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class OrderItemRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) extends OrderItemTable with HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._

  def filterQuery(id: Long) =
    orderItems.filter(_.id === id)

  def getAll: Future[Seq[OrderItem]] = {
    val query = orderItems.result
    db.run(query)
  }

  def get(id: Long): Future[OrderItem] =
    db.run(filterQuery(id).result.head)

  def insert(orderItem: OrderItem): Future[OrderItem] = db
    .run(orderItems returning orderItems.map(_.id) += orderItem)
    .map(id => orderItem.copy(id = Some(id)))

  def update(id: Long, orderItem: OrderItem): Future[Int] =
    db.run(filterQuery(id).update(orderItem))

  def delete(id: Long): Future[Int] =
    db.run(filterQuery(id).delete)
}
