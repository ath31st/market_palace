package doma.sidim.repository

import doma.sidim.dto.NewOrderDto
import doma.sidim.model.Order
import doma.sidim.model.OrderProducts
import doma.sidim.model.Orders
import doma.sidim.util.OrderStatus
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime

class OrderRepository(private val productRepository: ProductRepository) {
    fun create(item: NewOrderDto, userId: Long, deliveryDate: LocalDateTime): Long {
        var id: Long = 0
        transaction {
            id = Orders.insert {
                it[orderDate] = LocalDateTime.now()
                it[orderStatus] = OrderStatus.PENDING.ordinal
                it[orderCost] = item.orderCost
                it[Orders.deliveryDate] = deliveryDate
                it[deliveryAddress] = item.deliveryAddress
                it[Orders.userId] = userId
            }[Orders.id]

            OrderProducts.batchInsert(item.productIdsQuantities.entries) { (productId, quantity) ->
                this[OrderProducts.orderId] = id
                this[OrderProducts.productId] = productId
                this[OrderProducts.quantity] = quantity
            }
        }
        return id
    }

    fun read(id: Long): Order? {
        return transaction {
            val orderRow = Orders.select { Orders.id eq id }
                .mapNotNull {
                    it.toOrder()
                }.singleOrNull()

            orderRow?.let { order ->
                val products = OrderProducts.select { OrderProducts.orderId eq id }
                    .associate { it[OrderProducts.productId] to it[OrderProducts.quantity] }
                    .mapKeys { (productId, _) -> productRepository.read(productId)!! }

                order.copy(products = products)
            }
        }
    }

    fun findOrdersByUserId(userId: Long): List<Order> {
        return transaction {
            val orderRows = Orders.select { Orders.userId eq userId }
                .mapNotNull {
                    it.toOrder()
                }

            orderRows.map { order ->
                val products = OrderProducts.select { OrderProducts.orderId eq order.id!! }
                    .associate { it[OrderProducts.productId] to it[OrderProducts.quantity] }
                    .mapKeys { (productId, _) -> productRepository.read(productId)!! }

                order.copy(products = products)
            }
        }
    }

    fun delete(id: Long): Boolean {
        return transaction {
            Orders.deleteWhere { Orders.id eq id } > 0
        }
    }

    fun updateOrderStatus(id: Long, status: OrderStatus): Boolean {
        return transaction {
            Orders.update({ Orders.id eq id }) {
                it[orderStatus] = status.ordinal
            } > 0
        }
    }

    private fun ResultRow.toOrder(): Order {
        return Order(
            id = this[Orders.id],
            orderDate = this[Orders.orderDate],
            orderStatus = OrderStatus.getOrderStatus(this[Orders.orderStatus]),
            orderCost = this[Orders.orderCost],
            deliveryDate = this[Orders.deliveryDate],
            deliveryAddress = this[Orders.deliveryAddress],
            products = emptyMap(),
            userId = this[Orders.userId],
        )
    }
}
