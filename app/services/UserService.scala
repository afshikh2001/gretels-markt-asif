package services

import db.mysql.repositories.UserRepository

import javax.inject.Inject
import scala.concurrent.ExecutionContext

class UserService @Inject()(userRepository: UserRepository)(implicit ec: ExecutionContext) {

  def userSignin(email: String, password: String): Unit = {
    System.out.println(s"user with email: ${email} signed in successfully")
  }


}
