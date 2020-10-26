package model

case class Order(id: Long,
                 items: Seq[OrderItem],
                 price: Price,
                 customerId: Long,
                 createdAt: Long,
                 updatedAt: Long
                )

object Order {

}