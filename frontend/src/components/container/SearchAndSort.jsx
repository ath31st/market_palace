import React, { useState } from 'react'
import styled from 'styled-components'
import { BsSearch } from 'react-icons/bs'

const SearchAndSortContainer = styled.div`
    display: flex;
    justify-content: flex-end;
    gap: 1rem;
    align-items: center;
    padding: 10px 20px;
    margin-top: 10px;
`

const SearchBar = styled.div`
    display: flex;
    align-items: center;
    background-color: white;
    border: 1px solid #ddd;
    border-radius: 5px;
    width: 250px;
    height: 35px;
`

const SearchInput = styled.input`
    border: none;
    outline: none;
    padding: 5px;
    font-size: 16px;
    flex-grow: 1;
    color: #555;
`

const SortSelect = styled.select`
    padding: 5px 10px;
    border: 1px solid #ddd;
    border-radius: 5px;
    font-size: 16px;
    width: 250px;
    height: 35px;
    color: #555;
`

const SearchAndSort = ({ onSearch, onSort, sortOptions }) => {
  const [searchTerm, setSearchTerm] = useState('')
  const [sortBy, setSortBy] = useState('default')

  const handleSearchChange = (e) => {
    setSearchTerm(e.target.value)
    onSearch(e.target.value)
  }

  const handleSortChange = (e) => {
    setSortBy(e.target.value)
    onSort(e.target.value)
  }

  return (
    <SearchAndSortContainer>
      <SearchBar>
        <SearchInput
          type="text"
          placeholder="Search products"
          value={searchTerm}
          onChange={handleSearchChange}
        />
        <BsSearch/>
      </SearchBar>
      <SortSelect value={sortBy} onChange={handleSortChange}>
        <option value="default">Sort by</option>
        {sortOptions.map((option) => (
          <option key={option.value} value={option.value}>
            {option.label}
          </option>
        ))}
      </SortSelect>
    </SearchAndSortContainer>
  )
}

export default SearchAndSort
