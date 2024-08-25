import React, { useEffect } from 'react'
import styled from 'styled-components'
import QuantityControl from '../components/button/QuantityControl'
import { fetchCart, updateCart } from '../redux/cartSlice'
import { useDispatch, useSelector } from 'react-redux'

const CartContainer = styled.div`
    display: flex;
    padding: 20px;
`

const CartItems = styled.div`
    flex: 2;
`

const CartItem = styled.div`
    display: flex;
    justify-content: space-between;
    padding: 10px;
    border: 1px solid #ddd;
    margin-bottom: 10px;
    background-color: #f9f9f9;
`

const ProductInfo = styled.div`
    display: flex;
`

const ProductImage = styled.img`
    width: 100px;
    height: 100px;
    object-fit: cover;
    border: 1px solid #ddd;
    margin-right: 20px;
`

const ProductDetails = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: center;
`

const ProductTitle = styled.p`
    font-size: 20px;
    font-weight: bold;
    margin: 0;
    color: green;
`

const ProductDescription = styled.p`
    font-size: 16px;
    margin: 0;
    color: green;
`

const ProductPrice = styled.div`
    display: flex;
    align-items: center;
    font-size: 20px;
    color: green;
`

const CartSummary = styled.div`
    flex: 1;
    padding: 20px;
    border: 1px solid #ddd;
    margin-left: 20px;
    background-color: #f9f9f9;
`

const SummaryItem = styled.div`
    display: flex;
    justify-content: space-between;
    margin-bottom: 10px;
    font-size: 20px;
    color: green;
`

const CreateOrderButton = styled.button`
    width: 100%;
    padding: 10px;
    background-color: #32CD32;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;

    &:hover {
        background-color: #28a745;
    }
`

const CartPage = () => {
  const dispatch = useDispatch()
  const cartItems = useSelector(state => state.cart.items)

  useEffect(() => {
    dispatch(fetchCart())
  }, [dispatch])

  const handleQuantityChange = (id, quantity) => {
    const updatedItem = cartItems.find(item => item.id === id)

    if (updatedItem) {
      const productUpdate = {
        cartId: 9,
        productId: updatedItem.id,
        quantity: quantity,
      }
      console.log(productUpdate)
      dispatch(updateCart(productUpdate))
    }
  }

  return (
    <CartContainer>
      <CartItems>
        {cartItems.map(item => (
          <CartItem key={item.id}>
            <ProductInfo>
              <ProductImage src={item.imageLink} alt={item.title}/>
              <ProductDetails>
                <ProductTitle>{item.title}</ProductTitle>
                <ProductDescription>{item.smallDescription}</ProductDescription>
              </ProductDetails>
            </ProductInfo>
            <ProductPrice>${item.price}</ProductPrice>
            <QuantityControl
              quantity={item.quantity}
              onIncrement={() => handleQuantityChange(item.id,
                item.quantity + 1)}
              onDecrement={() => handleQuantityChange(item.id,
                Math.max(item.quantity - 1, 1))}
            />
          </CartItem>
        ))}
      </CartItems>
      <CartSummary>
        <SummaryItem>
          <span>Product count:</span>
          <span>{cartItems.reduce((total, item) => total + item.quantity, 0)}</span>
        </SummaryItem>
        <SummaryItem>
          <span>Total price:</span>
          <span>${cartItems.reduce(
            (total, item) => total + item.price * item.quantity, 0)}</span>
        </SummaryItem>
        <CreateOrderButton>Create order</CreateOrderButton>
      </CartSummary>
    </CartContainer>
  )
}

export default CartPage
