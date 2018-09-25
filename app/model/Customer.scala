package model

case class Customer(id: Long,
                    vorName: String,
                    nachName: String,
                    email: String,
                    plz: Int,
                    strasse: String,
                    ort: String,
                    country: String,
                    additionalAddress: String,
                    updatedAt: Long
               )

object Customer {

}
