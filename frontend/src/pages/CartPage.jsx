import React, { useState } from 'react'
import styled from 'styled-components'
import QuantityControl from '../components/button/QuantityControl'

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
  const [cartItems, setCartItems] = useState([
    {
      id: 1,
      image: 'path/to/image1.jpg',
      title: 'Product title 1',
      description: 'Small description',
      price: 20,
      quantity: 1,
    },
    {
      id: 2,
      image: 'path/to/image2.jpg',
      title: 'Product title 2',
      description: 'Small description',
      price: 30,
      quantity: 1,
    },
    {
      id: 3,
      image: 'path/to/image3.jpg',
      title: 'Product title 3',
      description: 'Small description',
      price: 40,
      quantity: 1,
    },
  ])

  const incrementQuantity = (id) => {
    setCartItems(prevItems =>
      prevItems.map(item =>
        item.id === id
          ? { ...item, quantity: item.quantity + 1 }
          : item,
      ),
    )
  }

  const decrementQuantity = (id) => {
    setCartItems(prevItems =>
      prevItems.map(item =>
        item.id === id
          ? { ...item, quantity: Math.max(item.quantity - 1, 0) }
          : item,
      ),
    )
  }

  return (
    <CartContainer>
      <CartItems>
        {cartItems.map(item => (
          <CartItem key={item.id}>
            <ProductInfo>
              <ProductImage src={item.image} alt={item.title}/>
              <ProductDetails>
                <ProductTitle>{item.title}</ProductTitle>
                <ProductDescription>{item.description}</ProductDescription>
              </ProductDetails>
            </ProductInfo>
            <ProductPrice>${item.price}</ProductPrice>
            <QuantityControl
              quantity={item.quantity}
              onIncrement={() => incrementQuantity(item.id)}
              onDecrement={() => decrementQuantity(item.id)}
            />
          </CartItem>
        ))}
      </CartItems>
      <CartSummary>
        <SummaryItem>
          <span>Product count:</span>
          <span>{cartItems.reduce((total, item) => total + item.quantity,
            0)}</span>
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
