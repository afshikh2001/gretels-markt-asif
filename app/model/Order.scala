package model

case class Order(id: Option[Long],
                 price: Price,
                 customerId: Long,
                 createdAt: Long,
                 updatedAt: Long
                )

