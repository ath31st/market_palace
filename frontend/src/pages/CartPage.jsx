import React, { useState, useEffect } from 'react'
import styled from 'styled-components'
import SubmitButton from '../components/button/SubmitButton'
import CartItem from '../components/CartItem'
import { useCart } from '../hooks/useCart'
import { useDispatch, useSelector } from 'react-redux'
import { createOrder } from '../redux/orderSlice'
import { clearCart } from '../redux/cartSlice'
import InputField from '../components/input/InputField'

const CartPageContainer = styled.div`
    display: flex;
    align-items: center;
    justify-content: center;
`

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

const EmptyCartContainer = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    padding-top: 70px;
`

const EmptyCartImage = styled.div`
    background-image: url('/your_cart_is_empty.png');
    width: 495px;
    height: 390px;
    margin-bottom: 20px;
    border-radius: 120px;
    background-color: rgba(70, 255, 70, 0.71);
`

const CartPage = () => {
  const { cartItems, handleQuantityChange } = useCart()
  const dispatch = useDispatch()
  const orderSuccess = useSelector(state => state.order.success)
  const [deliveryAddress, setDeliveryAddress] = useState('')
  const [orderMessage, setOrderMessage] = useState('')

  useEffect(() => {
    if (orderSuccess) {
      setOrderMessage('Order created successfully!')
      dispatch(clearCart())
    }
  }, [orderSuccess, dispatch])

  const handleCreateOrder = () => {
    const orderCost = cartItems.reduce(
      (total, item) => total + item.price * item.quantity, 0)
    const productIdsQuantities = cartItems.reduce((map, item) => {
      map[item.id] = item.quantity
      return map
    }, {})

    dispatch(createOrder({ orderCost, deliveryAddress, productIdsQuantities }))
  }

  return (
    <CartPageContainer>
      {cartItems && cartItems.length > 0 ? (
        <CartContainer>
          <CartItems>
            {cartItems.map(item => (
              <CartItem
                key={item.id}
                item={item}
                handleQuantityChange={handleQuantityChange}
              />
            ))}
          </CartItems>
          <CartSummary>
            <SummaryItem>
              <span>Product count:</span>
              <span>{cartItems.reduce(
                (total, item) => total + item.quantity, 0)}</span>
            </SummaryItem>
            <SummaryItem>
              <span>Total price:</span>
              <span>${cartItems.reduce(
                (total, item) => total + item.price * item.quantity, 0)}</span>
            </SummaryItem>
            <InputField
              type="text"
              name="deliveryAddress"
              placeholder="Delivery address"
              value={deliveryAddress}
              onChange={(e) =>
                setDeliveryAddress(e.target.value)}
            />
            <SubmitButton onClick={handleCreateOrder}>
              Create order
            </SubmitButton>
            {orderMessage && <p>{orderMessage}</p>}
          </CartSummary>
        </CartContainer>
      ) : (
        <EmptyCartContainer>
          <EmptyCartImage/>
        </EmptyCartContainer>
      )}
    </CartPageContainer>
  )
}

export default CartPage
