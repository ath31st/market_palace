package doma.sidim.dto

import kotlinx.serialization.Serializable

@Serializable
data class OrderDto(
    val id: Long,
    val orderDate: String,
    val deliveryDate: String,
    val deliveryAddress: String,
    val orderCost: Long,
    val orderStatus: String,
    val products: List<ProductInOrderDto>
)