package services

import db.mysql.repositories.ProductRepository
import javax.inject.Inject

class ProductService @Inject()(productRepository: ProductRepository){

}
