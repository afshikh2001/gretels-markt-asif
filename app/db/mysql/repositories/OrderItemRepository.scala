package db.mysql.repositories

import db.mysql.DatabaseService
import db.mysql.tables.OrderItemTable
import model.OrderItem

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class OrderItemRepository @Inject()(databaseService: DatabaseService) extends OrderItemTable {

  import databaseService._
  import databaseService.driver.api._

  def filterQuery(id: Long)=
    orderItems.filter(_.id === id)

  def getAll()(implicit ec: ExecutionContext): Future[Seq[OrderItem]] = {
    val query = orderItems.result
    db.run(query)
  }

  def get(id: Long)(implicit ec: ExecutionContext): Future[OrderItem] =
    db.run(filterQuery(id).result.head)

  def insert(orderItem: OrderItem)(implicit ec: ExecutionContext): Future[OrderItem] = db
    .run(orderItems returning orderItems.map(_.id) += orderItem)
    .map(id => orderItem.copy(id = Some(id)))

  def update(id: Long, orderItem: OrderItem)(implicit ec: ExecutionContext): Future[Int] =
    db.run(filterQuery(id).update(orderItem))

  def delete(id: Long)(implicit ec: ExecutionContext): Future[Int] =
    db.run(filterQuery(id).delete)
}
