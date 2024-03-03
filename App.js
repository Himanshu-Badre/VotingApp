import Login from './Components/Login';
//import Registration from './Components/Registeration';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './App.css';
import Registration from './Components/Registration';
import VotingPage from './Components/VotingPage';
import AdminHomePage from './Components/AdminHomePage';

function App() {

  return (<>
     <Router>
      <Routes>
        <Route path="/registration" element={<Registration />} /> 
        <Route path="/login" element={<Login />} />
      </Routes>
    </Router>

</>);
}

export default App;
