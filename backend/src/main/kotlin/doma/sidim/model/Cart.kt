package doma.sidim.model

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class Cart(
    val id: Long? = null,
    val userId: Long,
    val products: List<Product> = emptyList(),
)

object Carts : Table("carts") {
    val id = long("id").autoIncrement()
    val userId = long("user_id").references(Users.id).uniqueIndex()

    override val primaryKey = PrimaryKey(id)
}

object CartProducts : Table("cart_products") {
    val cartId = long("cart_id").references(Carts.id)
    val productId = long("product_id").references(Products.id)
    val quantity = integer("quantity")
}