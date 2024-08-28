import { useDispatch, useSelector } from 'react-redux'
import { addToCart, fetchCart, updateCart } from '../redux/cartSlice'
import { useEffect } from 'react'
import useAuth from './useAuth'

export const useCart = () => {
  const dispatch = useDispatch()
  const cartItems = useSelector(state => state.cart.items)
  const cartId = useSelector(state => state.cart.id)
  const isAuthenticated = useAuth().isAuthenticated

  useEffect(() => {
    if (isAuthenticated) {
      dispatch(fetchCart())
    }
  }, [dispatch, isAuthenticated])

  const handleQuantityChange = async (id, changeQuantity) => {
    if (!isAuthenticated) return

    const updatedItem = cartItems.find(item => item.id === id)

    if (updatedItem) {
      const productUpdate = {
        cartId: cartId,
        productId: updatedItem.id,
        changeQuantity: changeQuantity,
      }
      try {
        await dispatch(updateCart(productUpdate))
        await dispatch(fetchCart())
      } catch (error) {
        console.error('Failed to update cart:', error)
      }
    }
  }

  const handleAddToCart = async (productId) => {
    if (!isAuthenticated) return

    try {
      await dispatch(addToCart({ productId: productId, quantity: 1 }))
      await dispatch(fetchCart())
    } catch (error) {
      console.error('Error adding to cart:', error)
    }
  }

  return { cartItems, handleQuantityChange, handleAddToCart }
}
