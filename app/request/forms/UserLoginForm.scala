package request.forms

import play.api.data.Forms._
import play.api.data._

case class UserLoginForm(email: String, password: String)

object UserLoginForm {
  val userLoginForm = Form(
    mapping("email" -> text,
      "password" -> text
    )(UserLoginForm.apply)(UserLoginForm.unapply)
  )
}