package doma.sidim.dto

import kotlinx.serialization.Serializable

@Serializable
data class NewOrderDto(
    val orderCost: Long,
    val deliveryAddress: String,
    val productIds: List<Long>,
)
