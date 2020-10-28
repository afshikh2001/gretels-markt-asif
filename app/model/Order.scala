package model

case class Order(id: Long,
                 items: Seq[OrderItem],
                 price: Price,
                 customerId: Long,
                 createdAt: Long,
                 updatedAt: Long
                )

object Order {
  def apply(userId: Long, items: List[OrderItem]): Order = {
    //new Order(id, items, price, customerId, createdAt, updatedAt)
    ???
  }
}