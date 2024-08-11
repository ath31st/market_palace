import { useSelector } from 'react-redux'

const useAuth = () => {
  const isAuthenticated = useSelector((state) => state.auth.isAuthenticated)
  const token = useSelector((state) => state.auth.token)
  return { isAuthenticated, token }
}

export default useAuth
