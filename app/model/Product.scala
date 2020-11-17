package model

case class Product(id: Option[Long],
                   name: String,
                   productType: String,
                   quantity: Quantity,
                   price: Price,
                   angebot: Option[Boolean],
                   gesmeck: Option[String],
                   media: String,
                   createdAt: Long,
                   updatedAt: Long
                  )
