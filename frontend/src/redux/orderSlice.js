import { createSlice, createAsyncThunk } from '@reduxjs/toolkit'
import axios from '../config/axiosConfig'

export const fetchOrders = createAsyncThunk(
  'order/fetchOrders',
  async ({ rejectWithValue }) => {
    try {
      const response = await axios.get('/api/v1/my-orders')
      return response.data
    } catch (error) {
      return rejectWithValue(
        error.response ? error.response.data.message : 'Error fetching orders',
      )
    }
  },
)

export const createOrder = createAsyncThunk(
  'order/createOrder',
  async (
    { orderCost, deliveryAddress, productIdsQuantities },
    { rejectWithValue }) => {
    try {
      const response = await axios.post(`/api/v1/my-orders`, {
        orderCost,
        deliveryAddress,
        productIdsQuantities,
      })
      return response.data
    } catch (error) {
      return rejectWithValue(
        error.response ? error.response.data.message : 'Failed to create order',
      )
    }
  },
)

const orderSlice = createSlice({
  name: 'order',
  initialState: {
    orders: [],
    order: null,
    error: null,
    success: false,
  },
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(fetchOrders.fulfilled, (state, action) => {
      state.orders = action.payload
      state.error = null
    }).addCase(fetchOrders.rejected, (state, action) => {
      state.error = action.payload
    }).addCase(createOrder.fulfilled, (state, action) => {
      state.order = action.payload
      state.error = null
      state.success = true
    }).addCase(createOrder.rejected, (state, action) => {
      state.error = action.payload
      state.success = false
    })
  },
})

export default orderSlice.reducer
