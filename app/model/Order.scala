package model

case class Order(id: Long,
                 items: Seq[OrderItem],
                 customerId: Long,
                 createdAt: Long,
                 updatedAt: Long
                )

object Order {

}