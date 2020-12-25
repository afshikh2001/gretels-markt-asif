package services

import db.mysql.repositories.OrderRepository
import javax.inject.Inject
import model.{Order, OrderItem, User}

import scala.concurrent.Future

class OrderService @Inject()(orderRepository: OrderRepository) {

  def createOrder(user: User, orderItems: List[OrderItem]): Future[Order] = {
    val customerId = user.id
    val maybeOrder = user.id.map(id => Order(id, orderItems))

    ???
  }
}
