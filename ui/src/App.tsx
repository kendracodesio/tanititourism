import React from 'react';
import Navbar from './components/Navbar';
import Hero from "./components/Hero";
import CallToActionButton from "./components/CallToActionButton";
import About from "./components/About";
import Discover from "./components/Discover";
import Gallery from "./components/Gallery";
import GoToTopButton from "./components/GoToTopButton";
import Footer from "./components/Footer";

function App() {
  return (
    <div className="App">
        <Navbar />
        <Hero />
        <CallToActionButton/>
        <div className="container">
            <About />
            <Discover />
            <Gallery />
        </div>
        <GoToTopButton />
        <Footer />
    </div>
  );
}

export default App;
