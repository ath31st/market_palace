package doma.sidim.service

import doma.sidim.dto.CartReq
import doma.sidim.model.Cart
import doma.sidim.repository.CartRepository

class CartService(private val cartRepository: CartRepository) {

    fun createCart(cartReq: CartReq, userId: Long): Long {
        return cartRepository.create(cartReq, userId)
    }

    fun getCart(id: Long): Cart? {
        return cartRepository.read(id)
    }

    fun deleteCart(id: Long): Boolean {
        return cartRepository.delete(id)
    }

    fun addProductToCart(cartId: Long, productId: Long, quantity: Int): Boolean {
        val cart = cartRepository.read(cartId) ?: return false
        val cartProducts = cart.products.entries
            .associate { it.key.id!! to it.value }
            .toMutableMap()

        cartProducts[productId] = cartProducts[productId]?.plus(quantity) ?: quantity

        return cartRepository.updateCart(cartId, cartProducts)
    }

    fun removeProductFromCart(cartId: Long, productId: Long, quantity: Int): Boolean {
        val cart = cartRepository.read(cartId) ?: return false
        val cartProducts = cart.products.entries
            .associate { it.key.id!! to it.value }
            .toMutableMap()

        val currentQuantity = cartProducts[productId]

        if (currentQuantity != null) {
            if (currentQuantity > quantity) {
                cartProducts[productId] = currentQuantity - quantity
            } else {
                cartProducts.remove(productId)
            }
            return cartRepository.updateCart(cartId, cartProducts)
        }

        return false
    }

}
