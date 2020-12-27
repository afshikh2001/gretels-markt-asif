package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, ControllerComponents, Request}
import request.forms.{UserLoginForm, UserRegistrationForm}


@Singleton
class UserController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {


  def signin() = Action { implicit request =>
    val loginData = UserLoginForm.userLoginForm.bindFromRequest.get
    Ok("")
  }

  def signup() = Action { implicit request =>
    val customerData = UserRegistrationForm.userRegistrationForm.bindFromRequest.get
    println(customerData.toString)
    Ok("")
  }

  def create()= Action { implicit request =>
    val customerData = UserRegistrationForm.userRegistrationForm.bindFromRequest.get
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
