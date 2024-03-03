import React, { useState, useEffect } from 'react';
import axios from 'axios';

const AdminHomePage = () => {
  const [candidates, setCandidates] = useState([]);

  useEffect(() => {
    const fetchCandidateVotes = async () => {
      try {
        const response = await axios.get('http://localhost:8080/candidates');
        setCandidates(response.data);
      } catch (error) {
        console.error('Error fetching candidate votes:', error);
      }
    };
    fetchCandidateVotes();
  }, []);

  const handleLogout = () => {
    // Implement logout logic...
    const loginPath = '/login';
    window.location.pathname = loginPath;
  };

  return (
    <div className="admin-homepage">
      <h2>Admin Home Page</h2>
      <div className="candidate-list">
        {candidates.map((candidate) => (
          <div key={candidate.id} className="candidate">
            <h3>{candidate.name}</h3>
            <p>Votes: {candidate.votes}</p>
          </div>
        ))}
      </div>
      <button onClick={handleLogout}>Logout</button>
    </div>
  );
};

export default AdminHomePage;
