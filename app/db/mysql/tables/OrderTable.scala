package db.mysql.tables

import model.{Order, Price}
import slick.jdbc.MySQLProfile.api._
import slick.lifted.ProvenShape

trait OrderTable {

  class OrderTable(tag: Tag) extends Table[Order](tag, "order") {

    def id = column[Long]("id")

    def price = column[Double]("price")

    def priceUnit = column[String]("price_unit")

    def customerId = column[Long]("customer_id")

    def createdAt = column[Long]("created_at")

    def updatedAt = column[Long]("updated_at")

    def * : ProvenShape[Order] = (
      id,
      (price, priceUnit),
      customerId,
      createdAt,
      updatedAt).shaped <> ( {
      case (id, price, customerId, createdAt, updatedAt) =>
        Order(Option(id), Price.tupled.apply(price), customerId, createdAt, updatedAt)
    }, { order: Order =>
      Some(order.id.getOrElse(0), Price.unapply(order.price).get, order.customerId, order.createdAt, order.updatedAt)
    })

  }

  protected val orders = TableQuery[OrderTable]

}
