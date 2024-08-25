package doma.sidim.service

import doma.sidim.dto.NewCartDto
import doma.sidim.model.Cart
import doma.sidim.repository.CartRepository

class CartService(private val cartRepository: CartRepository) {

    fun createCart(dto: NewCartDto, userId: Long): Long {
        return cartRepository.create(dto, userId)
    }

    fun getCart(id: Long): Cart? {
        return cartRepository.read(id)
    }

    fun getCartByUserId(userId: Long): Cart? {
        return cartRepository.readByUserId(userId)
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

    fun updateProductInCart(cartId: Long, productId: Long, quantity: Int): Boolean {
        val cart = cartRepository.read(cartId) ?: return false
        val cartProducts = cart.products.entries
            .associate { it.key.id!! to it.value }
            .toMutableMap()

        val currentQuantity = cartProducts[productId] ?: 0

        val newQuantity = currentQuantity + quantity

        when {
            newQuantity > 0 -> {
                cartProducts[productId] = newQuantity
            }

            newQuantity <= 0 -> {
                cartProducts.remove(productId)
            }
        }

        return cartRepository.updateCart(cartId, cartProducts)
    }


    fun existsCartByUserId(userId: Long): Boolean {
        return cartRepository.existsCartByUserId(userId)
    }

    fun deleteCartByUserId(userId: Long): Boolean {
        return cartRepository.deleteByUserId(userId)
    }
}
