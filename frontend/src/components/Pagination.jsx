import React from 'react'
import styled from 'styled-components'

const PaginationContainer = styled.div`
    display: flex;
    justify-content: center;
    margin: 20px 0;
`

const PageButton = styled.button`
    padding: 10px 15px;
    margin: 0 5px;
    border: 1px solid #ddd;
    border-radius: 5px;
    background-color: ${props => props.active ? '#32CD32' : 'white'};
    color: ${props => props.active ? 'white' : '#555'};
    cursor: pointer;

    &:hover {
        background-color: ${props => props.active ? '#32CD32' : '#f1f1f1'};
    }
`

const Pagination = ({ totalPages, currentPage, onPageChange }) => {
  const pages = [...Array(totalPages).keys()].map(number => number + 1)

  return (
    <PaginationContainer>
      {pages.map(page => (
        <PageButton
          key={page}
          active={page === currentPage}
          onClick={() => onPageChange(page)}
        >
          {page}
        </PageButton>
      ))}
    </PaginationContainer>
  )
}

export default Pagination
