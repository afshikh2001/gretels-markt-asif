package db.mysql.repositories

import db.mysql.DatabaseService
import db.mysql.tables.OrderTable
import model.Order
import scala.concurrent.{ExecutionContext, Future}

class OrderRepository(databaseService: DatabaseService) extends OrderTable {

  import databaseService._
  import databaseService.driver.api._

  def filterQuery(id: Long) =
    orders.filter(_.id === id)

  def getAll()(implicit ec: ExecutionContext): Future[Seq[Order]] = {
    val query = orders.result
    db.run(query)
  }

  def get(id: Long)(implicit ec: ExecutionContext): Future[Order] =
    db.run(filterQuery(id).result.head)

  def insert(order: Order)(implicit ec: ExecutionContext): Future[Order] = db
    .run(orders returning orders.map(_.id) += order)
    .map(id => order.copy(id = Some(id)))

  def update(id: Long, order: Order)(implicit ec: ExecutionContext): Future[Int] =
    db.run(filterQuery(id).update(order))

  def delete(id: Long)(implicit ec: ExecutionContext): Future[Int] =
    db.run(filterQuery(id).delete)
}
