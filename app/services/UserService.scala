package services

class UserService {

  def userSignin(email: String, password: String): Unit = {
    System.out.println(s"user with email: ${email} signed in successfully")
  }

}
