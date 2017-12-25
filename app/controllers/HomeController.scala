package controllers

import javax.inject._

import dao.ProductDao
import db.client.JdbcClientFactory
import db.config.{JdbcConfig, JdbcConfigProvider}
import play.api._
import play.api.mvc._

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  /**
    * Create an Action to render an HTML page.
    *
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def index() = Action { implicit request: Request[AnyContent] =>
    val config=JdbcConfigProvider.config
    println("CONFIG")
    println(config.toString)
    val client = JdbcClientFactory.createClient(config)
    val productDao = new ProductDao(client)
    productDao.getProduct(1)
    Ok(views.html.index())
  }
}
