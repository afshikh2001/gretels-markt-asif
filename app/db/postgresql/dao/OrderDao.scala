package db.postgresql.dao

import javax.inject._
import db.postgresql.client.JdbcClient
import model.{Order, Price}
import scalikejdbc._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global


@Singleton
class OrderDao(client: JdbcClient) {

  import OrderDao._

  def addOrder(order: Order): Future[Unit] = {
    Future {
      client.db autoCommit { implicit session =>
        sql"""
             |INSERT INTO ${orderTable} (${orderFields})
             |VALUES(${order.id},${order.price},${order.customerId},
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
        sql"""DELETE FROM ${orderTable}
             | WHERE $idField = ${id};
           """.stripMargin
          .update()
          .apply()
      }
    }
  }

  /*

  DB localTx { implicit session =>
  // --- transcation scope start ---
  sql"update emp set name = ${name1} where id = ${id1}".update.apply()
  sql"update emp set name = ${name2} where id = ${id2}".update.apply()
  // --- transaction scope end ---
}

   */
  def getOrder(id: Long): Future[Option[Order]] = {
    Future {
      client.db readOnly { implicit session =>
        sql"""SELECT * FROM ${orderTable}
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
        sql"""SELECT * FROM ${orderTable}
             | Limit $limit OFFSET $offset;""".stripMargin
          .map(orderMapper)
          .collection
          .apply()
      }
    }
  }

}

object OrderDao {

  private val orderTable = sqls"order"

  private val idField = sqls"id"
  private val priceField = sqls"price"
  private val priceUnitField = sqls"price_unit"
  private val customerIdField = sqls"customer_id"
  private val createdAtField = sqls"created_at"
  private val updatedAtField = sqls"updated_at"

  private val orderFields = sqls.csv(idField,
    priceField,
    priceUnitField,
    customerIdField,
    createdAtField,
    updatedAtField)

  private val orderMapper = (rs: WrappedResultSet) => Order(
    id = rs.longOpt(idField),
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