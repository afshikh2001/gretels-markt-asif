package model

case class User(id: Option[Long],
                firstName: String,
                lastName: String,
                type_ : String,
                email: String,
                password: String,
                plz: Int,
                straße: String,
                ort: String,
                country: String,
                additionalAddress: String,
                createdAt: Long,
                updatedAt: Long
               )


