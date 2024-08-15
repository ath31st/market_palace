import styled from 'styled-components'

const LabelWithLink = styled.div`
    margin-top: 20px;
    font-size: 14px;
    color: #006400;

    a {
        color: #32CD32;
        text-decoration: none;

        &:hover {
            text-decoration: underline;
        }
    }
`

export default LabelWithLink
