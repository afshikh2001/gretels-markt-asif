package request

import play.api.data._
import play.api.data.Forms._

case class CustomerData(vorName: String,
                        nachName: String,
                        email: String,
                        password: String,
                        plz: Int,
                        strasse: String,
                        ort: String,
                        additionalAddress: String,
                        phoneNumber: String)

object CustomerData {
  val customerDataForm = Form(
    mapping("vorName" -> text,
      "nachName" -> text,
      "email" -> text,
      "password" -> text,
      "plz" -> number,
      "strasse" -> text,
      "ort" -> text,
      "additionalAddress" -> text,
      "phoneNumber" -> text
    )(CustomerData.apply)(CustomerData.unapply)
  )
}
