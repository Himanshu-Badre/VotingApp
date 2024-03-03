import React, { useState } from 'react';
import axios from 'axios';
import VotingPage from './VotingPage';
import Registration from './Registration';
import AdminHomePage from './AdminHomePage';

const Login = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState(null);
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [role, setRole] = useState('');

  const handleLoginSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/login', { username, password });
      console.log(response.data); // Handle successful login

      // Set flag in session storage to indicate successful login
      sessionStorage.setItem('isLoggedIn', 'true');

      // Set isLoggedIn state to true
      setIsLoggedIn(true);

      // Set role state to the selected role
      setRole(document.getElementById('role').value);

    } catch (err) {
      console.error(err);
      setError('Login failed. Please check your username and password.');
    }
  };

  const handleRegistrationClick = () => {
    const registrationPath = '/registration';
    window.location.pathname = registrationPath;
  };
  // // Redirect to VotingPage if isLoggedIn is true
  // if (isLoggedIn) {
  //     return <VotingPage />;
  // }

    // Redirect to corresponding page based on selected role
      if (isLoggedIn && role === 'Admin') {
        return <AdminHomePage />;
    } else if (isLoggedIn && role === 'User') {
        return <VotingPage />;
    }

  return (
    <div className="login-container">
        <h1>Voting App Login</h1>

        <form onSubmit={handleLoginSubmit} className="login-form">
            <input
                type="text"
                placeholder="Username"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
                required
            />
            <input
                type="password"
                placeholder="Password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                required
            />
          <div className="role-selection-container ">
          <label htmlFor="role">Role</label>
          <select id="role" name="role" className="form-control" required>
            <option value="">Select Role</option>
            <option value="Admin">Admin</option>
            <option value="User">User</option>
          </select>
          {/* <span className="required-indicator">*</span> */}
        </div>
            <button type="submit">Login</button>
        </form>
        {error && <p className="error-message">{error}</p>}

        <button
            onClick={handleRegistrationClick}
            style={{
                backgroundColor: '#4CAF50',
                color: 'white',
                padding: '10px 20px',
                border: 'none',
                borderRadius: '5px',
                cursor: 'pointer',
                fontSize: '16px',
                marginTop: '10px',
                width: '100%',
            }}
        >
            Register
        </button>

        
    </div>
  );
};

export default Login;