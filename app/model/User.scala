package model

case class User(id: Long,
                vorName: String,
                nachName: String,
                email: String,
                password: String,
                plz: Int,
                straße: String,
                ort: String,
                country: String,
                additionalAddress: String,
                updatedAt: Long
                   )

object User {

}
