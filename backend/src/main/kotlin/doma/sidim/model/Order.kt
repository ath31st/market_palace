package doma.sidim.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDate

@Serializable
data class Order(
    val id: Long? = null,
    @Contextual val orderDate: LocalDate,
    val orderStatus: String,
    val orderCost: Long,
    @Contextual val deliveryDate: LocalDate,
    val deliveryAddress: String,
    val userId: Long,
    val products: List<Product>
)

object Orders : Table("orders") {
    val id = long("id").autoIncrement()
    val orderDate = datetime("order_date")
    val orderStatus = varchar("order_status", 50)
    val orderCost = long("order_cost")
    val deliveryDate = datetime("delivery_date")
    val deliveryAddress = varchar("delivery_address", 500)
    val userId = long("user_id").references(Users.id)

    override val primaryKey = PrimaryKey(id)
}

object OrderProducts : Table("order_products") {
    val orderId = long("order_id").references(Orders.id)
    val productId = long("product_id").references(Products.id)
}
