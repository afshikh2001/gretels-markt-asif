package model

case class OrderItem(id: Long,
                     name: String,
                     quantity: Int,
                     quantityUnit: String,
                     price: Double,
                     priceUnit: String,
                     productId:Long,
                     orderId: Long,
                     createdAt: Long,
                     updatedAt: Long
                    )

object OrderItem {

}