package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, ControllerComponents, Request}
import request.UserLoginForm.userLoginForm
import request.UserRegistrationForm.userRegistrationForm
import services.UserService


@Singleton
class UserController @Inject()(cc: ControllerComponents, userService: UserService) extends AbstractController(cc) {


  def signin() = Action { implicit request =>
    val loginData = userLoginForm.bindFromRequest.get
    userService.userSignin(email = loginData.email, password = loginData.password)
    Ok("")
  }

  def signup() = Action { implicit request =>
    val customerData = userRegistrationForm.bindFromRequest.get
    println(customerData.toString)
    Ok("")
  }

  def create()= Action { implicit request =>
    val customerData = userRegistrationForm.bindFromRequest.get
    println(customerData.toString)
    Ok("")
  }

  def updateUser()=Action{implicit request=>
    Ok("")
  }

  def showUserDetails()=Action{implicit request=>
    Ok("")
  }





}
