package services

import db.postgresql.dao.OrderDao
import javax.inject.Inject
import model.{Order, OrderItem, User}

import scala.concurrent.Future

class OrderService @Inject()(orderDao: OrderDao) {

  def createOrder(user: User, orderItems: List[OrderItem]): Future[Order] = {


    ???
  }
}
