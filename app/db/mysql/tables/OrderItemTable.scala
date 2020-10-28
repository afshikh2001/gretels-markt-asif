package db.mysql.tables

import model.{OrderItem, Price, Quantity}
import slick.jdbc.MySQLProfile.api._
import slick.lifted.ProvenShape

trait OrderItemTable {

  class OrderItemsTable(tag: Tag) extends Table[OrderItem](tag, "order_items") {

    def id = column[Long]("id")

    def name = column[String]("name")

    def quantity = column[Int]("quantity")

    def quantityUnit = column[String]("quantity_unit")

    def price = column[Double]("price")

    def priceUnit = column[String]("price_unit")

    def productId = column[Long]("product_id")

    def orderId = column[Long]("order_id")

    def createdAt = column[Long]("created_at")

    def updatedAt = column[Long]("updated_at")

    def * : ProvenShape[OrderItem] = (
      id,
      name,
      (quantity, quantityUnit),
      (price, priceUnit),
      productId,
      orderId,
      createdAt,
      updatedAt).shaped <> ( {
      case (id, name, quantity, price, productId, orderId, createdAt, updatedAt) =>
        OrderItem(id, name, Quantity.tupled.apply(quantity), Price.tupled.apply(price), productId, orderId, createdAt, updatedAt)
    }, { orderItem: OrderItem =>
      Some(
        orderItem.id, orderItem.name, Quantity.unapply(orderItem.itemQuantity).get, Price.unapply(orderItem.itemPrice).get,
        orderItem.productId, orderItem.orderId, orderItem.createdAt, orderItem.updatedAt
      )
    })
  }

  protected val orderItems = TableQuery[OrderItem]

}