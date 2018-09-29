package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, ControllerComponents, Request}
import request.UserLoginForm.userLoginForm
import request.UserRegistrationForm.userRegistrationForm


@Singleton
class UserController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def login() = Action { implicit request =>
    Ok(views.html.login(""))
  }

  def signin() = Action { implicit request =>
    val loginData = userLoginForm.bindFromRequest.get
    println(loginData.toString)
    Ok(views.html.login(""))
  }

  def signup() = Action { implicit request =>
    val customerData = userRegistrationForm.bindFromRequest.get
    println(customerData.toString)
    Ok(views.html.login(""))
  }


}
