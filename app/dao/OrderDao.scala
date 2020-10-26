package dao

import db.client.JdbcClient
import model.{Order, Price}
import scalikejdbc._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class OrderDao(client: JdbcClient) {

    import OrderDao._

    def addOrder(order: Order): Future[Unit] = {
      Future {
        client.db autoCommit { implicit session =>
          sql"""
               |INSERT INTO ${ordersTable} (${orderFields})
               |VALUES(${order.id},${order.items},${order.price},${order.customerId},
               |${order.createdAt},${order.updatedAt})
               |;""".stripMargin
            .update()
            .apply()
        }
      }
    }

    def deleteOrder(id: Long): Future[Unit] = {
      Future {
        client.db autoCommit { implicit session =>
          sql"""DELETE FROM ${ordersTable}
               | WHERE $idField = ${id};
           """.stripMargin
            .update()
            .apply()
        }
      }
    }

    def getOrder(id: Long): Future[Option[Order]] = {
      Future {
        client.db readOnly { implicit session =>
          sql"""SELECT * FROM ${ordersTable}
               | WHERE $idField = ${id}
               | Limit 1;""".stripMargin
            .map(orderMapper)
            .single()
            .apply()
        }
      }
    }

  def getOrders(limit: Int = 20, offset: Int): Future[List[Order]] = {
    Future {
      client.db readOnly { implicit session =>
        sql"""SELECT * FROM ${ordersTable}
             | Limit $limit OFFSET $offset;""".stripMargin
          .map(orderMapper)
          .collection
          .apply()
      }
    }
  }

  }

object OrderDao {

  private val ordersTable = sqls"orders"

  private val idField = sqls"id"
  private val itemsField = sqls"items"
  private val priceField = sqls"price"
  private val priceUnitField = sqls"priceUnit"
  private val customerIdField = sqls"customerId"
  private val createdAtField = sqls"created_at"
  private val updatedAtField = sqls"updated_at"

  private val orderFields = sqls.csv(idField,
    itemsField,
    priceField,
    priceUnitField,
    customerIdField,
    createdAtField,
    updatedAtField)

  private val orderMapper = (rs: WrappedResultSet) => Order(
    id = rs.long(idField),
    items = rs.array(itemsField),
    price = priceMapper(rs),
    customerId = rs.int(customerIdField),
    createdAt = rs.long(createdAtField),
    updatedAt = rs.long(updatedAtField)
  )


  private val priceMapper = (rs: WrappedResultSet) => Price(
    value = rs.double(priceField),
    unit = rs.string(priceUnitField),
  )









}