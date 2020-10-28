package controllers

import javax.inject._

import db.postgresql.dao.ProductDao
import db.postgresql.client.JdbcClientFactory
import db.postgresql.config.{JdbcConfig, JdbcConfigProvider}
import play.api._
import play.api.mvc._

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class ProductController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  /**
    * Create an Action to render an HTML page.
    *
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def home() = Action { implicit request: Request[AnyContent] =>
    val timeout = 10 seconds
    val config = JdbcConfigProvider.config
    println("CONFIG")
    println(config.toString)
    val client = JdbcClientFactory.createClient(config)
    val productDao = new ProductDao(client)
    val fProduct = productDao.getProduct(1)
    // fProduct.map(product=>Ok(views.html.home(product.get)))

    val product = Await.result(fProduct, timeout)
    Ok(product.get.toString)
  }
}
