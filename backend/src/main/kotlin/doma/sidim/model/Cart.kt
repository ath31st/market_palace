package doma.sidim.model

import doma.sidim.dto.CartDto
import doma.sidim.dto.ProductInCartDto
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class Cart(
    val id: Long? = null,
    val userId: Long,
    val products: Map<Product, Int> = emptyMap(),
)

fun Cart.toCartDto(): CartDto {
    return CartDto(
        this.id,
        products = this.products.entries.map { entry ->
            ProductInCartDto(
                entry.key.id!!,
                entry.key.title,
                entry.value,
                entry.key.description,
                entry.key.imageLink,
                entry.key.price
            )
        }
    )
}

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