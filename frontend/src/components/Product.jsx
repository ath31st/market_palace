import React from 'react'
import styled from 'styled-components'

const ProductContainer = styled.div`
    border: 1px solid #ddd;
    border-radius: 5px;
    padding: 10px;
    display: flex;
    flex-direction: column;
    align-items: center;
    background-color: #fff;
`

const ProductImage = styled.img`
    width: 100%;
    height: auto;
    border-radius: 5px;
`

const ProductTitle = styled.h3`
    font-size: 18px;
    color: #333;
    margin: 10px 0;
`

const ProductPrice = styled.p`
    font-size: 16px;
    color: #888;
`

const AddToCartButton = styled.button`
    width: 100%;
    padding: 10px;
    background-color: #32CD32;
    color: white;
    border: none;
    border-radius: 5px;
    font-size: 18px;
    cursor: pointer;
    margin-top: 10px;

    &:hover {
        background-color: #006400;
    }
`

const Product = ({ image, title, price }) => {
  return (
    <ProductContainer>
      <ProductImage src={image} alt={title}/>
      <ProductTitle>{title}</ProductTitle>
      <ProductPrice>{price}</ProductPrice>
      <AddToCartButton>Add to cart</AddToCartButton>
    </ProductContainer>
  )
}

export default Product
