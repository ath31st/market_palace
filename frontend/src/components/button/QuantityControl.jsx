import React from 'react'
import styled from 'styled-components'

const QuantityControlContainer = styled.div`
    display: flex;
    align-items: center;
`

const QuantityButton = styled.button`
    width: 30px;
    height: 30px;
    padding: 5px 10px;
    background-color: #32CD32;
    color: white;
    border: none;
    border-radius: 5px;
    font-size: 16px;
    cursor: pointer;
    margin: 0 5px;

    &:hover {
        background-color: #006400;
    }
`

const QuantityDisplay = styled.span`
    font-size: 16px;
    color: #000;
    margin: 0 10px;
`

const QuantityControl = ({ quantity, onIncrement, onDecrement }) => {
  return (
    <QuantityControlContainer>
      <QuantityButton onClick={onDecrement}>-</QuantityButton>
      <QuantityDisplay>{quantity}</QuantityDisplay>
      <QuantityButton onClick={onIncrement}>+</QuantityButton>
    </QuantityControlContainer>
  )
}

export default QuantityControl
