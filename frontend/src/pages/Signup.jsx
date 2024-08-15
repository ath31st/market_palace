import React, { useState } from 'react'
import axios from 'axios'
import InputField from '../components/input/InputField'
import SubmitButton from '../components/button/SubmitButton'
import ErrorMessage from '../components/message/ErrorMessage'
import { Link, useNavigate } from 'react-router-dom'
import SignInUpForm from '../components/form/SignInUpForm'
import SignInUpContainer from '../components/container/SignInUpContainer'
import LabelWithLink from '../components/label/LabelWithLink'

const Signup = () => {
  const [formData, setFormData] = useState({
    firstname: '',
    lastname: '',
    email: '',
    password: '',
    confirmPassword: '',
  })

  const [error, setError] = useState('')
  const navigate = useNavigate()

  const handleChange = (e) => {
    const { name, value } = e.target
    setFormData({
      ...formData,
      [name]: value,
    })
  }

  const handleSubmit = async (e) => {
    e.preventDefault()
    if (
      formData.password === '' ||
      formData.email === '' ||
      formData.firstname === '' ||
      formData.lastname === ''
    ) {
      setError('Registration data cannot be empty')
    } else if (formData.password !== formData.confirmPassword) {
      setError('Passwords do not match')
    } else {
      setError('')
      try {
        await axios.post(`/api/v1/signup`, formData)
        navigate('/login')
      } catch (error) {
        console.error('Error during registration:', error)
        if (error.response && error.response.data) {
          setError(`Registration failed with cause: ${error.response.data}.`)
        } else {
          setError(`Registration failed. Please try again.`)
        }
      }
    }
  }

  return (
    <SignInUpContainer>
      <SignInUpForm onSubmit={handleSubmit}>
        <h2>Signup</h2>
        <InputField
          type="text"
          name="firstname"
          placeholder="First name"
          value={formData.firstname}
          onChange={handleChange}
        />
        <InputField
          type="text"
          name="lastname"
          placeholder="Last name"
          value={formData.lastname}
          onChange={handleChange}
        />
        <InputField
          type="email"
          name="email"
          placeholder="Email"
          value={formData.email}
          onChange={handleChange}
        />
        <InputField
          type="password"
          name="password"
          placeholder="Password"
          value={formData.password}
          onChange={handleChange}
        />
        <InputField
          type="password"
          name="confirmPassword"
          placeholder="Confirm Password"
          value={formData.confirmPassword}
          onChange={handleChange}
        />
        {error && <ErrorMessage>{error}</ErrorMessage>}
        <SubmitButton type="submit" onClick={handleSubmit}>
          Sign Up
        </SubmitButton>
        <LabelWithLink>
          Already have an account? <Link to="/login">Sign in</Link>
        </LabelWithLink>
      </SignInUpForm>
    </SignInUpContainer>
  )
}

export default Signup
