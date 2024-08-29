package doma.sidim.model

import doma.sidim.dto.OrderDto
import doma.sidim.util.OrderStatus
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

@Serializable
data class Order(
    val id: Long? = null,
    @Contextual val orderDate: LocalDateTime,
    val orderStatus: OrderStatus,
    val orderCost: Long,
    @Contextual val deliveryDate: LocalDateTime,
    val deliveryAddress: String,
    val userId: Long,
    val products: Map<Product, Int>
)

fun Order.toOrderDto() = OrderDto(
    this.id!!,
    this.orderDate.toString(),
    this.deliveryDate.toString(),
    this.deliveryAddress,
    this.orderCost,
    this.orderStatus.displayName(),
    this.products.entries.map { it.key.toProductInOrderDto(it.value) }
)


object Orders : Table("orders") {
    val id = long("id").autoIncrement()
    val orderDate = datetime("order_date")
    val orderStatus = integer("order_status")
    val orderCost = long("order_cost")
    val deliveryDate = datetime("delivery_date")
    val deliveryAddress = varchar("delivery_address", 500)
    val userId = long("user_id").references(Users.id)

    override val primaryKey = PrimaryKey(id)
}

object OrderProducts : Table("order_products") {
    val orderId = long("order_id").references(Orders.id)
    val productId = long("product_id").references(Products.id)
    val quantity = integer("quantity")
}
