package doma.sidim.dto

import kotlinx.serialization.Serializable

@Serializable
data class CartChange(
    val cartId: Long,
    val productId: Long,
    val quantity: Int,
)