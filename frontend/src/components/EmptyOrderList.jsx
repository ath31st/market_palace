import React from 'react'
import styled from 'styled-components'
import { BsCartX } from 'react-icons/bs'

const EmptyStateContainer = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 300px;
`

const EmptyIcon = styled(BsCartX)`
    font-size: 100px;
    margin-bottom: 20px;
    color: #32CD32;
`

const EmptyText = styled.p`
    font-size: 60px;
    color: #32CD32;
    text-align: center;
`

const EmptyOrderList = () => {
  return (
    <EmptyStateContainer>
      <EmptyIcon/>
      <EmptyText>No orders found</EmptyText>
    </EmptyStateContainer>
  )
}

export default EmptyOrderList
