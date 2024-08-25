package doma.sidim.dto

import kotlinx.serialization.Serializable

@Serializable
data class ProductInCartDto(
    val id: Long,
    val title: String,
    val quantity: Int,
    val smallDescription: String,
    val imageLink: String,
    val price: Long,
)
