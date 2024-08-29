import React, { useEffect, useState } from 'react'
import styled from 'styled-components'
import SearchAndSort from '../components/container/SearchAndSort'
import axios from '../config/axiosConfig'
import EmptyOrderList from '../components/EmptyOrderList'
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
    border-radius: 5px;
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
    border-radius: 5px;
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

const formatDate = (dateString) => {
  const options = { year: 'numeric', month: 'long', day: 'numeric' }
  return new Date(dateString).toLocaleDateString('en-EN', options)
}

const MyOrdersPage = () => {
  const [orders, setOrders] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState(null)
  const navigate = useNavigate()
  const [search, setSearch] = useState('')
  const [sortBy, setSortBy] = useState('default')
  const sortOptions = [
    { value: 'cost', label: 'Cost' },
    { value: 'date', label: 'Date' },
  ]

  useEffect(() => {
    const fetchOrders = async () => {
      setLoading(true)
      try {
        const response = await axios.get('/api/v1/my-orders', {
          params: {
            search: search || undefined,
            sortBy: sortBy !== 'default' ? sortBy : undefined,
          },
        })
        setOrders(response.data)
      } catch (error) {
        setError(error)
      } finally {
        setLoading(false)
      }
    }

    fetchOrders()
  }, [search, sortBy])

  const handleProductClick = (id) => {
    navigate(`/products/${id}`)
  }

  return (
    <OrdersContainer>
      <SearchAndSort
        onSort={setSortBy}
        onSearch={setSearch}
        sortOptions={sortOptions}
        searchPlaceholder={'Search orders by id'}
      />
      {loading ? <p>Loading...</p> : error ? <p>Error: {error.message}</p> :
        <>
          {orders.length === 0 ? (
            <EmptyOrderList/>
          ) : (
            <OrderList>
              {orders.map((order) => (
                <OrderItem key={order.id}>
                  <OrderDetails>
                    <OrderID>Order ID: {order.id}</OrderID>
                    <OrderDate>Order date:
                      {formatDate(order.orderDate)}
                    </OrderDate>
                    <DeliveryDetails>
                      Delivery address: {order.deliveryAddress}
                      <br/>
                      Delivery date: {formatDate(order.deliveryDate)}
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
          )}
        </>}
    </OrdersContainer>
  )
}

export default MyOrdersPage
