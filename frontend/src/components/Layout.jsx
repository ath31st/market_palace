import React from 'react'
import { Outlet } from 'react-router-dom'
import Header from '../components/Header'
import Footer from '../components/Footer'
import styled from 'styled-components'

const OuterContainer = styled.main`
    padding: 20px;
    min-height: calc(100vh - 140px);
    display: flex;
    flex-direction: column;
    background-image: url('/background/common_bg.jpg');
    background-size: cover;
    background-position: center;
`

const InnerContainer = styled.div`
    max-width: 1200px;
    width: 100%;
    margin: 0 auto;
    flex-grow: 1;
`

const Layout = () => {
  return (
    <>
      <Header/>
      <OuterContainer>
        <InnerContainer>
          <Outlet/>
        </InnerContainer>
      </OuterContainer>
      <Footer/>
    </>
  )
}

export default Layout
