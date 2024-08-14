import React from 'react'
import styled from 'styled-components'
import { useSelector } from 'react-redux'

const HeaderContainer = styled.header`
    background-color: #333;
    color: white;
    padding: 10px 20px;
    display: flex;
    justify-content: space-between;
    align-items: center;
`

const NavLinks = styled.div`
    display: flex;
    gap: 15px;
`

const Text = styled.span`
    color: #ccc;
`

const Header = () => {
  const user = useSelector((state) => state.auth.user)

  return (
    <HeaderContainer>
      <h1>Market palace Project</h1>
      <NavLinks>
        {user !== null ? (
            <Text>{user.email}</Text>
        ) : (
          <Text>You are not authorized</Text>
        )}
      </NavLinks>
    </HeaderContainer>
  )
}

export default Header
