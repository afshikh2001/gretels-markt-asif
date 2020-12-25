package services

import db.mysql.repositories.OrderItemRepository
import javax.inject.Inject
import model.{Order, OrderItem, User}

import scala.concurrent.Future

class OrderItemService @Inject()(orderItemRepository: OrderItemRepository){

  def createOrderItem(user: User, product:Product): Future[OrderItem] = {

    ???
  }





}
