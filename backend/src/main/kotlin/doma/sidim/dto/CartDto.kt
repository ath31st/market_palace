package doma.sidim.dto

import kotlinx.serialization.Serializable

@Serializable
data class CartDto(val cartId: Long?, val products: List<ProductInCartDto>)
