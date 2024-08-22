import React from 'react'
import styled from 'styled-components'
import {
  FaShoppingCart,
  FaDoorOpen,
  FaListAlt,
  FaSignInAlt,
} from 'react-icons/fa'
import useAuth from '../hooks/useAuth'
import { useNavigate } from 'react-router-dom'
import { useDispatch } from 'react-redux'
import { performLogout } from '../redux/authSlice'

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
    cursor: pointer;
    
    &:hover {
        color: #006400;
    }
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
  const navigate = useNavigate()
  const dispatch = useDispatch()

  return (
    <HeaderContainer>
      <Logo onClick={() => navigate('/')}>Market palace</Logo>
      <Icons>
        {!isAuthenticated ? (
          <FaSignInAlt onClick={() => navigate('/login')}/>
        ) : (
          <>
            <FaListAlt onClick={() => navigate('/my-orders')}/>
            <FaShoppingCart onClick={() => navigate('/cart')}/>
            <FaDoorOpen onClick={() => dispatch(performLogout())}/>
          </>
        )}
      </Icons>
    </HeaderContainer>
  )
}

export default Header
