package dao

import dao.OrderDao.{idField, itemsField}
import db.client.JdbcClient
import model.{Price, Quantity, Order, OrderItem}
import scalikejdbc._


class OrderItemDao {

}


object OrderItemDao {

  private val orderItemsTable = sqls"orderItems"

  private val idField = sqls"id"
  private val nameField = sqls"name"
  private val quantityField = sqls"quantity"
  private val quantityUnitField = sqls"quantityUnit"
  private val priceField = sqls"price"
  private val priceUnitField = sqls"priceUnit"
  private val productIdField = sqls"productId"
  private val orderIdField = sqls"orderId"
  private val createdAtField = sqls"created_at"
  private val updatedAtField = sqls"updated_at"

  private val orderFields = sqls.csv(idField,
    nameField,
    quantityField,
    quantityUnitField,
    priceField,
    priceUnitField,
    productIdField,
    orderIdField,
    createdAtField,
    updatedAtField)

   val orderItemMapper = (rs: WrappedResultSet) => OrderItem(
    id = rs.long(idField),
    name = rs.string(nameField),
    itemQuantity = quantityMapper(rs),
    itemPrice = priceMapper(rs),
    productId = rs.long(productIdField),
    orderId = rs.long(orderIdField),
    createdAt = rs.long(createdAtField),
    updatedAt = rs.long(updatedAtField)
  )

  private val quantityMapper = (rs: WrappedResultSet) => Quantity(
    value = rs.int(priceField),
    unit = rs.string(priceUnitField),
  )

  private val priceMapper = (rs: WrappedResultSet) => Price(
    value = rs.double(priceField),
    unit = rs.string(priceUnitField),
  )

}