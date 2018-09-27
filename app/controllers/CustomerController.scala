package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}
import request.CustomerLoginForm
import request.CustomerLoginForm.customerLoginForm
import request.CustomerRegistrationForm.customerRegistrationForm


@Singleton
class CustomerController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def login() = Action { implicit request =>
    Ok(views.html.login(""))
  }

  def signin() = Action { implicit request =>
    val loginData = customerLoginForm.bindFromRequest.get
    println(loginData.toString)
    Ok(views.html.login(""))
  }

  def signup() = Action { implicit request =>
    val customerData = customerRegistrationForm.bindFromRequest().get
    println(customerData.toString)
    Ok(views.html.login(""))
  }


}
