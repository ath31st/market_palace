import { createSlice, createAsyncThunk } from '@reduxjs/toolkit'
import axios from 'axios'
import { jwtDecode } from 'jwt-decode'

const apiUrl = process.env.REACT_APP_API_BASE_URL

const axiosInstance = axios.create({
  baseURL: apiUrl,
})

export const login = createAsyncThunk(
  'auth/login',
  async (formData, { rejectWithValue }) => {
    try {
      const response = await axiosInstance.post(`${apiUrl}/api/v1/login`,
        formData)
      const { token } = response.data
      const user = jwtDecode(token)
      localStorage.setItem('jwtToken', token)
      return { token, user }
    } catch (error) {
      return rejectWithValue(
        error.response ? error.response.data.message : 'Server error')
    }
  })

export const performLogout = createAsyncThunk(
  'auth/logout',
  async (_, { rejectWithValue }) => {
    try {
      localStorage.removeItem('jwtToken')
      return {}
    } catch (error) {
      return rejectWithValue('Logout failed')
    }
  })

const authSlice = createSlice({
  name: 'auth',
  initialState: {
    token: localStorage.getItem('jwtToken'),
    isAuthenticated: !!localStorage.getItem('jwtToken'),
    user: localStorage.getItem('jwtToken') ? jwtDecode(
      localStorage.getItem('jwtToken')) : null,
    error: null,
  },
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(login.fulfilled, (state, action) => {
      state.token = action.payload.token
      state.isAuthenticated = true
      state.user = action.payload.user
      state.error = null
    }).addCase(login.rejected, (state, action) => {
      state.error = action.payload
      state.token = null
      state.user = null
      state.isAuthenticated = false
    }).addCase(performLogout.fulfilled, (state) => {
      state.token = null
      state.isAuthenticated = false
      state.user = null
      state.error = null
    }).addCase(performLogout.rejected, (state, action) => {
      state.error = action.payload
    })
  },
})

export default authSlice.reducer
