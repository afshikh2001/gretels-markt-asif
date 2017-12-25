package model

case class Product(id: Long,
                   name: String,
                   productType: String,
                   quantity: Int,
                   quantityUnit: String,
                   price: Double,
                   priceUnit: String,
                   angebot: Option[Boolean],
                   gesmeck: Option[String],
                   media: String,
                   createdAt: Long,
                   updatedAt: Long
                  )
object Product {

}