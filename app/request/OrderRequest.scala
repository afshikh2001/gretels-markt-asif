package request

import play.api.libs.functional.syntax.{toFunctionalBuilderOps, unlift}
import play.api.libs.json.{JsPath, Reads, Writes}

case class OrderRequest (customerId:Long, orderItems:List[RequestedItem])

case class RequestedItem(productId: Long, quantity: RequestedQuantity)

case class RequestedQuantity(value: Int, unit: String)


object OrderRequest {

  implicit val quantityWrites: Writes[RequestedQuantity] =
    (JsPath \ "value").write[Int]
      .and((JsPath \ "unit").write[String])(unlift(RequestedQuantity.unapply))


  implicit val requestedItemWrites: Writes[RequestedItem] =
    (JsPath \ "productId").write[Long]
      .and((JsPath \ "quantity").write[RequestedQuantity])(unlift(RequestedItem.unapply))


  implicit val orderRequestWrites: Writes[OrderRequest] =
    (JsPath \ "customerId").write[Long]
      .and((JsPath \ "orderItems").write[List[RequestedItem]])(unlift(OrderRequest.unapply))
  

  implicit val quantityReads: Reads[RequestedQuantity] =
    (JsPath \ "value").read[Int]
      .and((JsPath \ "unit").read[String])(RequestedQuantity.apply _)


  implicit val requestedItemReads: Reads[RequestedItem] =
    (JsPath \ "productId").read[Long]
      .and((JsPath \ "quantity").read[RequestedQuantity])(RequestedItem.apply _)


  implicit val orderRequestReads: Reads[OrderRequest] =
    (JsPath \ "customerId").read[Long]
      .and((JsPath \ "orderItems").read[List[RequestedItem]])(OrderRequest.apply _)
  }