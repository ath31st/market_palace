import axios from 'axios'
import store from '../redux/store'
import { performLogout } from '../redux/authSlice'

const apiUrl = process.env.REACT_APP_API_BASE_URL

axios.defaults.baseURL = apiUrl

axios.interceptors.request.use(
  config => {
    const token = store.getState().auth.token
    if (token) {
      config.headers.Authorization = `Bearer ${token}`
    }
    return config
  },
  error => Promise.reject(error),
)

axios.interceptors.response.use(
  response => response,
  error => {
    if (error.response && error.response.status === 401) {
      store.dispatch(performLogout())
      window.location.href = '/login'
    }
    return Promise.reject(error)
  },
)

export default axios
