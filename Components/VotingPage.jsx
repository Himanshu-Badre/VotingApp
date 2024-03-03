import React, { useState } from 'react';
import axios from 'axios';
import Login from './Login';
import { useNavigate } from 'react-router-dom';

const VotingPage = () => {
    const initialCandidates = [
        { name: 'Candidate A', votes: 0 },
        { name: 'Candidate B', votes: 0 },
        { name: 'Candidate C', votes: 0 }
    ];
 
    const [candidates, setCandidates] = useState(initialCandidates);
    const [voted, setVoted] = useState(false);
    const [selectedCandidate, setSelectedCandidate] = useState(null);
    // Add these lines
  const [showLogoutConfirmation, setShowLogoutConfirmation] = useState(false);
  const navigate = useNavigate();

    const handleSelectCandidate = (candidate) => {
        setSelectedCandidate(candidate);
    };
    const logout = async () => {
        // Send a request to the backend to log out the user
        const response= await axios.post('http://localhost:8080/logout');
        console.log(response.data);
      };

      const handleLogout = () => {
        //setShowLogoutConfirmation(true);
    
        const loginPath = '/login';
        window.location.pathname = loginPath;
    };

    //   const handleLogoutConfirmation = (confirmLogout) => {
    //     if (confirmLogout) {
    //       logout().then(() => {
    //         navigate('/login');
    //       });
    //     }
    //     setShowLogoutConfirmation(false);
    //   };

    const handleVote = async () => {
        if (!voted && selectedCandidate) {
            try {
                // Send the selected candidate's data to the backend
                const response = await axios.post('http://localhost:8080/vote', { candidateName: selectedCandidate.name });
                console.log(response.data); // Handle successful vote
 
                // Update the frontend state to reflect the vote
                const updatedCandidates = candidates.map((c) =>
                    c.name === selectedCandidate.name
                        ? { ...c, votes: c.votes + 1 }
                        : c
                );
                setCandidates(updatedCandidates);
                console.log(updatedCandidates);
                setVoted(true); // Set voted to true after successful vote
                setSelectedCandidate(null); // Reset selected candidate
            } catch (error) {
                console.error('Error voting:', error);
            }
        } else if (selectedCandidate) {
            alert('You have already voted for another candidate.');
        } else {
            alert('Please select a candidate to vote.');
        }
    };

    return (
        <div className="voting-page">
            <h2 className="title">Voting Page</h2>
            <div className="candidates-container">
                {candidates.map((candidate) => (
                    <div key={candidate.name} className="candidate">
                        <h2 className="candidate-name">{candidate.name}</h2>
                        <p className="votes-count">Votes: {candidate.votes}</p>
                        <label>
                            <input
                                type="radio"
                                name="candidate"
                                value={candidate.name}
                                onChange={() => handleSelectCandidate(candidate)}
                            />{' '}
                            Select
                        </label>
                   </div>
                ))}
            </div>
            <button onClick={handleVote} className="vote-button">
                Vote
            </button>

            {/* Logout Button */}
            <LogoutButton onClick={handleLogout} />
            {/* <LogoutConfirmation
                show={showLogoutConfirmation}
                onConfirm={handleLogoutConfirmation}
            /> */}
            {voted && <p className="already-voted">You already voted</p>}
        </div>
    );
};
        const LogoutButton = ({ onClick }) => {
            return <button onClick={onClick} className="logout-button">Logout</button>;
        };
        // const LogoutConfirmation = ({ show, onConfirm }) => {
        //     if (!show) {
        //     return null;
        //     }

    // return (
    //     <div className="logout-confirmation">
    //       <p>Are you sure you want to logout?</p>
    //       <button onClick={() => onConfirm(true)} className="confirm-logout-button">Yes</button>
    //       <button onClick={() => onConfirm(false)} className="cancel-logout-button">No</button>
    //     </div>
    //   );
    // };

export default VotingPage;
