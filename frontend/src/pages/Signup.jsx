import React, { useState } from 'react'
import { useForm } from 'react-hook-form'
import axios from 'axios'
import InputField from '../components/input/InputField'
import SubmitButton from '../components/button/SubmitButton'
import ErrorMessage from '../components/message/ErrorMessage'
import { Link, useNavigate } from 'react-router-dom'
import SignInUpForm from '../components/form/SignInUpForm'
import SignInUpContainer from '../components/container/SignInUpContainer'
import LabelWithLink from '../components/label/LabelWithLink'

const Signup = () => {
  const apiUrl = process.env.REACT_APP_API_BASE_URL

  const axiosInstance = axios.create({
    baseURL: apiUrl,
  })

  const {
    register,
    handleSubmit,
    formState: { errors },
    watch,
  } = useForm()

  const [error, setError] = useState(null)
  const navigate = useNavigate()
  const password = watch('password')

  const onSubmit = async (data) => {
    console.log(data)
    try {
      await axiosInstance.post(`/api/v1/signup`, data)
      navigate('/login')
    } catch (error) {
      console.error('Error during registration:', error)
      setError(`Registration failed. Please try again.`)
    }
  }

  return (
    <SignInUpContainer>
      <SignInUpForm onSubmit={handleSubmit(onSubmit)}>
        <h2>Signup</h2>
        <InputField
          type="text"
          placeholder="First name"
          {...register('firstname', { required: 'First name is required' })}
        />
        {errors.firstname &&
          <ErrorMessage>{errors.firstname.message}</ErrorMessage>}

        <InputField
          type="text"
          placeholder="Last name"
          {...register('lastname', { required: 'Last name is required' })}
        />
        {errors.lastname &&
          <ErrorMessage>{errors.lastname.message}</ErrorMessage>}

        <InputField
          type="email"
          placeholder="Email"
          {...register('email', {
            required: 'Email is required',
            pattern: {
              value: /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/,
              message: 'Invalid email address format',
            },
          })}
        />
        {errors.email && <ErrorMessage>{errors.email.message}</ErrorMessage>}

        <InputField
          type="password"
          placeholder="Password"
          {...register('password', {
            required: 'Password is required',
            minLength: {
              value: 3,
              message: 'Password must be at least 3 characters',
            },
          })}
        />
        {errors.password &&
          <ErrorMessage>{errors.password.message}</ErrorMessage>}

        <InputField
          type="password"
          placeholder="Confirm Password"
          {...register('confirmPassword', {
            required: 'Confirm Password is required',
            validate: (value) =>
              value === password || 'Passwords do not match',
          })}
        />
        {errors.confirmPassword &&
          <ErrorMessage>{errors.confirmPassword.message}</ErrorMessage>}
        {error && <ErrorMessage>{error}</ErrorMessage>}
        <SubmitButton type="submit">Sign Up</SubmitButton>
        <LabelWithLink>
          Already have an account? <Link to="/login">Sign in</Link>
        </LabelWithLink>
      </SignInUpForm>
    </SignInUpContainer>
  )
}

export default Signup
