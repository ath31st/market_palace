package doma.sidim.dto

import kotlinx.serialization.Serializable

@Serializable
data class CartReq(
    val productIdsQuantities: Map<Long, Int>,
)