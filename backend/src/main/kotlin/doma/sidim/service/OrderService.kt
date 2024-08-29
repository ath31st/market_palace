package doma.sidim.service

import doma.sidim.dto.NewOrderDto
import doma.sidim.model.Order
import doma.sidim.repository.OrderRepository
import doma.sidim.util.OrderStatus
import org.jetbrains.exposed.sql.SortOrder
import java.time.LocalDateTime

class OrderService(private val orderRepository: OrderRepository) {

    fun createOrder(newOrder: NewOrderDto, userId: Long): Long {
        val days = (2..5).random().toLong()
        val deliveryDate = LocalDateTime.now().plusDays(days)
        return orderRepository.create(newOrder, userId, deliveryDate)
    }

    fun getOrder(id: Long): Order? {
        return orderRepository.read(id)
    }

    fun getOrdersByUserId(
        userId: Long,
        sortOrder: SortOrder,
        search: String?,
        sortBy: String?
    ): List<Order> {
        val searchableId: Long? = try {
            search?.toLong()
        } catch (e: Exception) {
            null
        }
        return orderRepository.findOrdersByUserId(userId, sortOrder, searchableId, sortBy)
    }

    fun deleteOrder(id: Long): Boolean {
        return orderRepository.delete(id)
    }

    fun updateOrderStatus(id: Long, status: OrderStatus): Boolean {
        return orderRepository.updateOrderStatus(id, status)
    }
}
