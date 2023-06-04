import React from 'react';
import Navbar from './components/Navbar';
import Hero from "./components/Hero";
import CallToActionButton from "./components/CallToActionButton";

function App() {
  return (
    <div className="App">
        <Navbar />
        <Hero />
        <CallToActionButton/>

    </div>
  );
}

export default App;
