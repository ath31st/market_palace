import React from 'react'
import styled from 'styled-components'
import SearchAndSort from '../components/container/SearchAndSort'

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
  const orders = [
    {
      id: 'Order123',
      date: '2024-08-21',
      deliveryAddress: '123 Street, City',
      deliveryDate: '2024-08-25',
      status: 'Delivered',
      cost: '$150',
      products: [
        'path/to/product1.jpg',
        'path/to/product2.jpg',
        'path/to/product3.jpg',
      ],
    },
    {
      id: 'Order234',
      date: '2024-08-13',
      deliveryAddress: '123 Street, City',
      deliveryDate: '2024-08-26',
      status: 'Delivered',
      cost: '$550',
      products: [
        'path/to/product4.jpg',
        'path/to/product5.jpg',
        'path/to/product6.jpg',
      ],
    },
  ]

  return (
    <OrdersContainer>
      <SearchAndSort/>
      <OrderList>
        {orders.map((order) => (
          <OrderItem key={order.id}>
            <OrderDetails>
              <OrderID>Order ID: {order.id}</OrderID>
              <OrderDate>Order date: {order.date}</OrderDate>
              <DeliveryDetails>
                Delivery address: {order.deliveryAddress}
                <br/>
                Delivery date: {order.deliveryDate}
              </DeliveryDetails>
            </OrderDetails>
            <ProductPhotos>
              {order.products.map((photo, index) => (
                <ProductPhoto key={index} src={photo} alt="product"/>
              ))}
            </ProductPhotos>
            <OrderStatus>
              <p>Order status: {order.status}</p>
              <OrderCost>Order cost: {order.cost}</OrderCost>
            </OrderStatus>
          </OrderItem>
        ))}
      </OrderList>
    </OrdersContainer>
  )
}

export default MyOrdersPage
