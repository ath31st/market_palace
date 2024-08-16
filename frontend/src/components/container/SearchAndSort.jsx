import React from 'react';
import styled from 'styled-components';
import { BsSearch } from 'react-icons/bs';

const SearchAndSortContainer = styled.div`
    display: flex;
    justify-content: flex-end;
    gap: 1rem;
    align-items: center;
    padding: 10px 20px;
    margin-top: 10px;
`;

const SearchBar = styled.div`
    display: flex;
    align-items: center;
    background-color: white;
    border: 1px solid #ddd;
    border-radius: 5px;
    width: 250px;
    height: 35px;
`;

const SearchInput = styled.input`
    border: none;
    outline: none;
    padding: 5px;
    font-size: 16px;
    flex-grow: 1;
    color: #555;
`;

const SortSelect = styled.select`
    padding: 5px 10px;
    border: 1px solid #ddd;
    border-radius: 5px;
    font-size: 16px;
    width: 250px;
    height: 35px;
    color: #555;
`;

const SearchAndSort = () => {
  return (
    <SearchAndSortContainer>
      <SearchBar>
        <SearchInput type="text" placeholder="Search input" />
        <BsSearch />
      </SearchBar>
      <SortSelect>
        <option value="default">Sort by</option>
        <option value="price">Price</option>
        <option value="title">Title</option>
      </SortSelect>
    </SearchAndSortContainer>
  );
};

export default SearchAndSort;
