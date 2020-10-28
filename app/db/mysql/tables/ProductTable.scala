package db.mysql.tables

import model.{Price, Product, Quantity}
import slick.jdbc.MySQLProfile.api._

trait ProductTable {

  trait ProductTable {

    class OrderItemsTable(tag: Tag) extends Table[Product](tag, "products") {

      def id = column[Long]("id")

      def name = column[String]("name")

      def productType = column[String]("product_type")

      def quantity = column[Int]("quantity")

      def quantityUnit = column[String]("quantity_unit")

      def price = column[Double]("price")

      def priceUnit = column[String]("price_unit")

      def angebot = column[Option[Boolean]]("angebot")

      def gesmeck = column[Option[String]]("gesmeck")

      def media = column[String]("media")

      def createdAt = column[Long]("created_at")

      def updatedAt = column[Long]("updated_at")

      def * = (
        id,
        name,
        productType,
        (quantity, quantityUnit),
        (price, priceUnit),
        angebot,
        gesmeck,
        media,
        createdAt,
        updatedAt).shaped <> ( {
        case (id, name, productType, quantity, price, angebot, gesmeck, media, createdAt, updatedAt) =>
           Product(id, name, productType, Quantity.tupled.apply(quantity), Price.tupled.apply(price), angebot, gesmeck, media, createdAt, updatedAt)
      }, { product: Product =>
        Some(
          product.id, product.name, Quantity.unapply(product.quantity).get, Price.unapply(product.price).get,
          product.angebot, product.gesmeck, product.media, product.createdAt, product.updatedAt
        )
      })
    }

    protected val products = TableQuery[Product]

  }


}