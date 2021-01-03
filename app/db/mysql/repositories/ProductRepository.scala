package db.mysql.repositories

import javax.inject.Inject
import db.mysql.DatabaseService
import db.mysql.tables.ProductTable
import model.Product

import scala.concurrent.{ExecutionContext, Future}

class ProductRepository@Inject()(databaseService: DatabaseService) extends ProductTable {

  import databaseService._
  import databaseService.driver.api._

  def filterQuery(id: Long) =
    products.filter(_.id === id)

  def getAll()(implicit ec: ExecutionContext): Future[Seq[Product]] = {
    val query = products.result
    db.run(query)
  }

  def get(id: Long)(implicit ec: ExecutionContext): Future[Product] =
    db.run(filterQuery(id).result.head)

  def insert(product: Product)(implicit ec: ExecutionContext): Future[Product] = db
    .run(products returning products.map(_.id) += product)
    .map(id => product.copy(id = Some(id)))

  def update(id: Long, product: Product)(implicit ec: ExecutionContext): Future[Int] =
    db.run(filterQuery(id).update(product))

  def delete(id: Long)(implicit ec: ExecutionContext): Future[Int] =
    db.run(filterQuery(id).delete)
}
