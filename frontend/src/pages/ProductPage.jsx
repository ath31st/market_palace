import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import { useDispatch, useSelector } from 'react-redux'
import axios from '../config/axiosConfig'
import styled from 'styled-components'
import AddToCartButton from '../components/button/AddToCartButton'
import QuantityControl from '../components/button/QuantityControl'
import { fetchCart, updateCart, addToCart } from '../redux/cartSlice'

const ProductContainer = styled.div`
    display: flex;
    flex-direction: column;
    padding: 20px;
`

const ProductTitle = styled.h1`
    font-size: 54px;
    margin-bottom: 20px;
    color: #fff;
`

const ProductDetails = styled.div`
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    width: 100%;
    margin-bottom: 20px;
`

const ProductImage = styled.img`
    width: 500px;
    height: 300px;
    object-fit: cover;
    border: 1px solid #ddd;
    border-radius: 5px;
    margin-right: 20px;
`

const DescriptionBox = styled.div`
    padding: 20px;
    background-color: #e0f7da;
    border: 1px solid #c8e6c9;
    border-radius: 5px;
    width: 100%;
    font-size: 34px;
    color: green;
`

const InfoContainer = styled.div`
    display: flex;
    justify-content: space-between;
    margin-top: 20px;
`

const VendorInfo = styled.div`
    align-content: center;
    font-size: 40px;
    color: #fff;
`

const PriceAndButton = styled.div`
    display: flex;
    flex-direction: column;
    align-items: flex-end;
    width: 200px;
`

const ProductPrice = styled.p`
    font-size: 66px;
    color: #fff;
    margin-bottom: 10px;
`

const ProductPage = () => {
  const { id } = useParams()
  const [product, setProduct] = useState(null)
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState(null)
  const dispatch = useDispatch()
  const { items: cartItems, status: cartStatus } = useSelector(
    state => state.cart)

  useEffect(() => {
    const fetchProduct = async () => {
      try {
        const response = await axios.get(`/api/v1/products/${id}`)
        setProduct(response.data)
      } catch (error) {
        setError(error)
      } finally {
        setLoading(false)
      }
    }

    fetchProduct()
    dispatch(fetchCart())
  }, [id, dispatch])

  const handleAddToCart = () => {
    dispatch(addToCart({ productId: product.id, quantity: 1 }))
  }

  const handleIncrement = () => {
    const existingItem = cartItems.find(item => item.productId === product.id)
    if (existingItem) {
      dispatch(updateCart(
        { productId: product.id, quantity: existingItem.quantity + 1 }))
    }
  }

  const handleDecrement = () => {
    const existingItem = cartItems.find(item => item.productId === product.id)
    if (existingItem && existingItem.quantity > 1) {
      dispatch(updateCart(
        { productId: product.id, quantity: existingItem.quantity - 1 }))
    }
  }

  if (loading) return <p>Loading...</p>
  if (error) return <p>Error: {error.message}</p>

  const isProductInCart = Array.isArray(cartItems) && cartItems.length > 0 &&
    cartItems.some(item => item.productId === product.id)
  const currentQuantity = isProductInCart ? cartItems.find(
    item => item.productId === product.id).quantity : 0

  return (
    <ProductContainer>
      <ProductTitle>{product.title}</ProductTitle>
      <ProductDetails>
        <ProductImage src={product.imageLink} alt={product.title}/>
        <DescriptionBox>
          <p>{product.description}</p>
        </DescriptionBox>
      </ProductDetails>
      <InfoContainer>
        <VendorInfo>Vendor: {product.vendorInfo}</VendorInfo>
        <PriceAndButton>
          <ProductPrice>${product.price}/lb</ProductPrice>
          {isProductInCart ? (
            <QuantityControl
              quantity={currentQuantity}
              onIncrement={handleIncrement}
              onDecrement={handleDecrement}
            />
          ) : (
            <AddToCartButton onClick={handleAddToCart}>Add to
              cart</AddToCartButton>
          )}
        </PriceAndButton>
      </InfoContainer>
    </ProductContainer>
  )
}

export default ProductPage
