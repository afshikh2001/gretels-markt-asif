package model

case class OrderItem(id: Option[Long],
                     name: String,
                     itemQuantity: Quantity,
                     itemPrice: Price,
                     productId: Long,
                     orderId: Option[Long],
                     createdAt: Long,
                     updatedAt: Long
                    )

object OrderItem {

  def apply(product: Product, itemQuantity: Quantity, itemPrice: Price): OrderItem = {
    val now = System.currentTimeMillis();
    new OrderItem(None, product.name, itemQuantity, itemPrice, product.id,None, now, now)
  }


}


