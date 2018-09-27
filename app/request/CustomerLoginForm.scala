package request

import play.api.data._
import play.api.data.Forms._

case class CustomerLoginForm(email: String, password: String)

object CustomerLoginForm {
  val customerLoginForm = Form(
    mapping("email" -> text,
      "password" -> text
    )(CustomerLoginForm.apply)(CustomerLoginForm.unapply)
  )
}