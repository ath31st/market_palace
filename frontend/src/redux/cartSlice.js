import { createSlice, createAsyncThunk } from '@reduxjs/toolkit'
import axios from '../config/axiosConfig'

export const fetchCart = createAsyncThunk(
  'cart/fetchCart',
  async (_, { rejectWithValue }) => {
    try {
      const response = await axios.get('/api/v1/cart')
      return response.data
    } catch (error) {
      return rejectWithValue(error.response?.data || 'Error fetching cart')
    }
  },
)

export const addToCart = createAsyncThunk(
  'cart/addToCart',
  async (cartReq, { rejectWithValue }) => {
    try {
      const response = await axios.post('/api/v1/cart', cartReq)
      return response.data
    } catch (error) {
      return rejectWithValue(error.response?.data || 'Error adding to cart')
    }
  },
)

export const updateCart = createAsyncThunk(
  'cart/updateCart',
  async (cartChange, { rejectWithValue }) => {
    try {
      const response = await axios.put('/api/v1/cart', cartChange)
      return response.data
    } catch (error) {
      return rejectWithValue(error.response?.data || 'Error updating cart')
    }
  },
)

const cartSlice = createSlice({
  name: 'cart',
  initialState: {
    items: [],
    status: 'idle',
    error: null,
  },
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(fetchCart.pending, (state) => {
      state.status = 'loading'
    }).addCase(fetchCart.fulfilled, (state, action) => {
      state.status = 'succeeded'
      state.items = action.payload.products
    }).addCase(fetchCart.rejected, (state, action) => {
      state.status = 'failed'
      state.error = action.payload
    }).addCase(addToCart.fulfilled, (state, action) => {
      state.status = 'succeeded'
      state.items = action.payload.products
    }).addCase(addToCart.rejected, (state, action) => {
      state.status = 'failed'
      state.error = action.payload
    }).addCase(updateCart.fulfilled, (state, action) => {
      state.status = 'succeeded'
      state.items = action.payload.products
    }).addCase(updateCart.rejected, (state, action) => {
      state.status = 'failed'
      state.error = action.payload
    })
  },
})

export default cartSlice.reducer
