import { useEffect, useState } from 'react'
import axios from 'axios'
import SearchAndSort from '../components/container/SearchAndSort'
import ProductList from '../components/ProductList'
import Pagination from '../components/Pagination'

const Products = () => {
  const apiUrl = process.env.REACT_APP_API_BASE_URL
  const [currentPage, setCurrentPage] = useState(1)
  const [totalPages, setTotalPages] = useState(1)
  const [products, setProducts] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState(null)

  useEffect(() => {
    const fetchProducts = async (page = 1, size = 10) => {
      const axiosInstance = axios.create({
        baseURL: apiUrl,
      })

      try {
        const response = await axiosInstance.get('/api/v1/products', {
          params: { page, size },
        })
        console.log(response.data)
        setProducts(response.data.items)
        setTotalPages(response.data.totalPages)
        setCurrentPage(response.data.currentPage)
      } catch (error) {
        console.error('Error fetching products:', error)
        setError(error)
      } finally {
        setLoading(false)
      }
    }

    fetchProducts(currentPage)
  }, [currentPage, apiUrl])

  return (
    <div className="products">
      <SearchAndSort/>
      {loading ? <p>Loading...</p> : error ? <p>Error: {error.message}</p> :
        <>
          <ProductList products={products}/>
          <Pagination
            totalPages={totalPages}
            currentPage={currentPage}
            onPageChange={page => setCurrentPage(page)}
          />
        </>
      }
    </div>
  )
}

export default Products
