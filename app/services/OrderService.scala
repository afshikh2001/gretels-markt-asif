package services

import db.mysql.repositories.OrderRepository

import javax.inject.Inject
import model.{Order, OrderItem, User}
import request.OrderRequest

import scala.concurrent.Future

class OrderService @Inject()(orderRepository: OrderRepository) {

  def createOrder(orderRequest: OrderRequest): Future[Order] = {
    val customerId = orderRequest.customerId
    val maybeOrder = orderRequest.orderItems

    ???
  }
}
