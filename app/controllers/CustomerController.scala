package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}
import request.CustomerData
import request.CustomerData.customerDataForm


@Singleton
class CustomerController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def login() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.login(""))
  }

  def signin() = Action { implicit request: Request[AnyContent] =>

    Ok(views.html.login(""))
  }

  def signup() = Action(parse.form(customerDataForm)) { implicit request =>
    val customerData = customerDataForm.bindFromRequest().get
    println(customerData.toString)
    Ok(views.html.login(""))
  }


}
