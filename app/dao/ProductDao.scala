package dao

import db.client.JdbcClient
import model.Product
import scalikejdbc._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ProductDao(client: JdbcClient) {

  import ProductDao._

  def addProduct(product: Product): Future[Unit] = {
    Future {
      client.db autoCommit { implicit session =>
        sql"""
             |INSERT INTO $productsTable ($productFields)
             |VALUES(${product.id},${product.name},${product.productType},${product.quantity},
             |${product.quantityUnit},${product.price},${product.priceUnit},${product.angebot},${product.gesmeck},
             |${product.media},${product.createdAt},${product.updatedAt});
             |""".stripMargin
          .update()
          .apply()
      }
    }
  }

  def deleteProduct(id: Long): Future[Unit] = {
    Future {
      client.db autoCommit { implicit session =>
        sql"""DELETE FROM $productsTable
             | WHERE $idField = ${id};
           """.stripMargin
          .update()
          .apply()
      }
    }
  }

  def getProduct(id: Long): Future[Option[Product]] = {
    Future {
      client.db readOnly { implicit session =>
        sql"""SELECT * FROM $productsTable
             | WHERE $idField = $id
             | Limit 1;""".stripMargin
          .map(productMapper)
          .single()
          .apply()
      }
    }
  }

  def getProducts(limit: Int = 20, offset: Int): Future[List[Product]] = {
    Future {
      client.db readOnly { implicit session =>
        sql"""SELECT * FROM $productsTable
             | Limit $limit OFFSET $offset;""".stripMargin
          .map(productMapper)
          .collection
          .apply()
      }
    }
  }


}


object ProductDao {

  private val productsTable = sqls"products"

  private val idField = sqls"id"
  private val nameField = sqls"name"
  private val productTypeField = sqls"type"
  private val quantityField = sqls"quantity"
  private val quantityUnitField = sqls"quantity_unit"
  private val priceField = sqls"price"
  private val priceUnitField = sqls"price_unit"
  private val angebotField = sqls"angebot"
  private val gesmeckField = sqls"gesmeck"
  private val mediaField = sqls"media"
  private val createdAtField = sqls"created_at"
  private val updatedAtField = sqls"updated_at"

  private val productFields = sqls.csv(idField,
    nameField,
    productTypeField,
    quantityField,
    quantityUnitField,
    priceField,
    priceUnitField,
    angebotField,
    gesmeckField,
    mediaField,
    createdAtField,
    updatedAtField)

  private val productMapper = (rs: WrappedResultSet) => Product(
    id = rs.long(idField),
    name = rs.string(nameField),
    productType = rs.string(productTypeField),
    quantity = rs.int(quantityField),
    quantityUnit = rs.string(quantityUnitField),
    price = rs.double(priceField),
    priceUnit = rs.string(priceUnitField),
    angebot = rs.booleanOpt(angebotField),
    gesmeck = rs.stringOpt(gesmeckField),
    media = rs.string(mediaField),
    createdAt = rs.long(createdAtField),
    updatedAt = rs.long(updatedAtField)
  )


}