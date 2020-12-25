package request.forms

import play.api.data.Forms._
import play.api.data._

case class UserRegistrationForm(vorName: String,
                                nachName: String,
                                email: String,
                                password: String,
                                plz: Int,
                                straße: String,
                                ort: String,
                                additionalAddress: String,
                                phoneNumber: String)

object UserRegistrationForm {
  val userRegistrationForm = Form(
    mapping("vorName" -> text,
      "nachName" -> text,
      "email" -> text,
      "password" -> text,
      "plz" -> number,
      "straße" -> text,
      "ort" -> text,
      "additionalAddress" -> text,
      "phoneNumber" -> text
    )(UserRegistrationForm.apply)(UserRegistrationForm.unapply)
  )
}
