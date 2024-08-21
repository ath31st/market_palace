import React from 'react'
import styled from 'styled-components'
import { FaShoppingCart, FaDoorOpen, FaListAlt, } from 'react-icons/fa'
import useAuth from '../hooks/useAuth'

const HeaderContainer = styled.header`
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 20px 20px;
    background-color: #f8f8f8;
    border-bottom: 1px solid #ddd;
`

const Logo = styled.div`
    font-size: 34px;
    font-weight: bold;
    color: #32CD32;
`

const Icons = styled.div`
    display: flex;
    align-items: center;

    & > * {
        margin-left: 20px;
        cursor: pointer;
        font-size: 25px;
        color: #555;
    }
`

const Header = () => {
  const isAuthenticated = useAuth().isAuthenticated

  return (
    <HeaderContainer>
      <Logo>Market palace</Logo>
      <Icons>
        <FaListAlt/>
        <FaShoppingCart/>
        {isAuthenticated && <FaDoorOpen/>}
      </Icons>
    </HeaderContainer>
  )
}

export default Header
