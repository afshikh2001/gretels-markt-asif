package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, ControllerComponents}

@Singleton
class OrderController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def createOrder() = Action { implicit request =>
    Ok("")
  }

  def cancelOrder() = Action { implicit request =>
    Ok("")
  }

  def showOrderDetails() = Action { implicit request =>
    Ok("")
  }

}
