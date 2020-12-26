package model

case class OrderItem(id: Option[Long],
                     name: String,
                     itemQuantity: Quantity,
                     itemPrice: Price,
                     productId:Long,
                     orderId: Long,
                     payment: Boolean,
                     createdAt: Long,
                     updatedAt: Long
                    )

object OrderItem {

  def apply(productId: Long,
            productName:String,
            itemQuantity: Quantity,
            itemPrice: Price,
            orderId: Long): OrderItem = {
    val now = System.currentTimeMillis()
    new OrderItem(
      id = None,
      name = productName,
      itemQuantity = itemQuantity,
      itemPrice = itemPrice,
      productId=productId,
      orderId = orderId,
      payment = false,
      createdAt = now,
      updatedAt = now
    )
  }
}


