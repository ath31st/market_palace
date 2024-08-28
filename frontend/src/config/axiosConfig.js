import axios from 'axios'

const apiUrl = process.env.REACT_APP_API_BASE_URL

const axiosInstance = axios.create({
  baseURL: apiUrl,
})

axiosInstance.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('jwtToken')
    if (token) {
      config.headers.Authorization = `Bearer ${token}`
    }
    return config
  },
  (error) => Promise.reject(error),
)

axiosInstance.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response && error.response.status === 401) {
      localStorage.removeItem('jwtToken')
      window.location.href = '/login'
    }
    return Promise.reject(error)
  },
)

export default axiosInstance
