package db.mysql.repositories

import javax.inject.Inject
import db.mysql.DatabaseService
import db.mysql.tables.ProductTable
import model.Product
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

class ProductRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) extends ProductTable with HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._

  def filterQuery(id: Long) =
    products.filter(_.id === id)

  def getAll(): Future[Seq[Product]] = {
    val query = products.result
    db.run(query)
  }

  def get(id: Long): Future[Product] =
    db.run(filterQuery(id).result.head)

  def insert(product: Product): Future[Product] = db
    .run(products returning products.map(_.id) += product)
    .map(id => product.copy(id = Some(id)))

  def update(id: Long, product: Product): Future[Int] =
    db.run(filterQuery(id).update(product))

  def delete(id: Long): Future[Int] =
    db.run(filterQuery(id).delete)
}
