import { useEffect, useState } from 'react'
import useAuth from '../hooks/useAuth'
import axios from 'axios'

const Products = () => {
  const isAuthenticated = useAuth().isAuthenticated
  const apiUrl = process.env.REACT_APP_API_BASE_URL
  const [products, setProducts] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState(null)

  useEffect(() => {
    const axiosInstance = axios.create({
      baseURL: apiUrl,
    })

    const fetchProducts = async () => {
      try {
        const response = await axiosInstance.get('/api/v1/products')
        console.log(response.data)
        setProducts(response.data.items)
      } catch (error) {
        console.error('Error fetching products:', error)
        setError(error)
      } finally {
        setLoading(false)
      }
    }

    fetchProducts()
  }, [apiUrl])

  return (
    <div className="products">
      {loading ? <p>Loading...</p> : error ? <p>Error: {error.message}</p> :
        <ul>{products.map(
          product => <li key={product.id}>{product.title}</li>)}</ul>}
    </div>
  )
}

export default Products
