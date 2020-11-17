package model

case class OrderItem(id: Option[Long],
                     name: String,
                     itemQuantity: Quantity,
                     itemPrice: Price,
                     productId: Long,
                     orderId: Long,
                     createdAt: Long,
                     updatedAt: Long
                    )

object OrderItem {
}


