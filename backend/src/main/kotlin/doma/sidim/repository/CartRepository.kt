package doma.sidim.repository

import doma.sidim.dto.NewCartDto
import doma.sidim.model.Cart
import doma.sidim.model.CartProducts
import doma.sidim.model.Carts
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

class CartRepository(private val productRepository: ProductRepository) {
    fun create(item: NewCartDto, userId: Long): Long {
        var id: Long = 0
        transaction {
            id = Carts.insert {
                it[Carts.userId] = userId
            }[Carts.id]

            CartProducts.insert {
                it[cartId] = id
                it[productId] = item.productId
                it[quantity] = item.quantity
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

    fun updateCart(cartId: Long, updatedProducts: Map<Long, Int>): Boolean {
        return transaction {
            val deleteCount = CartProducts.deleteWhere { CartProducts.cartId eq cartId }

            val insertCount =
                CartProducts.batchInsert(updatedProducts.entries) { (productId, quantity) ->
                    this[CartProducts.cartId] = cartId
                    this[CartProducts.productId] = productId
                    this[CartProducts.quantity] = quantity
                }.count()

            deleteCount > 0 || insertCount > 0
        }
    }

    fun deleteByUserId(userId: Long): Boolean {
        return transaction {
            Carts.deleteWhere { Carts.userId eq userId } > 0
        }
    }

    fun existsCartByUserId(userId: Long): Boolean {
        return transaction { Carts.select { Carts.userId eq userId }.count() > 0 }
    }

    private fun ResultRow.toCart(): Cart {
        return Cart(
            id = this[Carts.id],
            userId = this[Carts.userId],
            products = emptyMap(),
        )
    }
}
