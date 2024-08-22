import React from 'react'
import styled from 'styled-components'
import Product from './Product'

const ProductListWrapper = styled.div`
    display: flex;
    justify-content: center;
    overflow: auto;
`

const ProductListContainer = styled.div`
    display: grid;
    grid-template-columns: repeat(5, 200px);
    grid-auto-rows: 250px;
    gap: 20px;
    height: calc(250px * 2 + 40px);
    box-sizing: content-box;
`

const ProductList = ({ products }) => {
  return (
    <ProductListWrapper>
      <ProductListContainer>
        {products.map(product => (
          <Product
            key={product.id}
            id={product.id}
            image={product.imageLink}
            title={product.title}
            price={product.price}
          />
        ))}
      </ProductListContainer>
    </ProductListWrapper>
  )
}

export default ProductList
