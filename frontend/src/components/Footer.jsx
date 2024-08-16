import React from 'react'
import styled from 'styled-components'

const FooterContainer = styled.footer`
    background-color: #f8f8f8;
    padding: 20px 20px;
    text-align: center;
    bottom: 0;
    width: 100%;
`

const CopyrightText = styled.div`
    font-weight: bold;
    color: #32CD32;
`

const Footer = () => {
  return (
    <FooterContainer>
      <CopyrightText>&copy; Market palace project 2024</CopyrightText>
    </FooterContainer>
  )
}

export default Footer
