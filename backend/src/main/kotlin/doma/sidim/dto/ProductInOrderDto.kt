package doma.sidim.dto

import kotlinx.serialization.Serializable

@Serializable
data class ProductInOrderDto(
    val id: Long,
    val quantity: Int,
    val imageLink: String,
    val price: Long,
)