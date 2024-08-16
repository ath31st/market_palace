import React from 'react'
import styled from 'styled-components'
import Product from './Product'

const ProductListContainer = styled.div`
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
    gap: 20px;
    padding: 20px;
`

const ProductList = ({ products }) => {
  return (
    <ProductListContainer>
      {products.map(product => (
        <Product
          key={product.id}
          image={product.image}
          title={product.title}
          price={product.price}
        />
      ))}
    </ProductListContainer>
  )
}

export default ProductList
