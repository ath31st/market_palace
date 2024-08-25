package doma.sidim.dto

import kotlinx.serialization.Serializable

@Serializable
data class NewCartDto(
    val productId: Long,
    val quantity: Int
)