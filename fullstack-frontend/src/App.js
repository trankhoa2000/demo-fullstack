import './App.css';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css'
import Navbar from './layout/Navbar';
import Home from './pages/Home';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import AddNews from './users/AddNews';
import EditNews from './users/EditNews';
import ViewNews from './users/ViewNews';

function App() {
  return (
    <div className="App">
      <Router>
        <Navbar />
        <Routes>
          <Route exact path='/' element={<Home />} />
          <Route exact path='/addnews' element={<AddNews />} />
          <Route exact path='/editnews/:id' element={<EditNews />} />
          <Route exact path="/viewnews/:id" element={<ViewNews/>} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
