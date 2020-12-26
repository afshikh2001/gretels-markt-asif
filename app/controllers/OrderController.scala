package controllers

import play.api.libs.json.{JsError, Json}

import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, ControllerComponents}
import request.OrderRequest
import services.OrderService

@Singleton
class OrderController @Inject()(cc: ControllerComponents, orderService: OrderService) extends AbstractController(cc) {

  def createOrder() = Action(parse.json) { implicit request =>
    val placeResult = request.body.validate[OrderRequest]
    placeResult.fold(
      errors => {
        BadRequest(Json.obj("message" -> JsError.toJson(errors)))
      },
      orderRequest => {
        print(orderRequest.toString)
        orderService.createOrder(orderRequest)
        Ok(Json.obj("message" -> ("Place '" + orderRequest.customerId + "' saved.")))
      }
    )
  }

  def cancelOrder() = Action { implicit request =>
    Ok("")
  }

  def showOrderDetails() = Action { implicit request =>
    Ok("")
  }

}
