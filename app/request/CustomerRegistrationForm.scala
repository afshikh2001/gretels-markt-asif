package request

import play.api.data._
import play.api.data.Forms._

case class CustomerRegistrationForm(vorName: String,
                                    nachName: String,
                                    email: String,
                                    password: String,
                                    plz: Int,
                                    straße: String,
                                    ort: String,
                                    additionalAddress: String,
                                    phoneNumber: String)

object CustomerRegistrationForm {
  val customerRegistrationForm = Form(
    mapping("vorName" -> text,
      "nachName" -> text,
      "email" -> text,
      "password" -> text,
      "plz" -> number,
      "straße" -> text,
      "ort" -> text,
      "additionalAddress" -> text,
      "phoneNumber" -> text
    )(CustomerRegistrationForm.apply)(CustomerRegistrationForm.unapply)
  )
}
