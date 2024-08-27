import { useDispatch, useSelector } from 'react-redux'
import { fetchCart, updateCart } from '../redux/cartSlice'

export const useCart = () => {
  const dispatch = useDispatch()
  const cartItems = useSelector(state => state.cart.items)
  const cartId = useSelector(state => state.cart.id)

  const handleQuantityChange = async (id, changeQuantity) => {
    const updatedItem = cartItems.find(item => item.id === id)

    if (updatedItem) {
      const productUpdate = {
        cartId: cartId,
        productId: updatedItem.id,
        changeQuantity: changeQuantity,
      }
      console.log(productUpdate)
      await dispatch(updateCart(productUpdate))
      dispatch(fetchCart())
    }
  }

  return { cartItems, handleQuantityChange }
}
