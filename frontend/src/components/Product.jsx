import React from 'react'
import styled from 'styled-components'
import AddToCartButton from './button/AddToCartButton'
import { useNavigate } from 'react-router-dom'

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
    width: 180px;
    height: 130px;
    border-radius: 5px;
    cursor: pointer;
`

const ProductTitle = styled.h3`
    font-size: 18px;
    color: #333;
    margin: 10px 0;
    cursor: pointer;
`

const ProductPrice = styled.p`
    font-size: 16px;
    color: #888;
`

const Product = ({ id, image, title, price }) => {
  const navigate = useNavigate()

  const handleProductClick = () => {
    navigate(`/products/${id}`)
  }

  return (
    <ProductContainer>
      <ProductImage onClick={handleProductClick} src={image} alt={title}/>
      <ProductTitle onClick={handleProductClick}>{title}</ProductTitle>
      <ProductPrice>${price}/lb</ProductPrice>
      <AddToCartButton>Add to cart</AddToCartButton>
    </ProductContainer>
  )
}

export default Product
