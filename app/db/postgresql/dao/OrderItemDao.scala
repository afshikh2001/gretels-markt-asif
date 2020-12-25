package db.postgresql.dao

import db.postgresql.client.JdbcClient
import model.{OrderItem, Price, Quantity}
import scalikejdbc._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global


class OrderItemDao(client: JdbcClient)  {

  import OrderItemDao._

  def addOrderItem(orderItem: OrderItem): Future[Unit] = {
    Future {
      client.db autoCommit { implicit session =>
        sql"""
             |INSERT INTO $orderItemTable ($orderItemFields)
             |VALUES(${orderItem.id},${orderItem.name},${orderItem.itemQuantity.value},${orderItem.itemQuantity.unit},
             |${orderItem.itemPrice.value},${orderItem.itemPrice.unit},${orderItem.productId},${orderItem.orderId},
             |${orderItem.createdAt},${orderItem.updatedAt})
             |;""".stripMargin
          .update()
          .apply()
      }
    }
  }

  def deleteOrderItem(id: Long): Future[Unit] = {
    Future {
      client.db autoCommit { implicit session =>
        sql"""DELETE FROM $orderItemTable
             | WHERE $idField = $id;
           """.stripMargin
          .update()
          .apply()
      }
    }
  }

  def getOrderItem(id: Long): Future[Option[OrderItem]] = {
    Future {
      client.db readOnly { implicit session =>
        sql"""SELECT * FROM $orderItemTable
             | WHERE $idField = ${id}
             | Limit 1;""".stripMargin
          .map(orderItemMapper)
          .single()
          .apply()
      }
    }
  }

  def getOrderItems(orderId: Long, limit: Int = 20, offset: Int): Future[List[OrderItem]] = {
    Future {
      client.db readOnly { implicit session =>
        sql"""SELECT * FROM $orderItemTable
             |WHERE $orderId=$orderId
             | Limit $limit OFFSET $offset;""".stripMargin
          .map(orderItemMapper)
          .collection
          .apply()
      }
    }
  }

}


object OrderItemDao {

  private val orderItemTable = sqls"order_item"

  private val idField = sqls"id"
  private val nameField = sqls"name"
  private val quantityField = sqls"quantity"
  private val quantityUnitField = sqls"quantity_unit"
  private val priceField = sqls"price"
  private val priceUnitField = sqls"price_unit"
  private val productIdField = sqls"product_id"
  private val orderIdField = sqls"order_id"
  private val createdAtField = sqls"created_at"
  private val updatedAtField = sqls"updated_at"

  private val orderItemFields = sqls.csv(idField,
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
    id = rs.longOpt(idField),
    name = rs.string(nameField),
    itemQuantity = quantityMapper(rs),
    itemPrice = priceMapper(rs),
    productId = rs.long(productIdField),
    orderId = rs.longOpt(orderIdField),
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