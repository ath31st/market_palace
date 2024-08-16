import React from 'react'
import { Outlet } from 'react-router-dom'
import Header from '../components/Header'
import Footer from '../components/Footer'
import styled from 'styled-components'

const Container = styled.main`
    padding: 20px;
    min-height: calc(100vh - 140px);
    display: flex;
    flex-direction: column;
    background-image: url('/background/common_bg.jpg');
    background-size: cover;
    background-position: center;
    margin: 0 auto;
`

const Layout = () => {
  return (
    <>
      <Header/>
      <Container>
        <Outlet/>
      </Container>
      <Footer/>
    </>
  )
}

export default Layout
