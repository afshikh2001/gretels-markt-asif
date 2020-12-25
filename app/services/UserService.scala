package services

import db.mysql.repositories.UserRepository
import javax.inject.Inject

class UserService @Inject()(userRepository: UserRepository){

  def userSignin(email: String, password: String): Unit = {
    System.out.println(s"user with email: ${email} signed in successfully")
  }





}
