package doma.sidim.dto

data class CartChange(
    val cartId: Long,
    val productId: Long,
    val quantity: Int,
)