import React from 'react'
import styled from 'styled-components'

const FooterContainer = styled.footer`
    background-color: #333;
    color: white;
    padding: 10px 20px;
    text-align: center;
    bottom: 0;
    width: 100%;
`

const Footer = () => {
  return (
    <FooterContainer>
      &copy; Market palace project 2024
    </FooterContainer>
  )
}

export default Footer
