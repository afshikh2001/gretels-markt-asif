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

    ???
  }
}
