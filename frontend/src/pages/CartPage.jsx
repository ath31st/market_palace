import React, { useEffect } from 'react'
import styled from 'styled-components'
import { fetchCart, updateCart } from '../redux/cartSlice'
import { useDispatch, useSelector } from 'react-redux'
import SubmitButton from '../components/button/SubmitButton'
import CartItem from '../components/CartItem'

const CartContainer = styled.div`
    display: flex;
    padding: 20px;
`

const CartItems = styled.div`
    flex: 2;
`

const CartSummary = styled.div`
    flex: 1;
    padding: 20px;
    border: 1px solid #ddd;
    margin-left: 20px;
    background-color: #f9f9f9;
    border-radius: 5px;
    height: 100%;
`

const SummaryItem = styled.div`
    display: flex;
    justify-content: space-between;
    margin-bottom: 10px;
    font-size: 20px;
    color: green;
`

const CartPage = () => {
  const dispatch = useDispatch()
  const cartItems = useSelector(state => state.cart.items)
  const cartId = useSelector(state => state.cart.id)

  useEffect(() => {
    dispatch(fetchCart())
  }, [dispatch])

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
      .then(() => {dispatch(fetchCart())})
    }
  }

  return (
    <CartContainer>
      <CartItems>
        {cartItems && cartItems.length > 0 ? (
          cartItems.map(item => (
            <CartItem
              key={item.id}
              item={item}
              handleQuantityChange={handleQuantityChange}
            />
          ))
        ) : (
          <p>Your cart is empty</p>
        )}
      </CartItems>
      <CartSummary>
        <SummaryItem>
          <span>Product count:</span>
          <span>{cartItems ? cartItems.reduce(
            (total, item) => total + item.quantity, 0) : 0}</span>
        </SummaryItem>
        <SummaryItem>
          <span>Total price:</span>
          <span>${cartItems ? cartItems.reduce(
            (total, item) => total + item.price * item.quantity, 0) : 0}</span>
        </SummaryItem>
        <SubmitButton>Create order</SubmitButton>
      </CartSummary>
    </CartContainer>
  )
}

export default CartPage
