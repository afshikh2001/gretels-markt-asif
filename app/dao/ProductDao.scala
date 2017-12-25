package dao

import db.client.JdbcClient
import model.Product
import scalikejdbc._

class ProductDao(jdbcClient: JdbcClient) {

  def getProduct(id: Long): Product = {

    ???
  }


}


object ProductDao {

  private val idField = sqls"id"
  private val nameField = sqls"name"
  private val productTypeField = sqls"product_type"
  private val quantityField = sqls"quantity"
  private val quantityUnitField = sqls"quantity_unit"
  private val priceField = sqls"price"
  private val priceUnitField = sqls"price_unit"
  private val angebotField = sqls"angebot"
  private val gesmeckField = sqls"gesmeck"
  private val mediaField = sqls"media"
  private val createdAtField = sqls"created_at"
  private val updatedAtField = sqls"updated_at"


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