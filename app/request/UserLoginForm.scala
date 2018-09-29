package request

import play.api.data._
import play.api.data.Forms._

case class UserLoginForm(email: String, password: String)

object UserLoginForm {
  val userLoginForm = Form(
    mapping("email" -> text,
      "password" -> text
    )(UserLoginForm.apply)(UserLoginForm.unapply)
  )
}