import { configureStore } from '@reduxjs/toolkit'
import authReducer from './authSlice'
import cartReducer from './cartSlice'
import orderSlice from './orderSlice'

const store = configureStore({
  reducer: {
    auth: authReducer,
    cart: cartReducer,
    order: orderSlice,
  },
})

export default store
