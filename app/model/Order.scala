package model


object Order {

  def apply(customerId: Long, orderItems: List[OrderItem]): Order = {
    val price_value = orderItems.map(item => item.itemPrice.value).sum
    val price = new Price(price_value, "EUR")
    val now = System.currentTimeMillis()
    new Order(None, price, customerId, now, now);
  }


}


case class Order(id: Option[Long],
                 price: Price,
                 customerId: Long,
                 createdAt: Long,
                 updatedAt: Long
                )

