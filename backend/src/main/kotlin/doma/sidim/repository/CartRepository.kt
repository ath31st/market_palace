package doma.sidim.repository

import doma.sidim.dto.CartReq
import doma.sidim.model.Cart
import doma.sidim.model.CartProducts
import doma.sidim.model.Carts
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

class CartRepository(private val productRepository: ProductRepository) {
    fun create(item: CartReq, userId: Long): Long {
        var id: Long = 0
        transaction {
            id = Carts.insert {
                it[Carts.userId] = userId
            }[Carts.id]

            CartProducts.batchInsert(item.productIdsQuantities.entries) { (productId, quantity) ->
                this[CartProducts.cartId] = id
                this[CartProducts.productId] = productId
                this[CartProducts.quantity] = quantity
            }
        }
        return id
    }

    fun read(id: Long): Cart? {
        return transaction {
            val cartRow = Carts.select { Carts.id eq id }
                .mapNotNull {
                    it.toCart()
                }.singleOrNull()

            cartRow?.let { cart ->
                val products = CartProducts.select { CartProducts.cartId eq id }
                    .associate { it[CartProducts.productId] to it[CartProducts.quantity] }
                    .mapKeys { (productId, _) -> productRepository.read(productId)!! }

                cart.copy(products = products)
            }
        }
    }

    fun delete(id: Long): Boolean {
        return transaction {
            Carts.deleteWhere { Carts.id eq id } > 0
        }
    }

    private fun ResultRow.toCart(): Cart {
        return Cart(
            id = this[Carts.id],
            userId = this[Carts.userId],
            products = emptyMap(),
        )
    }
}
