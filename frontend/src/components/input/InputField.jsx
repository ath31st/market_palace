import styled from 'styled-components'

const InputField = styled.input`
    width: 100%;
    padding: 10px;
    margin-bottom: 15px;
    border: 1px solid #006400;
    border-radius: 5px;
    font-size: 16px;

    &:focus {
        border-color: #32CD32;
        outline: none;
    }
`

export default InputField
