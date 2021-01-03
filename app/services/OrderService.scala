package services

import db.mysql.repositories.OrderRepository

import javax.inject.Inject
import model.{Order, OrderItem, User}
import request.OrderRequest

import scala.concurrent.{ExecutionContext, Future}

class OrderService @Inject()(orderRepository: OrderRepository)(implicit ec: ExecutionContext) {

  def createOrder(orderRequest: OrderRequest): Future[Order] = {
    val customerId = orderRequest.customerId
    val maybeOrder = orderRequest.orderItems
    Future.successful(Order(customerId, List.empty))
  }
}
