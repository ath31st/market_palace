import { Route, Routes } from 'react-router-dom'
import Products from './pages/Products'
import Layout from './components/Layout'
import Signup from './pages/Signup'
import NotFoundPage from './pages/NotFoundPage'
import Login from './pages/Login'
import PrivateRoute from './components/PrivateRoute'
import ProductPage from './pages/ProductPage'
import CartPage from './pages/CartPage'
import MyOrdersPage from './pages/MyOrdersPage'

function App () {
  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<Layout/>}>
          <Route index element={<Products/>}/>
          <Route path="/products/:id" element={<ProductPage/>}/>
          <Route path="/cart"
                 element={<PrivateRoute element={<CartPage/>}/>}/>
          <Route path="/my-orders"
                 element={<PrivateRoute element={<MyOrdersPage/>}/>}/>
          <Route path="*" element={<NotFoundPage/>}/>
        </Route>
        <Route path="/signup" element={<Signup/>}/>
        <Route path="/login" element={<Login/>}/>
      </Routes>
    </div>
  )
}

export default App
