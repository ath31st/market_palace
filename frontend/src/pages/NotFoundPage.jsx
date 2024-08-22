import React from 'react'
import { Link } from 'react-router-dom'
import styled from 'styled-components'

const NotFoundContainer = styled.div`
    margin-top: 50px;
    display: flex;
    flex-direction: column;
    align-items: center;
`

const ErrorCode = styled.h1`
    font-size: 10rem;
    margin: 0;
    color: #fff;
`

const ErrorMessage = styled.h2`
    font-size: 2rem;
    margin: 20px 0;
    color: #fff;
`

const HomeLink = styled(Link)`
    display: inline-block;
    margin-top: 20px;
    padding: 10px 20px;
    background-color: #28a745;
    color: white;
    text-decoration: none;
    border-radius: 5px;
    font-size: 1.2rem;

    &:hover {
        background-color: #218838;
    }
`

const NotFoundPage = () => {
  return (
    <NotFoundContainer>
      <ErrorCode>404</ErrorCode>
      <ErrorMessage>Oops! This page doesn't exist.</ErrorMessage>
      <HomeLink to="/">Go back to main page</HomeLink>
    </NotFoundContainer>
  )
}

export default NotFoundPage
