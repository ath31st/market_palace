import React from 'react'
import styled from 'styled-components'
import QuantityControl from '../components/button/QuantityControl'

const CartItemContainer = styled.div`
    display: flex;
    justify-content: space-between;
    padding: 10px;
    border: 1px solid #ddd;
    margin-bottom: 10px;
    background-color: #f9f9f9;
    border-radius: 5px;
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
    border-radius: 5px;
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

const CartItem = React.memo(({ item, handleQuantityChange }) => {
  return (
    <CartItemContainer>
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
        onIncrement={() => handleQuantityChange(item.id, 1)}
        onDecrement={() => handleQuantityChange(item.id, -1)}
      />
    </CartItemContainer>
  )
})

export default CartItem
