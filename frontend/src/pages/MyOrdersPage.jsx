import React, { useEffect, useState } from 'react'
import styled from 'styled-components'
import SearchAndSort from '../components/container/SearchAndSort'
import axios from '../config/axiosConfig'
import { useNavigate } from 'react-router-dom'

const OrdersContainer = styled.div`
    padding: 20px;
`

const OrderList = styled.div`
    margin-top: 20px;
`

const OrderItem = styled.div`
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 20px;
    border: 1px solid #ddd;
    margin-bottom: 20px;
    background-color: #f9f9f9;
`

const OrderDetails = styled.div`
    display: flex;
    flex-direction: column;
    color: green;
`

const OrderID = styled.p`
    font-size: 20px;
    font-weight: bold;
`

const OrderDate = styled.p`
    font-size: 16px;
`

const DeliveryDetails = styled.p`
    font-size: 16px;
`

const ProductPhotos = styled.div`
    display: flex;
    gap: 10px;
`

const ProductPhoto = styled.img`
    width: 80px;
    height: 80px;
    object-fit: cover;
    border: 1px solid #ddd;
    cursor: pointer;
`

const OrderStatus = styled.div`
    text-align: right;
    color: green;
    font-size: 18px;
`

const OrderCost = styled.div`
    text-align: right;
    font-size: 20px;
    font-weight: bold;
    color: green;
`

const MyOrdersPage = () => {
  const [orders, setOrders] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState(null)
  const navigate = useNavigate()

  useEffect(() => {
    const fetchOrders = async () => {
      try {
        const response = await axios.get('/api/v1/my-orders')
        setOrders(response.data)
        setLoading(false)
      } catch (error) {
        setError(error)
      }
    }

    fetchOrders()
  }, [])

  const handleProductClick = (id) => {
    navigate(`/products/${id}`)
  }

  return (
    <OrdersContainer>
      <SearchAndSort/>
      {loading ? <p>Loading...</p> : error ? <p>Error: {error.message}</p> :
        <>
          <OrderList>
            {orders.map((order) => (
              <OrderItem key={order.id}>
                <OrderDetails>
                  <OrderID>Order ID: {order.id}</OrderID>
                  <OrderDate>Order date: {order.orderDate}</OrderDate>
                  <DeliveryDetails>
                    Delivery address: {order.deliveryAddress}
                    <br/>
                    Delivery date: {order.deliveryDate}
                  </DeliveryDetails>
                </OrderDetails>
                <ProductPhotos>
                  {order.products.map((item, index) => (
                    <ProductPhoto
                      onClick={() => handleProductClick(item.id)}
                      key={index} src={item.imageLink}
                      alt="product"/>
                  ))}
                </ProductPhotos>
                <OrderStatus>
                  <p>Order status: {order.orderStatus}</p>
                  <OrderCost>Order cost: {order.orderCost}$</OrderCost>
                </OrderStatus>
              </OrderItem>
            ))}
          </OrderList>
        </>}
    </OrdersContainer>
  )
}

export default MyOrdersPage
