package services

import db.mysql.repositories.ProductRepository

import javax.inject.Inject
import scala.concurrent.ExecutionContext

class ProductService @Inject()(productRepository: ProductRepository)(implicit ec: ExecutionContext){

}
