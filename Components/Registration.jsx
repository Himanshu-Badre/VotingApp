import React from 'react'
import { Link } from 'react-router-dom'
import axios from 'axios'

function Registration() {
  const handleSubmit = async (event) => {
    event.preventDefault()

    const formElements = event.target.elements
    const username = formElements.username.value.trim()
    const password = formElements.password.value.trim()
    const email = formElements.email.value.trim()
    const phoneNo = formElements.phoneNo.value.trim()
    const role = formElements.role.value

    // Client-side validation for mobile number
    if (!/^\d{10}$/.test(phoneNo)) {
      alert('Please enter a valid 10-digit phone number.')
      return
    }

    // Client-side validation for email
    if (!/\S+@\S+\.\S+/.test(email)) {
      alert('Please enter a valid email address.')
      return
    }

    try {
      const apiResponse = await axios.post('http://localhost:8080/register', { username, password, email, phoneNo, role })
      
      const {isUserExist} = apiResponse?.data;
 
        if(isUserExist ==='true'){
          alert('User already exist.');
        }else{
          alert('Registration successful. Please log in.');
          window.location.href = '/login';
        } 

    } catch (error) {
      console.error(error) // Log registration errors
      
    }
  }

  return (
    <div className="registration-form">
      <h2 className="form-title">Create your account</h2>
      <form noValidate onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="username">Username</label>
          <input
            id="username"
            name="username"
            type="text"
            className="form-control"
            required
            title="Username must be at least 3 characters long"
            minLength="3"
          />
        </div>
        <div className="form-group">
          <label htmlFor="email">Email</label>
          <input
            id="email"
            name="email"
            type="email"
            className="form-control"
            required
            title="Please enter a valid email address"
          />
        </div>
        <div className="form-group">
          <label htmlFor="phoneNo">Phone No</label>
          <input
            id="phoneNo"
            name="phoneNo"
            type="tel"
            className="form-control"
            inputMode="numeric"
            maxLength="10"
            required
            title="Please enter a 10-digit phone number"
            pattern="[0-9]{10}"
          />
        </div>
        <div className="form-group">
          <label htmlFor="password">Password</label>
          <input
            id="password"
            name="password"
            type="password"
            className="form-control"
            required
            title="Password must be at least 8 characters long"
            minLength="8"
          />
        </div>
        <div className="form-group role-selection">
          <label htmlFor="role">Role</label>
          <select id="role" name="role" className="form-control" required>
            <option value="">Select Role</option>
            <option value="admin">Admin</option>
            <option value="user">User</option>
          </select>
          {/* <span className="required-indicator">*</span> */}
        </div>
        <button type="submit" className="register-button">
          Register
        </button>
        <Link to="/login" className="login-link">
          Already have an account? Log in
        </Link>
      </form>
    </div>
  )
}

export default Registration