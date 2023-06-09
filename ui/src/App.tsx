import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar';
import CallToActionButton from "./components/CallToActionButton";
import Home from './pages/HomePage/Home'
import GoToTopButton from "./components/GoToTopButton";
import Footer from "./components/Footer";
import Do from "./pages/DoPage/Do";
import Stay from "./pages/StayPage/Stay";
import Dine from "./pages/DinePage/Dine";
import Plan from "./pages/PlanPage/Plan";

function App() {
  return (
    <div className="App">
            <Navbar />
            <CallToActionButton />
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/do" element={<Do />}/>
                <Route path="/stay" element={<Stay />}/>
                <Route path="/dine" element={<Dine />}/>
                <Route path="/plan" element={<Plan />}/>
            </Routes>
        <GoToTopButton />
        <Footer />
    </div>
  );
}

export default App;
